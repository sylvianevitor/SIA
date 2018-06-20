package com.example.sylviane.sia.Tema_Scene.Tema_Detail;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sylviane.sia.AssistidosDetail.AssistidosDetailView;
import com.example.sylviane.sia.Tema_Scene.Tema_Detail.TemaDetailActivity;
import com.example.sylviane.sia.Tema_Scene.Tema_Detail.TemaDetailView;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.model.Tema;

import java.util.ArrayList;


public class TemaDetailPresenter {

    public TemaDetailPresenter(TemaDetailView temaDetailView, Context context) {
        this.temaDetailView = temaDetailView;
        this.context = context;
    }

    Context context;
    TemaDetailView temaDetailView;
    private Tema tema;


    public void getTemaDetails(int temaId) {

//        TemaDAO temaDAO = new TemaDAO(context);
//        tema = temaDAO.getTemaId(temaId);

        if(tema != null){
            //Log.d("teste", tema.get());
            //faz o que a gente quer
            temaDetailView.showDetails(temaId);
        }else{
            //exibe msg de erro
            temaDetailView.showError();
        }
    }

}
