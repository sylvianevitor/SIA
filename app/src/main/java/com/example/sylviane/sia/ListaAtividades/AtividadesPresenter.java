package com.example.sylviane.sia.ListaAtividades;

import android.content.Context;
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;
import com.example.sylviane.sia.persist.model.Atividade;
/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesPresenter {

    private AtividadesView atividadesView;
    private Context context;

    AtividadesPresenter(AtividadesView atividadesView){
        this.atividadesView = atividadesView;
    }

    //valida informações do banco
    public void updateList(List<Atividade> atividadeList) {
        if(atividadeList!=null){
            atividadesView.updateListAtividades(atividadeList);
        }
        else{
            Toast.makeText(context,"Erro ao carregar lista de atividades",Toast.LENGTH_LONG);
        }
    }

}
