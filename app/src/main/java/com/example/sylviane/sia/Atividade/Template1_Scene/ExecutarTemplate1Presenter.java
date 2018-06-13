package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.provider.MediaStore;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Relatorios.RelatoriosActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Execucao;
import com.example.sylviane.sia.persist.model.Template1;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by sylviane on 12/05/18.
 */

public class ExecutarTemplate1Presenter {
    ExecutarTemplate1View executarTemplate1View;
    Template1DAO template1DAO;
    AtividadeDAO atividadeDAO;
    Execucao execucao;
    ExecucaoDAO execucaoDAO;
    int id_atividade = 0;
    //Atividade atividade = atividadeDAO.getAtividadeId(id_atividade);
    Context contexto;

    public ExecutarTemplate1Presenter(ExecutarTemplate1View executarTemplate1View, Context contexto){
        this.executarTemplate1View = executarTemplate1View;
        this.contexto = contexto;
    }

    public Bitmap load_image(){

        Bitmap imagemBitmap = null;
        /*List<Template1> arquivos = template1DAO.getArquivos(atividade);
        String PathImage = arquivos.get(id_atividade).getImage();
        File imgFile = new  File(PathImage);
        if(imgFile.exists()){
            imagemBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        }*/
        return imagemBitmap;
    }

    public MediaPlayer load_audio(){
        MediaPlayer audio = new MediaPlayer();
        /*List<Template1> arquivos = template1DAO.getArquivos(atividade);
        String PathAudio = arquivos.get(id_atividade).getAudio();
        if(PathAudio != null){
            try {
                audio.setDataSource(PathAudio);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        return audio;
    }

    public void selecao_imagem(){
        //verificar se imagem eh a mesma do banco
        //atualizar pontuacao
    }
    public void sair(int pontuacao, Context contexto){
        Intent abrirFeedback = new Intent(contexto, RelatoriosActivity.class);
        abrirFeedback.putExtra("pontos", pontuacao);
        //gravar_execucao(pontuacao);
        contexto.startActivity(abrirFeedback);
    }

    /*public void gravar_execucao(int pontuacao){
        execucao.setId_atividade(id_atividade);
        execucao.setHora();
        execucao.setData();
        execucao.setPerc_acertos(pontuacao);
        execucao.setTempo();
    }*/
}
