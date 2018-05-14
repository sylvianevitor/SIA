package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.AtividadeTemplate1;
import com.example.sylviane.sia.persist.model.Template1Arquivo;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;

public class AtividadeTemplate1DAO extends  AtividadeDAO{

    public AtividadeTemplate1DAO(Context context) {
        super(context);
    }

    public boolean insertArquivo(Template1Arquivo arquivo) {

        this.db = this.database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("audio", arquivo.getAudio());
        values.put("imagem", arquivo.getImagem());
        values.put("id_atividade", arquivo.getId_atividade());

        long result = this.db.insert(TABLE, null, values);
        this.db.close();

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<Atividade> getArquivo() {

        ArrayList<Atividade>  list = new ArrayList<Atividade>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "descricao", "dificuldade", "id_proprietario", "dt_cadastro", "nr_execucoes", "id_tema", "tipo_atividade"};

        Cursor cursor = db.query(TABLE, campos, null, null, null, null, "descricao");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Atividade atividade = new Atividade();
                atividade.setId(cursor.getInt(0));
                atividade.setNome(cursor.getString(1));
                atividade.setDescricao(cursor.getString(2));
                atividade.setDificuldade(cursor.getInt(3));
                atividade.setId_proprietario(cursor.getInt(4));
                atividade.setDt_cadastro(cursor.getString(5));
                atividade.setNr_execucoes(cursor.getInt(6));
                atividade.setId_tema(cursor.getInt(7));
                atividade.setTipo_atividade(cursor.getInt(8));
                list.add(atividade);
            }
        }

        db.close();

        return list;
    }
}
