package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;

public class TemaDAO {

    private final String TABLE = "tema";

    private SQLiteDatabase db;
    private Database database;

    public TemaDAO(Context context) {
        database = new Database(context);
    }

    public Tema insert(Tema tema) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tema", tema.getTema());
        values.put("imagem", tema.getImagem());

        int result = (int) db.insert(TABLE, null, values);
        db.close();

        if(result == -1) {
            return null;
        }

        tema.setId(result);
        return tema;
    }

    public ArrayList<Tema> getTemas() {

        ArrayList<Tema>  list = new ArrayList<Tema>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "tema", "imagem"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "tema");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Tema tema = new Tema();
                tema.setId(cursor.getInt(0));
                tema.setTema(cursor.getString(1));
                tema.setImagem(cursor.getString(2));
                list.add(tema);
            }
        }

        db.close();

        return list;
    }
}
