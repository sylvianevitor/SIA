package com.example.sylviane.sia.Atividade.Cores_Scene;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.sylviane.sia.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import static com.example.sylviane.sia.R.drawable.round_button_violet;

/**
 * Created by sylviane on 12/06/18.
 */

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
    @BindView(R.id.btnSair2)
    Button sair;

    int pontuacao = 100;
    boolean red_set = false;
    boolean blue_set = false;
    boolean yellow_set = false;
    ExecutarCoresPresenter executarCoresPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_cores);

        ButterKnife.bind(this);
        executarCoresPresenter = new ExecutarCoresPresenter(this, this);
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


    @OnClick(R.id.btnSair2)
    public void fim() {
        executarCoresPresenter.sair(pontuacao, this);
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
