package com.example.sylviane.sia.Atividade.Atividade_Passiva.CriarAtividadePassiva;

import android.app.Activity;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.persist.dao.PassivaTemplate1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.PassivaTemplate1;

/**
 * Created by Rafael Saito on 07/07/2018.
 */

public class CriarAtividadePassivaPresenter implements CriarAtividadePassivaView.Presenter{
    private static final int CODIGO_VIDEO = 1;

    private Context context;
    private Atividade atividade;
    private CriarAtividadePassivaView.View criarAtividadePassivaView;

    CriarAtividadePassivaPresenter(Context context, CriarAtividadePassivaView.View criarAtividadePassivaView) {
        this.context = context;
        this.criarAtividadePassivaView = criarAtividadePassivaView;
    }

    //Recebe os parâmetros do método onActivityResult e faz as verificações necessárias
    @Override
    public void verificaResultado(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CODIGO_VIDEO:
                if (Activity.RESULT_OK == resultCode) {
                    String caminhodoVideo = getRealPathFromURI(data.getData());
                    criarAtividadePassivaView.carregaVideo(caminhodoVideo);
                }
                break;
        }
    }

    //Abre a biblioteca permitindo selecionar somente arquivos de imagem
    @Override
    public void selecionaVideo() {

        Intent abreGaleria = new Intent();

        if (Build.VERSION.SDK_INT <= 19) {
            abreGaleria.setType("video/*");
            abreGaleria.setAction(Intent.ACTION_GET_CONTENT);
            abreGaleria.addCategory(Intent.CATEGORY_OPENABLE);
        } else {
            abreGaleria = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        }
        criarAtividadePassivaView.abreActivity(abreGaleria, CODIGO_VIDEO);
    }

    @Override
    public void cadastrar(String video) {
        PassivaTemplate1 passivaTemplate1 = new PassivaTemplate1();
        passivaTemplate1.setVideo(video);
        passivaTemplate1.setAtividade(atividade);
        PassivaTemplate1DAO passivaTemplate1DAO = new PassivaTemplate1DAO(context);
        boolean ok = passivaTemplate1DAO.adicionarAquivo(passivaTemplate1);

        criarAtividadePassivaView.abrirMainActivity(ok);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Video.Media.DATA};
        CursorLoader loader = new CursorLoader(context, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
