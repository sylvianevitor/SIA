package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Relatorios.RelatoriosActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Template1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sylviane on 12/05/18.
 */

public class ExecutarTemplate1Activity extends AppCompatActivity implements ExecutarTemplate1View {
    ExecutarTemplate1Presenter executarTemplate1Presenter;
    int exec = 0;
    MediaPlayer mp;
    int pontuacao = 100;
    int id_atividade;

    @BindView(R.id.imageButton1)
    ImageButton image1;
    @BindView(R.id.imageButton2)
    ImageButton image2;
    @BindView(R.id.imageButton3)
    ImageButton image3;
    @BindView(R.id.btnAudio)
    Button audio;
    @BindView(R.id.btnSair)
    Button sair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_template1);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        id_atividade = intent.getIntExtra("id_atividade",0);

        executarTemplate1Presenter = new ExecutarTemplate1Presenter(this, this, id_atividade);
        load_info();

    }

    @OnClick(R.id.btnAudio)
    public void play() {
        mp = MediaPlayer.create(this, R.raw.sound);
        mp.start();
    }

    @OnClick(R.id.imageButton1)
    public void selectImage1() {
        //desabilitar click de imagem ja selecionada
        image1.setClickable(false);
        selecao();
    }

    @OnClick(R.id.imageButton2)
    public void selectImage2() {
        image2.setClickable(false);
        selecao();
    }

    @OnClick(R.id.imageButton3)
    public void selectImage3() {
        image3.setClickable(false);
        selecao();
    }

    @OnClick(R.id.btnSair)
    public void fim() {
        executarTemplate1Presenter.sair(pontuacao, this);
    }

    @Override
    public void load_info() {
        //Carregar conteudo
        MediaPlayer audioFile = executarTemplate1Presenter.load_audio();
        if (audioFile != null) {
            mp = audioFile;
        } else {
            mp = MediaPlayer.create(this, R.raw.sound);
        }
        Bitmap imageFile = executarTemplate1Presenter.load_image();
        if (imageFile != null) {
            image1.setImageBitmap(imageFile);
        }
    }

    @Override
    public void selecao(){
        executarTemplate1Presenter.selecao_imagem();
        exec++;
        //nao ha mais imagens para selecionar
        if (exec == 3) {
            fim();
        }
        image1.setImageResource(R.drawable.ic_menu_profile);
        //carregar proximo audio
        load_info();
    }


}