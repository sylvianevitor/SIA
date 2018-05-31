package com.example.sylviane.sia.SelecaoAssistidos;

import com.example.sylviane.sia.Entity.AssistidosEntity;
import com.example.sylviane.sia.Entity.AssistidosListEntity;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylviane on 14/05/18.
 */

public class SelecaoAssistidosPresenter {

    private SelecaoAssistidosView assistidosView;
    private List<Assistido> assistidosList = new ArrayList<>();
    AssistidoDAO assistidoDAO;
    //private List<MovieEntity> moviesList = new ArrayList<>();
    //MovieListEntity movieListEntity


    SelecaoAssistidosPresenter(SelecaoAssistidosView assistidosView){
        this.assistidosView = assistidosView;
    }

    public void updateList(List<Assistido> listassistidos) {
       /* @Override
        public void onResponse(Call<AssistidoDAO> call, Response<AssistidoDAO> response) {
            assistidoDAO = response.body();
            if(assistidoDAO != null){
                assistidosView.updateList(assistidoDAO.getAssistidos());
            } else{
                assistidosView.showMessage("Falha");
            }
            assistidosView.hideLoading();
        }

        @Override
        public void onFailure(Call<AssistidoDAO> call, Throwable t) {
            assistidosView.hideLoading();
            assistidosView.showMessage("Falha no acesso ao servidor");
        }*/

    }
}
