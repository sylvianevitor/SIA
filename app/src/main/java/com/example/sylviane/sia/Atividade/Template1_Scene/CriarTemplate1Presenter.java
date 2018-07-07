package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Template1;

import java.util.List;

/**
 * Created by mariana on 01/05/18.
 */

public class CriarTemplate1Presenter implements CriarTemplate1View.Presenter{

    private static final int CODIGO_IMAGEM = 1;
    private static final int CODIGO_AUDIO = 2;
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;

    private Context context;
    private Atividade atividade;
    private CriarTemplate1View.View criarTemplate1View;

    CriarTemplate1Presenter(Context context, CriarTemplate1View.View criarTemplate1View) {
        this.context = context;
        this.criarTemplate1View = criarTemplate1View;
    }

    //Recebe os parâmetros do método onActivityResult e faz as verificações necessárias
    @Override
    public void verificaResultado(int requestCode, int resultCode, Intent data) {

//        int d = data.getIntExtra("id_imagem", 0);
//        Log.d("MARI verificaResultado", Integer.toString(d));

        switch (requestCode){
            case CODIGO_IMAGEM:
                if(Activity.RESULT_OK == resultCode){
                    //TODO: salvar este caminho da imagem no banco de dados
                    String caminhoDaImagem = getRealPathFromURI(data.getData());
                    criarTemplate1View.carregaImagem(caminhoDaImagem);
                }

                break;

            case CODIGO_AUDIO:
                if(Activity.RESULT_OK == resultCode){
                    //TODO: salvar este caminho do áudio no banco de dados
                    String caminhoDoAudio = getRealPathFromURI(data.getData());
                    criarTemplate1View.carregaAudio(caminhoDoAudio);
                }

                break;
        }

    }

    //Abre a biblioteca permitindo selecionar somente arquivos de imagem
    @Override
    public void selecionaImagem(int id) {

        Intent abreGaleria = new Intent();

        if (Build.VERSION.SDK_INT <= 19) {
            abreGaleria.setType("image/*");
            abreGaleria.setAction(Intent.ACTION_GET_CONTENT);
            abreGaleria.addCategory(Intent.CATEGORY_OPENABLE);
        } else {
            abreGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }

        criarTemplate1View.abreActivity(abreGaleria, CODIGO_IMAGEM, id);

    }

    //Abre a biblioteca permitindo selecionar somente arquivos de áudio
    @Override
    public void selecionaAudio(int id) {

        Intent abreBiblioteca = new Intent();

        if (Build.VERSION.SDK_INT <= 19) {
            abreBiblioteca.setType("audio/*");
            abreBiblioteca.setAction(Intent.ACTION_GET_CONTENT);
            abreBiblioteca.addCategory(Intent.CATEGORY_OPENABLE);
        } else {
            abreBiblioteca = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        }

        criarTemplate1View.abreActivity(abreBiblioteca, CODIGO_AUDIO, id);

    }

    @Override
    public Atividade getAtividade(int id_atividade) {
        AtividadeDAO atividadeDAO = new AtividadeDAO(context);
        atividade = atividadeDAO.getAtividadeId(id_atividade);
        return atividade;
    }

    @Override
    public void cadastrar(String pathAudio1, String pathAudio2, String pathAudio3, String pathImage1, String pathImage2, String pathImage3){

        Log.d("PATH1: ", pathAudio1 + " | " + pathImage1);
        Log.d("PATH2: ", pathAudio2 + " | " + pathImage2);
        Log.d("PATH3: ", pathAudio3 + " | " + pathImage3);


        Template1 template1 = new Template1();
        template1.setImage(pathImage1);
        template1.setAudio(pathAudio1);
        template1.setAtividade(atividade);

        Template1 template2 = new Template1();
        template2.setImage(pathImage2);
        template2.setAudio(pathAudio2);
        template2.setAtividade(atividade);

        Template1 template3 = new Template1();
        template3.setImage(pathImage3);
        template3.setAudio(pathAudio3);
        template3.setAtividade(atividade);

        Template1DAO template1DAO = new Template1DAO(context);
        boolean ok1 = template1DAO.adicionarAquivo(template1);
        boolean ok2 = template1DAO.adicionarAquivo(template2);
        boolean ok3 = template1DAO.adicionarAquivo(template3);

        criarTemplate1View.abrirMainActivity(ok1, ok2, ok3);
    }

    //pega o path(caminho do arquivo) da Uri retornada no onActivityResult
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Audio.Media.DATA };
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }




}
