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
        values.put("medicamento", assistido.getMedicamentos());
        values.put("imagem", assistido.getImagem());

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

        String[] campos = {"id", "nome_completo", "apelido", "dt_nasc", "responsavel", "telefone", "informacoes", "medicamento", "imagem"};

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
                assistido.setMedicamentos(cursor.getString(7));
                assistido.setImagem(cursor.getString(8));
                list.add(assistido);
            }
        }

        db.close();

        return list;
    }

    public Assistido getAssistidoId(int id) {

        db = database.getReadableDatabase();

        String[] campos = {"id", "nome_completo", "apelido", "dt_nasc", "responsavel", "telefone", "informacoes", "medicamento", "imagem"};

        Cursor cursor = db.query(TABLE, campos, "id=?", new String[] { Integer.toString(id) }, null, null, "apelido");

        if(cursor!=null) {
            db.close();
            return null;
        }

        cursor.moveToFirst();
        Assistido assistido = new Assistido();
        assistido.setId(cursor.getInt(0));
        assistido.setNome_completo(cursor.getString(1));
        assistido.setApelido(cursor.getString(2));
        assistido.setDt_nasc(cursor.getString(3));
        assistido.setResponsavel(cursor.getString(4));
        assistido.setTelefone(cursor.getString(5));
        assistido.setInformacoes(cursor.getString(6));
        assistido.setMedicamentos(cursor.getString(7));
        assistido.setImagem(cursor.getString(8));

        db.close();

        return assistido;
    }

    // Popular campos automaticamente
    public void popularAssistidos() {
        Assistido assistido = new Assistido();

        assistido.setNome_completo("Ricardo Pereira");
        assistido.setApelido("Ricardo");
        assistido.setDt_nasc("23/02/2010");
        assistido.setResponsavel("Ana Paula Pereira");
        assistido.setTelefone("(14)994929221");
        assistido.setInformacoes("Alérgico aos remédios XXX");
        assistido.setMedicamentos("Remedio 1");
        this.insert(assistido);

        assistido.setNome_completo("Carol Silva");
        assistido.setApelido("Carol");
        assistido.setDt_nasc("20/01/2001");
        assistido.setResponsavel("Maria Silva");
        assistido.setTelefone("(14)994929299");
        assistido.setInformacoes("Alérgico aos remédios KKK");
        assistido.setMedicamentos("Remedio 2");
        this.insert(assistido);

        assistido.setNome_completo("Sebastião Cardoso");
        assistido.setApelido("Bastião");
        assistido.setDt_nasc("27/09/1991");
        assistido.setResponsavel("António Cardoso");
        assistido.setTelefone("(14)994929991");
        assistido.setInformacoes("Alérgico aos remédios JJJ");
        assistido.setMedicamentos("Remedio 4");
        this.insert(assistido);
    }
}
