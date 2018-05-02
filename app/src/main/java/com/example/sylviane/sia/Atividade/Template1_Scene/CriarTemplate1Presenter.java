package com.example.sylviane.sia.Atividade.Template1_Scene;

import java.util.List;

/**
 * Created by mariana on 01/05/18.
 */

public class CriarTemplate1Presenter {

    CriarTemplate1View criarTemplate1View;

    public CriarTemplate1Presenter(CriarTemplate1View criarTemplate1View){
        this.criarTemplate1View = criarTemplate1View;
    }

    public void ligarCamera() {
        criarTemplate1View.showLoading();
        criarTemplate1View.camera();
        criarTemplate1View.hideLoading();
    }
}
