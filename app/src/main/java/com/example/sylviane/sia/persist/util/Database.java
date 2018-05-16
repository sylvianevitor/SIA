package com.example.sylviane.sia.persist.util;

import android.companion.AssociationRequest;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

public class Database extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "sia.db";
    private static final int VERSAO = 1;

    private Context context;

    public Database(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.getSQLCreateTableAtividade());
        db.execSQL(this.getSQLCreateTableAssistido());
        db.execSQL(this.getSQLCreateTableTema());
        db.execSQL(this.getSQLCreateTableFormacao());
        db.execSQL(this.getSQLCreateTableProfissional());
        db.execSQL(this.getSQLCreateTableTemplate1());

        this.popularAssistidos();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Popular campos automaticamente
    private void popularAssistidos() {
        AssistidoDAO assistidoDAO = new AssistidoDAO(this.context);
        Assistido assistido = new Assistido();

        assistido.setNome_completo("Ricardo Pereira");
        assistido.setApelido("Ricardo");
        assistido.setDt_nasc("23/02/2010");
        assistido.setResponsavel("Ana Paula Pereira");
        assistido.setTelefone("(14)994929221");
        assistido.setInformacoes("Alérgico aos remédios XXX");
        assistidoDAO.insert(assistido);

        assistido.setNome_completo("Carol Silva");
        assistido.setApelido("Carol");
        assistido.setDt_nasc("20/01/2001");
        assistido.setResponsavel("Maria Silva");
        assistido.setTelefone("(14)994929299");
        assistido.setInformacoes("Alérgico aos remédios KKK");
        assistidoDAO.insert(assistido);

        assistido.setNome_completo("Sebastião Cardoso");
        assistido.setApelido("Bastião");
        assistido.setDt_nasc("27/09/1991");
        assistido.setResponsavel("António Cardoso");
        assistido.setTelefone("(14)994929991");
        assistido.setInformacoes("Alérgico aos remédios JJJ");
        assistidoDAO.insert(assistido);
    }

    private String getSQLCreateTableAtividade() {
        String sql = "CREATE TABLE atividade ( " +
                "id integer primary key autoincrement, " +
                "nome text, " +
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
                "tema text, " +
                "imagem text )";
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

    private String getSQLCreateTableTemplate1() {
        String sql = "CREATE TABLE template1 ( " +
                "id integer primary key autoincrement, " +
                "id_atividade integer, " +
                "audio text, " +
                "imagem text )";
        return sql;
    }
}
