package com.example.sylviane.sia.Atividade.AtividadePassiva.ExecutarAtividadePassiva;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.sylviane.sia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Natasha on 19/06/2018.
 */

public class ExecutarAtividadePassivaActivity extends AppCompatActivity {
    @BindView(R.id.video_view_passiva)VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_executar_atividade_passiva);
        ButterKnife.bind(this);
        videoView.setVideoPath(
                "http://www.ebookfrenzy.com/android_book/movie.mp4");
        MediaController mediaController = new
                MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);


        videoView.start();

    }
}
