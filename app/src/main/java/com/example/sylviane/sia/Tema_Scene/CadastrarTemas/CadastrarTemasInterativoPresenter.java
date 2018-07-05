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
        boolean ok = temaDAO.insert(tema);

        cadastrarTemasInterativosView.abrirActivity(ok);
    }

//    public void comparaTema(String nome){
//        TemaDAO temaDAO = new TemaDAO(context);
//        List<Tema> temasList = temaDAO.getTemas();
//        for (int i==0; i < temasList.si; i )
//    }

    public void ligarCamera() {
        cadastrarTemasInterativosView.camera();
    }


}
