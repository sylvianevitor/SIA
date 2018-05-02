package com.example.sylviane.sia.com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.com.example.sylviane.sia.persist.model.Tema;
import com.example.sylviane.sia.com.example.sylviane.sia.util.Database;

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
}
