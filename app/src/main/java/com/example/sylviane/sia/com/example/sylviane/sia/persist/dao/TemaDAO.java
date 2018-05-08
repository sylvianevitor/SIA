package com.example.sylviane.sia.com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sylviane.sia.com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.com.example.sylviane.sia.util.Database;

import java.util.ArrayList;

public class TemaDAO {

    private final String TABLE = "tema";

    private SQLiteDatabase db;
    private Database database;

    public TemaDAO(Context context) {
        database = new Database(context);
    }

    public boolean insert(Tema tema) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("tema", tema.getTema());

        long result = db.insert(TABLE, null, values);
        db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Tema> getTemas() {

        ArrayList<Tema>  list = new ArrayList<Tema>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "tema"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "tema");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Tema tema = new Tema();
                tema.setId(cursor.getInt(0));
                tema.setTema(cursor.getString(1));
                list.add(tema);
            }
        }

        db.close();

        return list;
    }
}
