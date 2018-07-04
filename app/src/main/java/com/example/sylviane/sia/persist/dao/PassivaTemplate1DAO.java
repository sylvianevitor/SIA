package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.PassivaTemplate1;
import com.example.sylviane.sia.persist.model.Template1;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;
import java.util.List;

public class PassivaTemplate1DAO {

    private final String TABLE = "passivatemplate1";

    private SQLiteDatabase db;
    private Database database;
    private Context context;

    public PassivaTemplate1DAO(Context context) {

        this.context = context;
        database = new Database(context);
    }

    public List<PassivaTemplate1> getArquivos(Atividade atividade) {

        ArrayList<PassivaTemplate1>  list = new ArrayList<PassivaTemplate1>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "video", "id_atividade"};

        Cursor cursor = db.query(TABLE, campos, "id_atividade=?", new String[] { Integer.toString(atividade.getId()) }, null, null, null);

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                PassivaTemplate1 arquivo = new PassivaTemplate1();
                arquivo.setId(cursor.getInt(0));
                arquivo.setVideo(cursor.getString(1));
                arquivo.setAtividade(atividade);
                list.add(arquivo);
            }
        }

        db.close();

        return list;
    }

    public boolean adicionarAquivo(PassivaTemplate1 arquivo) {

        this.db = this.database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("video", arquivo.getVideo());
        values.put("id_atividade", arquivo.getAtividade().getId());

        long result = this.db.insert(TABLE, null, values);
        this.db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
