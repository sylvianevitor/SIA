package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Relatorios.RelatoriosActivity;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Template1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sylviane on 12/05/18.
 */
public class ExecutarTemplate1Activity extends AppCompatActivity implements ExecutarTemplate1View {
    ExecutarTemplate1Presenter executarTemplate1Presenter;
    MediaPlayer mp = new MediaPlayer();
    List<String> audioFiles;
    int pontuacao = 0;
    int id_atividade;
    ArrayList<Integer> id_assistidos;
    int index = -1;
    long startTime, endTime, elapsedTime;

    @BindView(R.id.imageButton1)
    ImageButton image1;
    @BindView(R.id.imageButton2)
    ImageButton image2;
    @BindView(R.id.imageButton3)
    ImageButton image3;
    @BindView(R.id.btnAudio)
    Button audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_template1);

        ButterKnife.bind(this);
        Intent intent = getIntent();
        id_atividade = intent.getIntExtra("id_atividade", 0);
        id_assistidos = intent.getIntegerArrayListExtra("assistido_id");
        Log.d("LISTA IDs", String.valueOf(id_assistidos));
        executarTemplate1Presenter = new ExecutarTemplate1Presenter(this, this, id_atividade);
        load_info();
        startTime = System.currentTimeMillis();
        setTitle(executarTemplate1Presenter.getNome());
    }

    @OnClick(R.id.btnAudio)
    public void play() {
        Log.d("Antigo audio", Integer.toString(index));
        releaseMediaPlayer();
        System.out.println(audioFiles);
        mp = MediaPlayer.create(this, Uri.parse(audioFiles.get(index)));
        mp.start();

    }

    @OnClick(R.id.imageButton1)
    public void selectImage1() {
        selecao(0);
    }

    @OnClick(R.id.imageButton2)
    public void selectImage2() {
        selecao(1);
    }

    @OnClick(R.id.imageButton3)
    public void selectImage3() {
        selecao(2);
    }

    @Override
    public void fim() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        executarTemplate1Presenter.sair(pontuacao,id_assistidos, this, id_atividade, elapsedTime);
        finish();
    }

    @Override
    public void load_info() {
        //Carregar conteudo
        audioFiles = executarTemplate1Presenter.load_audio();
        if (audioFiles != null) {
            index = executarTemplate1Presenter.getRandomIndex(0, 2);
//            mp = MediaPlayer.create(this, Uri.parse(audioFiles.get(index)));
            Log.d("Audio recebido", Integer.toString(index));
        } else {
            //mp = MediaPlayer.create(this, R.raw.sound);
        }
        List<Bitmap> imageFiles = executarTemplate1Presenter.load_image();
        if (imageFiles != null) {
            image1.setImageBitmap(imageFiles.get(0));
            image2.setImageBitmap(imageFiles.get(1));
            image3.setImageBitmap(imageFiles.get(2));
        }
    }


    @Override
    public void selecao(int i) {
        Log.d("Audio tocado", Integer.toString(index));
        Log.d("Imagem escolhida", Integer.toString(i));
        Toast toast;
        if (index == i) {
            pontuacao += 10; // acertou
            switch (index){
                case 0:
                    index = 1;
                    Log.d("Novo audio", Integer.toString(index));
                    image1.setClickable(false);
                    break;
                case 1:
                    index = 2;
                    Log.d("Novo audio", Integer.toString(index));
                    image2.setClickable(false);
                    break;
                case 2:
                    index = 0;
                    Log.d("Novo audio", Integer.toString(index));
                    image3.setClickable(false);
                    break;
            }
            toast = Toast.makeText(ExecutarTemplate1Activity.this, "PARABENS, VOCE ACERTOU!", Toast.LENGTH_LONG);
            toast.show();
        }else{
            toast = Toast.makeText(ExecutarTemplate1Activity.this, "TENTE NOVAMENTE!", Toast.LENGTH_LONG);
            toast.show();
        }
        if(pontuacao == 30){fim();} // acertou todas

    }

    private void releaseMediaPlayer() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

}