package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sylviane.sia.AtividadesDetail.AtividadesDetailActivity;
import com.example.sylviane.sia.Main_Scene.MainActivity;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.Template1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Template1;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.sylviane.sia.Atividade.Template1_Scene.CriarTemplate1Presenter.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE;

public class CriarTemplate1Activity extends AppCompatActivity implements CriarTemplate1View.View{

    @BindView(R.id.imageButton1)ImageButton imageButton1;
    @BindView(R.id.imageButton2)ImageButton imageButton2;
    @BindView(R.id.imageButton3)ImageButton imageButton3;

    CriarTemplate1View.Presenter criarTemplate1Presenter;

    String selectedImagePath;
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    Atividade atividade;

    private int currentAudioId = 0;
    private int currentImageId = 0;
    private String pathAudio1, pathAudio2, pathAudio3;
    private String pathImage1, pathImage2, pathImage3;

    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //se o foco for perdido por pouco tempo o áudio é pausado
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);

                //quando o foco for recuperado o áudio é executado novamente
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();

                //caso perca o foco permanentemente é dado o stop
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.stop();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_template1);

        ButterKnife.bind(this);
        selectedImagePath = new String();

        criarTemplate1Presenter = new CriarTemplate1Presenter(this, this);

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade", -1);
        Log.d("id mari", Integer.toString(id_atividade));

        atividade = criarTemplate1Presenter.getAtividade(id_atividade);

        //Classe responsável por solicitar o foco do áudio
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        criarTemplate1Presenter.verificaResultado(requestCode, resultCode, data);
    }

    @OnClick(R.id.imageButton1)
    public void selecionaImagem1(){
        this.currentImageId = 1;
        criarTemplate1Presenter.selecionaImagem(1);
    }

    @OnClick(R.id.imageButton2)
    public void selecionaImagem2(){
        this.currentImageId = 2;
        criarTemplate1Presenter.selecionaImagem(2);
    }

    @OnClick(R.id.imageButton3)
    public void selecionaImagem3(){
        this.currentImageId = 3;
        criarTemplate1Presenter.selecionaImagem(3);
    }

    @OnClick(R.id.btnAudio1)
    public void selecionaAudio1(){

        this.currentAudioId = 1;

        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) { }
        criarTemplate1Presenter.selecionaAudio(1);
    }

    @OnClick(R.id.btnAudio2)
    public void selecionaAudio2(){

        this.currentAudioId = 2;

        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {}
        criarTemplate1Presenter.selecionaAudio(2);
    }

    @OnClick(R.id.btnAudio3)
    public void selecionaAudio3(){

        this.currentAudioId = 3;

        if (checkPermissionREAD_EXTERNAL_STORAGE(this)) {}
        criarTemplate1Presenter.selecionaAudio(3);
    }

    //@OnClick(R.id.btnAudio2)
//    public void executaAudio(){
//
//        //Verifica se algum áudio foi setado antes de dar play
//        if(mMediaPlayer != null) {
//
//            //pede permissão para o Android para executar o áudio
//            int result = mAudioManager.requestAudioFocus(mOnAudioFocusChangeListener,
//                    AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
//
//            if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
//                mMediaPlayer.start();
//            }
//        }
//    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    public void abreActivity(Intent intent, Integer codigo, int id) {
        startActivityForResult(intent, codigo);
    }

    //Exibe uma imagem a partir do seu caminho
    @Override
    public void carregaImagem(String caminhoImagem){
        ImageButton currentImageButton;
        if(currentImageId == 1)
            currentImageButton = imageButton1;
        else if(currentImageId == 2)
            currentImageButton = imageButton2;
        else
            currentImageButton = imageButton3;

        Picasso.get()
                .load("file://" + caminhoImagem)
                .fit()
                .centerCrop()
                .into(currentImageButton);

        if(currentImageId == 1)
            pathImage1 = caminhoImagem;
        else if (currentImageId == 2)
            pathImage2 = caminhoImagem;
        else
            pathImage3 = caminhoImagem;
    }

    //Carrega o áudio a partir do seu caminho
    @Override
    public void carregaAudio(String caminhoAudio) {

        releaseMediaPlayer();
        mMediaPlayer = MediaPlayer.create(this, Uri.parse(caminhoAudio));

        if(currentAudioId == 1)
            pathAudio1 = caminhoAudio;
        else if (currentAudioId == 2)
            pathAudio2 = caminhoAudio;
        else
            pathAudio3 = caminhoAudio;
    }

    //Limpa o MediaPlayer
    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_template1, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_salvar:
                if(pathAudio1 == null || pathAudio2 == null|| pathAudio3 == null){
                    Toast.makeText(CriarTemplate1Activity.this, "Não é possível cadastrar a atividade sem áudio.", Toast.LENGTH_LONG).show();
                }else if(pathImage1 == null || pathImage2 == null || pathImage3 == null){
                    Toast.makeText(CriarTemplate1Activity.this, "Não é possível cadastrar a atividade sem imagem.", Toast.LENGTH_LONG).show();
                }else{
                    criarTemplate1Presenter.cadastrar(pathAudio1, pathAudio2, pathAudio3, pathImage1, pathImage2, pathImage3);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void abrirMainActivity(boolean ok1, boolean ok2, boolean ok3){
        if (ok1 == true && ok2 == true && ok3 == true) {
            Toast.makeText(CriarTemplate1Activity.this, "Atividade cadastrada com sucesso", Toast.LENGTH_LONG).show();
            Intent abrirDetalhes = new Intent(CriarTemplate1Activity.this, AtividadesDetailActivity.class);
            abrirDetalhes.putExtra("atividade_id", atividade.getId());
            startActivity(abrirDetalhes);
            finish();

        } else{
            Toast.makeText(CriarTemplate1Activity.this, "Impossível cadastrar a atividade", Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkPermissionREAD_EXTERNAL_STORAGE(
            final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context,
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        (Activity) context,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    showDialog("External storage", context,
                            Manifest.permission.READ_EXTERNAL_STORAGE);

                } else {
                    ActivityCompat
                            .requestPermissions(
                                    (Activity) context,
                                    new String[] { Manifest.permission.READ_EXTERNAL_STORAGE },
                                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }

        } else {
            return true;
        }
    }

    public void showDialog(final String msg, final Context context,
                           final String permission) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setCancelable(true);
        alertBuilder.setTitle("Permission necessary");
        alertBuilder.setMessage(msg + " permission is necessary");
        alertBuilder.setPositiveButton(android.R.string.yes,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions((Activity) context,
                                new String[] { permission },
                                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // do your stuff
                } else {
                    Toast.makeText(this, "GET_ACCOUNTS Denied",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

}
