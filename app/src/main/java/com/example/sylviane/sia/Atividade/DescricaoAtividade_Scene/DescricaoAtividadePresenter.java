package com.example.sylviane.sia.Atividade.DescricaoAtividade_Scene;

import android.content.Context;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.dao.TemaDAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Tema;
import java.util.List;

public class DescricaoAtividadePresenter implements Contract.Presenter {
    Atividade atividadeExistente;
    boolean editar = false ;

    Contract.View descricaoAtividadeView;
    Context context;

    public DescricaoAtividadePresenter(Contract.View descricaoAtividadeView, Context context){
        this.descricaoAtividadeView = descricaoAtividadeView;
        this.context = context;
    }

    public void cadastro(String nome, String objetivo, String descricao, Integer dificuldade, Tema tema, Integer tipo) {
        AtividadeDAO atividadeDAO = new AtividadeDAO(context);
        Atividade atividade = new Atividade();
        if (editar){
            atividade = atividadeExistente;
        }

        atividade.setNome(nome);
        atividade.setObjetivo(objetivo);
        atividade.setDescricao(descricao);
        atividade.setDificuldade(dificuldade);
        atividade.setId_tema(tema.getId());
        atividade.setDt_cadastro("teste");
        atividade.setId_proprietario(1);
        atividade.setNr_execucoes(0);
        if (tipo == 0) {
            atividade.setTipo_atividade(Atividade.TIPO_ATIVA);
        } else if (tipo == 1){atividade.setTipo_atividade(Atividade.TIPO_PASSIVA);}
        atividade.setAtiva(Atividade.SITUACAO_ATIVA);

        if (editar){ //editar cadastro caso ja exista e nao inserir uma nova
            atividadeDAO.update(atividade);
        }else{
            atividadeDAO.insert(atividade);
        }

        descricaoAtividadeView.abrirAtividade(atividade.getId());
    }

    public void getTemas(){
        TemaDAO temaDAO = new TemaDAO(context);
        descricaoAtividadeView.preencheSpinnerTemas(temaDAO.getTemas());
    }

    public void getAtividade(int id_atividade){
        AtividadeDAO atividadeDAO = new AtividadeDAO(context);
        Atividade atividade = atividadeDAO.getAtividadeId(id_atividade);
        atividadeExistente = atividade;

        if(id_atividade != -1) {
            descricaoAtividadeView.preenchimento(atividade.getNome(), atividade.getObjetivo(), atividade.getDescricao());
            editar = true;
        }
    }

    public boolean comparaNome(String nome){
        if(editar = false){
            AtividadeDAO atividadeDAO = new AtividadeDAO(context);
            List<Atividade> atividadeList = atividadeDAO.getAtividade();
            for (int i =0; i< atividadeList.size();i++){
                if(atividadeList.get(i).getNome().equals(nome)){return false;}
            }
        }
        return  true;
    }

}
