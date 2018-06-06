package com.example.sylviane.sia.AssistidosDetail;

import android.widget.Toast;

import com.example.sylviane.sia.persist.model.Assistido;

/**
 * Created by Natasha on 22/05/2018.
 */

public class AssistidosDetailPresenter {

    public AssistidosDetailPresenter(AssistidosDetailView assistidosDetailView) {
        this.assistidosDetailView = assistidosDetailView;
    }

    AssistidosDetailView assistidosDetailView;
    private Assistido assistido;

    public void getAssistidosDetails(long assistidoId) {
                //movieDetailEntity = response.body();
                if(assistido != null){
                    //faz o que a gente quer
                    assistidosDetailView.showDetails(assistido);
                }else{
                    //exibe msg de erro
                    assistidosDetailView.showError();
                }
            }

}
