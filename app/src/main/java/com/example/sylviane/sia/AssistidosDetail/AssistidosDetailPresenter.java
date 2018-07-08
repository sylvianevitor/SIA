package com.example.sylviane.sia.AssistidosDetail;

import android.content.Context;
import android.widget.Toast;

import com.example.sylviane.sia.persist.dao.AssistidoDAO;
import com.example.sylviane.sia.persist.model.Assistido;
import com.example.sylviane.sia.persist.model.Execucao;

import java.util.List;

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
                    assistidosDetailView.showDetails(assistido);
                }else{
                    assistidosDetailView.showError();
                }
    }

    public String calculaIdade(java.util.Date dataNasc){
        return assistidosDetailView.calculaIdade(dataNasc);
    }

    //valida informações do banco
    public void updateList(List<Execucao> atividadeExecutadaList) {
        if(atividadeExecutadaList!=null){
            assistidosDetailView.updateListAtividades(atividadeExecutadaList);
        }
        else{
            Toast.makeText(context,"Erro ao carregar lista de atividades",Toast.LENGTH_LONG);
        }
    }
}
