package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.persist.model.Execucao;
import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;

public class ExecucaoDAO {

    private final String TABLE = "execucao";

    private SQLiteDatabase db;
    private Database database;

    public ExecucaoDAO(Context context) {
        database = new Database(context);
    }

    public Execucao insert(Execucao execucao) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_atividade", execucao.getId_atividade());
        values.put("data", execucao.getData());
        values.put("hora", execucao.getHora());
        values.put("perc_acertos", execucao.getPerc_acertos());
        values.put("tempo", execucao.getTempo());
        values.put("observacao", execucao.getObservacao());

        long result = db.insert(TABLE, null, values);
        db.close();

        if(result == -1) {
            return null;
        } else {
            execucao.setId((int) result);
            return execucao;
        }
    }

    public ArrayList<Execucao> getExecucaosAssistido(int id_assistido) {

        ArrayList<Execucao>  list = new ArrayList<Execucao>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "id_atividade", "data", "hora", "perc_acertos", "tempo", "observacao"};

        Cursor cursor = db.query(TABLE, campos, "id_assistido=?", new String[] { Integer.toString(id_assistido) }, null, null, "tema");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Execucao execucao = new Execucao();
                execucao.setId(cursor.getInt(0));
                execucao.setId_atividade(cursor.getInt(1));
                execucao.setData(cursor.getString(3));
                execucao.setHora(cursor.getString(4));
                execucao.setPerc_acertos(cursor.getFloat(5));
                execucao.setTempo(cursor.getFloat(6));
                execucao.setObservacao(cursor.getString(2));
                list.add(execucao);
            }
        }

        db.close();

        return list;
    }
}