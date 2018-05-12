package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.sylviane.sia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sylviane on 12/05/18.
 */

public class ExecutarTemplate1Activity extends AppCompatActivity implements ExecutarTemplate1View{
    ExecutarTemplate1Presenter executarTemplate1Presenter;
    //AtividadeDAO atividade = new AtididadeDAO;
    @BindView(R.id.imageButton1) ImageButton image1;
    @BindView(R.id.imageButton2) ImageButton image2;
    @BindView(R.id.imageButton3) ImageButton image3;
    @BindView(R.id.btnAudio) Button audio;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        /*//buscar imagem no banco
        //converter bitmap para drawable
        //Drawable d = new BitmapDrawable(getResources(), bitmap);

        image1.setImageResource(R.drawable.ic_menu_profile); //trocar imagem
        //buscar imagem no banco
        image2.setImageResource(R.drawable.ic_menu_profile); //trocar imagem
        //buscar imagem no banco
        image3.setImageResource(R.drawable.ic_menu_profile); //trocar imagem*/

        setContentView(R.layout.activity_exec_template1);

        //verificar thread
        Log.i("AsyncTask", "Thread: " + Thread.currentThread().getName());
        //thread para acesso ao banco
        chamarAsyncTask();

        ButterKnife.bind(this);
        executarTemplate1Presenter = new ExecutarTemplate1Presenter(this);
    }

    //classe auxiliar busca imagem no banco
    public class Auxiliar{
        public Bitmap baixarImagem(){
            Bitmap imagem;
            //metodo do DAO

            return imagem;
        }
    }
    // chamar criacao de thread
    private void chamarAsyncTask(){
        ImagemBD downloadImage = new ImagemBD();
        //verificar thread
        Log.i("AsyncTask", "AsyncTask sendo chamado Thread: " + Thread.currentThread().getName());
        downloadImage.execute();
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
            imagemBitmap = Auxiliar.baixarImagem(params[0]);
            return imagemBitmap;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap){
            if(bitmap!=null) {
                image1.setImageBitmap(bitmap);
                Log.i("AsyncTask", "Exibindo Bitmap Thread: " + Thread.currentThread().getName());
            }else{
                Log.i("AsyncTask", "Erro ao baixar a imagem " + Thread.currentThread().getName());
            }
            Log.i("AsyncTask", "Tirando ProgressDialog da tela Thread: " + Thread.currentThread().getName());
            load.dismiss();
        }

    }

    @OnClick(R.id.btnAudio)
    public void load_info(){executarTemplate1Presenter.load_info();}

    @Override
    public void load_audio(){
        //buscar audio no banco de dados
        //executar
        //MediaPlayer mp = MediaPlayer.create(this,R.raw.sound);
    }
}
