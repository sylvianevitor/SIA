package com.example.sylviane.sia.Atividade.Cores_Scene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sylviane.sia.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExecutarCoresActivity extends AppCompatActivity implements ExecutarCoresView {

    @BindView(R.id.colorButton0)
    ImageView image0;
    @BindView(R.id.colorButton1)
    ImageButton image1;
    @BindView(R.id.colorButton2)
    ImageButton image2;
    @BindView(R.id.colorButton3)
    ImageButton image3;
    @BindView(R.id.objectButton)
    ImageView objeto;

    int pontuacao = 100;
    boolean red_set = false;
    boolean blue_set = false;
    boolean yellow_set = false;
    ArrayList<Integer> assistidosList;
    long startTime, endTime, elapsedTime;
    ExecutarCoresPresenter executarCoresPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_cores);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        assistidosList = intent.getIntegerArrayListExtra("assistido_id");
        executarCoresPresenter = new ExecutarCoresPresenter(this, this);
        startTime = System.currentTimeMillis();
    }

    @OnClick(R.id.colorButton1)
    public void red_selection(){
        red_set = true;
        selecao();
    }

    @OnClick(R.id.colorButton2)
    public void blue_selection(){
        blue_set = true;
        selecao();
    }

    @OnClick(R.id.colorButton3)
    public void yellow_selection(){
        yellow_set = true;
        selecao();
    }



    public void fim() {
        endTime = System.currentTimeMillis();
        elapsedTime = endTime - startTime;
        executarCoresPresenter.sair(this, assistidosList, elapsedTime);
        finish();
    }

    @Override
    public void selecao() {
        if(red_set && blue_set){
            image0.setImageResource(R.drawable.round_button_violet);
            objeto.setImageResource(R.drawable.grape_icon_png);
            red_set = false;
            blue_set = false;
        } else if(blue_set && yellow_set){
            image0.setImageResource(R.drawable.round_button_green);
            objeto.setImageResource(R.drawable.tree_icon_png);
            blue_set = false;
            yellow_set = false;
        }else if (yellow_set && red_set){
            image0.setImageResource(R.drawable.round_button_orange);
            objeto.setImageResource(R.drawable.orange_icon_png);
            yellow_set = false;
            red_set = false;
        }
    }
}
