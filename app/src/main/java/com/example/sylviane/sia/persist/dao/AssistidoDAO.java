package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;

public class AssistidoDAO {

    private final String TABLE = "assistido";

    private SQLiteDatabase db;
    private Database database;

    public AssistidoDAO(Context context) {
        database = new Database(context);
    }

    public boolean insert(Assistido assistido) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome_completo", assistido.getNome_completo());
        values.put("apelido", assistido.getApelido());
        values.put("dt_nasc", assistido.getDt_nasc());
        values.put("responsavel", assistido.getResponsavel());
        values.put("telefone", assistido.getTelefone());
        values.put("informacoes", assistido.getInformacoes());

        long result = db.insert(TABLE, null, values);
        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Assistido> getAssistidos() {

        ArrayList<Assistido>  list = new ArrayList<Assistido>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome_completo", "apelido", "dt_nasc", "responsavel", "telefone", "informacoes"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "apelido");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Assistido assistido = new Assistido();
                assistido.setId(cursor.getInt(0));
                assistido.setNome_completo(cursor.getString(1));
                assistido.setApelido(cursor.getString(2));
                assistido.setDt_nasc(cursor.getString(3));
                assistido.setResponsavel(cursor.getString(4));
                assistido.setTelefone(cursor.getString(5));
                assistido.setInformacoes(cursor.getString(6));
                list.add(assistido);
            }
        }

        db.close();

        return list;
    }
}
