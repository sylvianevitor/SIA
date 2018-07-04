package com.example.sylviane.sia.Atividade.Atividade_Passiva.ExecutarAtividadePassiva;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.PassivaTemplate1DAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.PassivaTemplate1;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Natasha on 26/06/2018.
 */

public class ExecutarAtividadePassivaActivity extends AppCompatActivity {
    @BindView(R.id.video_view_passiva)VideoView videoView;
    Atividade atividade;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executar_atividade_passiva);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        int id_atividade = intent.getIntExtra("id_atividade",-1);

        AtividadeDAO atividadeDAO = new AtividadeDAO(this);
        atividade = atividadeDAO.getAtividadeId(id_atividade);
        executaVideo();


    }

    public String load_video(){
        PassivaTemplate1DAO passivaTemplate1DAO = new PassivaTemplate1DAO(this);
        ArrayList<PassivaTemplate1>  passivaTemplate1 = passivaTemplate1DAO.getArquivos(atividade);
        return passivaTemplate1.get(0).getVideo();
    }
    public void executaVideo(){
        videoView.setVideoPath(
                load_video());
        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


    }
}
