package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import com.example.sylviane.sia.Relatorios.RelatoriosActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.ExecucaoDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Execucao;
import com.example.sylviane.sia.persist.model.Template1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by sylviane on 12/05/18.
 */

public class ExecutarTemplate1Presenter {
    ExecutarTemplate1View executarTemplate1View;

    Execucao execucao = new Execucao();

    Atividade atividade;
    int id_atividade;
    Context contexto;
    private AudioManager mAudioManager;

    public ExecutarTemplate1Presenter(ExecutarTemplate1View executarTemplate1View, Context contexto, int id_atividade){
        this.executarTemplate1View = executarTemplate1View;
        this.contexto = contexto;
        this.id_atividade = id_atividade;
        AtividadeDAO atividadeDAO = new AtividadeDAO(contexto);
        atividade = atividadeDAO.getAtividadeId(id_atividade);
    }

    public List<Bitmap> load_image(){
        Template1DAO template1DAO = new Template1DAO(contexto);
        List <Bitmap> imagemBitmap = new ArrayList<Bitmap>();
        List<Template1> arquivos = template1DAO.getArquivos(atividade);

        for (int i =0; i < 3; i++) {
            String PathImage = arquivos.get(i).getImage();
            Log.d("path da imagem", PathImage);
            File imgFile = new File(PathImage);
            if (imgFile.exists()) {
                imagemBitmap.add(i, BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
            }
        }
        return imagemBitmap;
    }

    public List<MediaPlayer> load_audio() {
        Template1DAO template1DAO = new Template1DAO(contexto);
        List <MediaPlayer> audioList = new ArrayList<MediaPlayer>();
        List<Template1> arquivos = template1DAO.getArquivos(atividade);
        MediaPlayer mMediaPlayer = new MediaPlayer();

        for (int i =0; i< 3; i++) {
            String PathAudio = arquivos.get(1).getAudio();
            Log.d("path do audio", PathAudio);
            mMediaPlayer = MediaPlayer.create(contexto, Uri.parse(PathAudio));
            audioList.add(i,mMediaPlayer);
        }
        return audioList;
    }

    public void sair(int pontuacao, int[]assistidos, Context contexto, int atividade_id){
        Intent abrirFeedback = new Intent(contexto, RelatoriosActivity.class);
        abrirFeedback.putExtra("pontos", pontuacao);
        gravar_execucao(pontuacao, assistidos,atividade_id);
        contexto.startActivity(abrirFeedback);
    }

    public static int getRandomIndex(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max menor que minimo");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public void gravar_execucao(int pontuacao, int[]assistidos, int atividade_id){
        execucao.setId_atividade(atividade_id);
        //execucao.setHora();
        //execucao.setData();
        float percentual = pontuacao * 100/30;
        execucao.setPerc_acertos(percentual);
        execucao.setPontos((float) pontuacao);
        //execucao.setTempo();
        //execucao.setId_assistido(assistidos);
        ExecucaoDAO execucaoDAO = new ExecucaoDAO(contexto);
        execucaoDAO.insert(execucao);
    }
}
