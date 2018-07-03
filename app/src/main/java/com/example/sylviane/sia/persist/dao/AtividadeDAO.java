package com.example.sylviane.sia.persist.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.util.Database;

import java.util.ArrayList;

public class AtividadeDAO {

    protected final String TABLE = "atividade";

    protected SQLiteDatabase db;
    protected Database database;

    public AtividadeDAO(Context context) {
        database = new Database(context);
    }

    public Atividade insert(Atividade atividade) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", atividade.getNome());
        values.put("objetivo", atividade.getObjetivo());
        values.put("descricao", atividade.getDescricao());
        values.put("dificuldade", atividade.getDificuldade());
        values.put("id_proprietario", atividade.getId_proprietario());
        values.put("dt_cadastro", atividade.getDt_cadastro());
        values.put("nr_execucoes", atividade.getNr_execucoes());
        values.put("id_tema", atividade.getId_tema());
        values.put("tipo_atividade", atividade.getTipo_atividade());
        values.put("ativa", Atividade.SITUACAO_ATIVA);

        int result = (int)db.insert(TABLE, null, values);
        db.close();

        if(result == -1)
            return null;

        atividade.setId(result);

        return atividade;
    }

    public Atividade update(Atividade atividade) {

        db = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nome", atividade.getNome());
        values.put("objetivo", atividade.getObjetivo());
        values.put("descricao", atividade.getDescricao());
        values.put("dificuldade", atividade.getDificuldade());
        values.put("id_proprietario", atividade.getId_proprietario());
        values.put("dt_cadastro", atividade.getDt_cadastro());
        values.put("nr_execucoes", atividade.getNr_execucoes());
        values.put("id_tema", atividade.getId_tema());
        values.put("tipo_atividade", atividade.getTipo_atividade());
        values.put("ativa", atividade.getAtiva());

        long result = db.update(TABLE, values, "id=?", new String[] { Integer.toString(atividade.getId()) });
        db.close();

        if(result == -1)
            return null;

        return atividade;
    }

    public Atividade getAtividadeId(int id) {

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "objetivo", "descricao", "dificuldade", "id_proprietario", "dt_cadastro", "nr_execucoes", "id_tema", "tipo_atividade", "ativa"};

        Cursor cursor = db.query(TABLE, campos, "id=?", new String[] { Integer.toString(id) }, null, null, null);

        if(cursor == null || cursor.getCount() <= 0) {

            db.close();
            return null;
        }

        cursor.moveToFirst();
        Atividade atividade = new Atividade();
        atividade.setId(cursor.getInt(0));
        atividade.setNome(cursor.getString(1));
        atividade.setObjetivo(cursor.getString(2));
        atividade.setDescricao(cursor.getString(3));
        atividade.setDificuldade(cursor.getInt(4));
        atividade.setId_proprietario(cursor.getInt(5));
        atividade.setDt_cadastro(cursor.getString(6));
        atividade.setNr_execucoes(cursor.getInt(7));
        atividade.setId_tema(cursor.getInt(8));
        atividade.setTipo_atividade(cursor.getInt(9));
        atividade.setAtiva(cursor.getInt(10));

        db.close();
        return atividade;
    }

    public ArrayList<Atividade> getAtividade() {

        ArrayList<Atividade>  list = new ArrayList<Atividade>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "objetivo", "descricao", "dificuldade", "id_proprietario", "dt_cadastro", "nr_execucoes", "id_tema", "tipo_atividade", "ativa"};

        Cursor cursor = db.query(TABLE, campos, "ativa=?", new String[] { Integer.toString(Atividade.SITUACAO_ATIVA) }, null, null, "descricao");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Atividade atividade = new Atividade();
                atividade.setId(cursor.getInt(0));
                atividade.setNome(cursor.getString(1));
                atividade.setObjetivo(cursor.getString(2));
                atividade.setDescricao(cursor.getString(3));
                atividade.setDificuldade(cursor.getInt(4));
                atividade.setId_proprietario(cursor.getInt(5));
                atividade.setDt_cadastro(cursor.getString(6));
                atividade.setNr_execucoes(cursor.getInt(7));
                atividade.setId_tema(cursor.getInt(8));
                atividade.setTipo_atividade(cursor.getInt(9));
                atividade.setAtiva(cursor.getInt(10));
                list.add(atividade);
            }
        }

        db.close();

        return list;
    }

    public ArrayList<Atividade> getAtividadeByTema(int idTema) {

        ArrayList<Atividade>  list = new ArrayList<Atividade>();

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome", "objetivo", "descricao", "dificuldade", "id_proprietario", "dt_cadastro", "nr_execucoes", "id_tema", "tipo_atividade", "ativa"};

        Cursor cursor = db.query(TABLE, campos, "id_tema=? and ativa=?", new String[] { Integer.toString(idTema), Integer.toString(Atividade.SITUACAO_ATIVA) }, null, null, "descricao");

        if(cursor!=null) {

            while(cursor.moveToNext()) {
                Atividade atividade = new Atividade();
                atividade.setId(cursor.getInt(0));
                atividade.setNome(cursor.getString(1));
                atividade.setObjetivo(cursor.getString(2));
                atividade.setDescricao(cursor.getString(3));
                atividade.setDificuldade(cursor.getInt(4));
                atividade.setId_proprietario(cursor.getInt(5));
                atividade.setDt_cadastro(cursor.getString(6));
                atividade.setNr_execucoes(cursor.getInt(7));
                atividade.setId_tema(cursor.getInt(8));
                atividade.setTipo_atividade(cursor.getInt(9));
                atividade.setAtiva(cursor.getInt(10));
                list.add(atividade);
            }
        }

        db.close();

        return list;
    }
}
