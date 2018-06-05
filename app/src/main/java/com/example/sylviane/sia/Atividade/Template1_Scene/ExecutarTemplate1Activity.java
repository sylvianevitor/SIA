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

public class ExecutarTemplate1Activity extends AppCompatActivity implements ExecutarTemplate1View{
    ExecutarTemplate1Presenter executarTemplate1Presenter;
    Template1DAO template1DAO;
    AtividadeDAO atividadeDAO;
    //int id_atividade = 0; //id fake
    MediaStore.Audio sound;
    int pontuacao = 100;

    //Atividade atividade = atividadeDAO.getAtividadeId(id_atividade);
    @BindView(R.id.imageButton1) ImageButton image1;
    @BindView(R.id.imageButton2) ImageButton image2;
    @BindView(R.id.imageButton3) ImageButton image3;
    @BindView(R.id.btnAudio) Button audio;
    @BindView(R.id.btnSair) Button sair;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_template1);


        ButterKnife.bind(this);
        executarTemplate1Presenter = new ExecutarTemplate1Presenter(this);

        //Carregar conteudo
        executarTemplate1Presenter.load_info();
    }

    //thread para buscar imagem no banco
    public class ImagemBD extends AsyncTask<String,Void,Bitmap> {
        ProgressDialog load;
        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = ProgressDialog.show(ExecutarTemplate1Activity.this, "Por favor aguarde ...",
                    "Carregando Atividade ...");
        }
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap imagemBitmap = null;
            Log.i("AsyncTask", "Baixando a imagem Thread: " + Thread.currentThread().getName());
           // List<Template1> arquivos = template1DAO.getArquivos(atividade);
            //imagemBitmap = arquivos.getImage();
            return imagemBitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(bitmap!=null) {
                //image1.setImageBitmap(bitmap); //seta imagem nova
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }

    }

    //thread para buscar audio no banco
    public class AudioBD extends AsyncTask<String,Void,MediaStore.Audio> {
        ProgressDialog load;
        @Override
        protected void onPreExecute(){
            Log.i("AsyncTask", "Exibindo ProgressDialog na tela Thread: " + Thread.currentThread().getName());
            load = ProgressDialog.show(ExecutarTemplate1Activity.this, "Por favor aguarde ...",
                    "Carregando Audio ...");
        }
        @Override
        protected MediaStore.Audio doInBackground(String... params) {
            MediaStore.Audio audio = null;
            Log.i("AsyncTask", "Baixando o audio Thread: " + Thread.currentThread().getName());
            //List<Template1> arquivos = template1DAO.getArquivos(atividade);
            //imagemBitmap = arquivos.getaudio();
            return audio;
        }
        @Override
        protected void onPostExecute(MediaStore.Audio audio){
            if(audio!=null) {
               sound = audio;//seta novo audio
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar o audio " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }

    }


    @OnClick(R.id.btnAudio)
    public void play(){
        MediaPlayer mp = MediaPlayer.create(this,R.raw.sound);
        mp.start();
    }

    @OnClick(R.id.imageButton1)
    public void selectImage1(){executarTemplate1Presenter.selecao_imagem();}

    @OnClick(R.id.imageButton2)
    public void selectImage2(){executarTemplate1Presenter.selecao_imagem();}

    @OnClick(R.id.imageButton3)
    public void selectImage3(){executarTemplate1Presenter.selecao_imagem();}

    @OnClick(R.id.btnSair)
    public void sair(){executarTemplate1Presenter.sair();}

    @Override
    public void load_audio(){
        AudioBD downloadAudio = new AudioBD();
        downloadAudio.execute();
    }

    @Override
    public void load_imagem(){
        ImagemBD downloadImage = new ImagemBD();
        downloadImage.execute();
    }

    @Override
    public void selecao(){
        //verificar se imagem eh a mesma do banco
        //atualizar pontuacao
        image1.setImageResource(R.drawable.ic_menu_profile);
        image2.setImageResource(R.drawable.ic_menu_profile);
        image3.setImageResource(R.drawable.ic_menu_profile);
    }

    @Override
    public void fim(){
        Intent abrirFeedback = new Intent(ExecutarTemplate1Activity.this, RelatoriosActivity.class);
        abrirFeedback.putExtra("pontos", pontuacao);
        //salvar execucao no banco
        startActivity(abrirFeedback);
    }

}

