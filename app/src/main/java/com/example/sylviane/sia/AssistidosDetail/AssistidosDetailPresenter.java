package com.example.sylviane.sia.AssistidosDetail;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;

/**
 * Created by Natasha on 22/05/2018.
 */

public class AssistidosDetailPresenter {

    public AssistidosDetailPresenter(AssistidosDetailView assistidosDetailView, Context context) {
        this.assistidosDetailView = assistidosDetailView;
        this.context = context;
    }

    Context context;
    AssistidosDetailView assistidosDetailView;
    private Assistido assistido;


    public void getAssistidosDetails(int assistidoId) {

        AssistidoDAO assistidoDAO = new AssistidoDAO(context);
        assistido = assistidoDAO.getAssistidoId(assistidoId);

                if(assistido != null){
                    Log.d("teste", assistido.getNome_completo());
                    //faz o que a gente quer
                    assistidosDetailView.showDetails(assistido);
                }else{
                    //exibe msg de erro
                    assistidosDetailView.showError();
                }
            }
}
