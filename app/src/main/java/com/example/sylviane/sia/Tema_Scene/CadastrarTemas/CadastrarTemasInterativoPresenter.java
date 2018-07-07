package com.example.sylviane.sia.Tema_Scene.CadastrarTemas;

import android.content.Context;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

public class CadastrarTemasInterativoPresenter implements Contract.Presenter{

    Contract.View cadastrarTemasInterativosView;
    Context context;

    public CadastrarTemasInterativoPresenter(Contract.View cadastrarTemasInterativosView, Context context){
        this.cadastrarTemasInterativosView = cadastrarTemasInterativosView;
        this.context = context;
    }

    public void salvarTema(String nome, String imagem) {
        Tema tema = new Tema();
        tema.setTema(nome);
        tema.setImagem(imagem);

        TemaDAO temaDAO = new TemaDAO(context);
        tema = temaDAO.insert(tema);

        boolean ok = false;

        if (tema != null)
            ok = true;

        cadastrarTemasInterativosView.abrirActivity(ok);
    }


    public void ligarCamera() {
        cadastrarTemasInterativosView.camera();
    }

    public boolean comparaTema(String nome){
        TemaDAO temaDAO = new TemaDAO(context);
        List<Tema> temasList = temaDAO.getTemas();
        for (int i =0; i< temasList.size();i++){
            if(temasList.get(i).getTema().equals(nome)){return false;}
        }
        return  true;
    }


}
