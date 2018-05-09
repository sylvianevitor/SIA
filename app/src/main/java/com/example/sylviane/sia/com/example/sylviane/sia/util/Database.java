package com.example.sylviane.sia.com.example.sylviane.sia.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "sia.db";
    private static final int VERSAO = 1;

    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.getSQLCreateTableAtividade());
        db.execSQL(this.getSQLCreateTableAssistido());
        db.execSQL(this.getSQLCreateTableTema());
        db.execSQL(this.getSQLCreateTableFormacao());
        db.execSQL(this.getSQLCreateTableProfissional());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private String getSQLCreateTableAtividade() {
        String sql = "CREATE TABLE atividade ( " +
                "id integer primary key autoincrement, " +
                "descricao text, " +
                "id_proprietario integer, " +
                "dt_cadastro text, " +
                "nr_execucoes integer, " +
                "id_tema integer, " +
                "tipo_atividade integer )";
        return sql;
    }

    private String getSQLCreateTableAssistido() {
        String sql = "CREATE TABLE assitido ( " +
                "id integer primary key autoincrement, " +
                "nome_completo text, " +
                "apelido text, " +
                "dt_nasc text, " +
                "responsavel text, " +
                "telefone text, " +
                "informacoes text )";
        return sql;
    }

    private String getSQLCreateTableTema() {
        String sql = "CREATE TABLE tema ( " +
                "id integer primary key autoincrement, " +
                "tema text )";
        return sql;
    }

    private String getSQLCreateTableProfissional() {
        String sql = "CREATE TABLE profissional ( " +
                "id integer primary key autoincrement, " +
                "nome_completo text, " +
                "id_formacao integer )";
        return sql;
    }

    private String getSQLCreateTableFormacao() {
        String sql = "CREATE TABLE formacao ( " +
                "id integer primary key autoincrement, " +
                "formacao text )";
        return sql;
    }
}