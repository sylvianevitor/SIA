package com.example.sylviane.sia.ListaAssistidos;
import com.example.sylviane.sia.persist.model.Assistido;
import android.content.Context;
import android.widget.Toast;
import java.util.List;

public class AssistidosPresenter {

    private AssistidosView assistidosView;

    private Context context;

    AssistidosPresenter(AssistidosView assistidosView){
        this.assistidosView = assistidosView;
    }

    //pega informações do banco
    public void updateList(List<Assistido> assistidosList) {
        if(assistidosList!=null){
            assistidosView.updateList(assistidosList);
        }
        else{
            Toast.makeText(context,"Erro ao carregar lista",Toast.LENGTH_LONG);
        }

    }
}
