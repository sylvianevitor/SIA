package com.example.sylviane.sia.Main_Scene;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by mariana on 24/04/18.
 */

public class MainPresenter extends AppCompatActivity {

    MainView mainView = null;

    public MainPresenter(MainView mainView){
        this.mainView = mainView;
    }

    public void criarAtividade() {
        mainView.criar();
    }

    public void selecionarAtividade() {
        mainView.selecionar();
    }

    public void verAssistidos() {
        mainView.ver();
    }
}