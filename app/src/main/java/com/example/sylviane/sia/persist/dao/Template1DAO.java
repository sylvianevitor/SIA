package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Template1;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;
import java.util.List;

public class Template1DAO {

    private final String TABLE = "template1";

    private SQLiteDatabase db;
    private Database database;
    private Context context;

    public Template1DAO(Context context) {

        this.context = context;
        database = new Database(context);
    }

    public List<Template1> getArquivosAll() {

        ArrayList<Template1>  list = new ArrayList<Template1>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "audio", "imagem", "id_atividade"};

        Cursor cursor = db.query(TABLE, campos, null, null , null, null, null);

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Template1 arquivo = new Template1();
                arquivo.setId(cursor.getInt(0));
                arquivo.setAudio(cursor.getString(1));
                arquivo.setImage(cursor.getString(2));
                arquivo.setAtividade(null);
                list.add(arquivo);
            }
        }

        db.close();

        return list;
    }

    public List<Template1> getArquivos(Atividade atividade) {

        ArrayList<Template1>  list = new ArrayList<Template1>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "audio", "imagem", "id_atividade"};

        Cursor cursor = db.query(TABLE, campos, "id_atividade=?", new String[] { Integer.toString(atividade.getId()) }, null, null, null);

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Template1 arquivo = new Template1();
                arquivo.setId(cursor.getInt(0));
                arquivo.setAudio(cursor.getString(1));
                arquivo.setImage(cursor.getString(2));
                arquivo.setAtividade(atividade);
                list.add(arquivo);
            }
        }

        db.close();

        return list;
    }

    public boolean adicionarAquivo(Template1 arquivo) {

        this.db = this.database.getWritableDatabase();

        Log.d("LUAN", Integer.toString(arquivo.getAtividade().getId()));

        ContentValues values = new ContentValues();
        values.put("audio", arquivo.getAudio());
        values.put("imagem", arquivo.getImage());
        values.put("id_atividade", arquivo.getAtividade().getId());
        Log.d("Luan", Integer.toString( arquivo.getAtividade().getId()));

        long result = this.db.insert(TABLE, null, values);
        this.db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
