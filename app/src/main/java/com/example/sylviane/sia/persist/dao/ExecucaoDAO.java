package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.sylviane.sia.persist.model.Execucao;
import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ExecucaoDAO {

    private final String TABLE = "execucao";
    private final String TABLE_LEFT = "execucao_assistido";

    private SQLiteDatabase db;
    private Database database;

    public ExecucaoDAO(Context context) {
        database = new Database(context);
    }

    public Execucao insert(Execucao execucao) {

        db = database.getWritableDatabase();

        db.beginTransaction();

        ContentValues values = new ContentValues();
        values.put("id_atividade", execucao.getId_atividade());
        values.put("data", execucao.getData());
        values.put("hora", execucao.getHora());
        values.put("perc_acertos", execucao.getPerc_acertos());
        values.put("tempo", execucao.getTempo());
        values.put("observacao", execucao.getObservacao());
        values.put("pontos", execucao.getPontos());

        int result = (int)db.insert(TABLE, null, values);

        if(result == -1) {
            db.endTransaction();
            db.close();
            return null;
        }

        if(!this.addAssistido(result, execucao.getId_assistido())) {
            db.endTransaction();
            db.close();
            return null;
        }

        db.setTransactionSuccessful();;
        db.endTransaction();
        db.close();

        execucao.setId(result);

        return execucao;
    }

    private boolean addAssistido(int idExecucao, ArrayList<Integer> listAssistidos) {

        for(int i=0; i < listAssistidos.size(); i++) {

            ContentValues values = new ContentValues();
            values.put("id_execucao", idExecucao);
            values.put("id_assistido", listAssistidos.get(i));

            long result = db.insert(TABLE_LEFT, null, values);
            if(result == -1)
                return false;
        }

        return true;
    }

    public Execucao update(Execucao execucao) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id_atividade", execucao.getId_atividade());
        values.put("data", execucao.getData());
        values.put("hora", execucao.getHora());
        values.put("perc_acertos", execucao.getPerc_acertos());
        values.put("tempo", execucao.getTempo());
        values.put("observacao", execucao.getObservacao());
        values.put("pontos", execucao.getPontos());

        long result = db.update(TABLE, values, "id=?", new String[] { Integer.toString(execucao.getId()) });
        db.close();

        if(result == -1) {
            return null;
        }

        return execucao;
    }

    public ArrayList<Execucao> getExecucaosAssistido(int id_assistido) {

        ArrayList<Execucao>  list = new ArrayList<Execucao>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "id_atividade", "data", "hora", "perc_acertos", "tempo", "observacao", "pontos"};

        String quary = "SELECT a.* FROM " + TABLE + " a INNER JOIN " + TABLE_LEFT + " b ON a.id = b.id_execucao WHERE b.id_assistido=?";

        Cursor cursor = db.rawQuery(quary, new String[] { Integer.toString(id_assistido) } );

        if(cursor.getCount() > 0) {

            while(cursor.moveToNext()) {
                Execucao execucao = new Execucao();
                execucao.setId(cursor.getInt(0));
                execucao.setId_atividade(cursor.getInt(1));
                execucao.setData(cursor.getString(2));
                execucao.setHora(cursor.getString(3));
                execucao.setPerc_acertos(cursor.getFloat(4));
                execucao.setTempo(cursor.getFloat(5));
                execucao.setObservacao(cursor.getString(6));
                execucao.setPontos(cursor.getFloat(7));

                execucao.setId_assistido(this.getAssistidosExecucao(execucao.getId()));

                list.add(execucao);
            }
        }

        db.close();

        return list;
    }

    public Execucao getExecucaoId(int id) {

        db = database.getReadableDatabase();

        String[] campos = {"id", "id_atividade", "data", "hora", "perc_acertos", "tempo", "observacao", "pontos"};

        Cursor cursor = db.query(TABLE, campos, "id=?", new String[] { Integer.toString(id) }, null, null, "data");

        if(cursor.getCount() == 0) {
            db.close();
            return null;
        }

        cursor.moveToFirst();
        Execucao execucao = new Execucao();
        execucao.setId(cursor.getInt(0));
        execucao.setId_atividade(cursor.getInt(1));
        execucao.setData(cursor.getString(2));
        execucao.setHora(cursor.getString(3));
        execucao.setPerc_acertos(cursor.getFloat(4));
        execucao.setTempo(cursor.getFloat(5));
        execucao.setObservacao(cursor.getString(6));
        execucao.setPontos(cursor.getFloat(7));

        execucao.setId_assistido(getAssistidosExecucao(execucao.getId()));

        db.close();

        return execucao;
    }

    private ArrayList<Integer> getAssistidosExecucao(int idExecucao) {

        String[] campos= {"id_assistido"};

        Cursor cursor = db.query(TABLE, campos, "id_execucao=?", new String[] { Integer.toString(idExecucao) }, null, null, null);

        if(cursor.getCount() == 0)
            return null;

        ArrayList<Integer> listAssistidos = new ArrayList<>();

        while(cursor.moveToNext()) {
            listAssistidos.add(cursor.getInt(0));
        }

        return listAssistidos;
    }
}
