package com.example.sylviane.sia.AssistidosDetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sylviane.sia.ListaAtividades.OnRecyclerViewSelectedAtividades;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AtividadeDAO;
import com.example.sylviane.sia.persist.model.Atividade;
import com.example.sylviane.sia.persist.model.Execucao;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class AssistidosDetailAdapter extends RecyclerView.Adapter<AssistidosDetailAdapter.ViewHolder>{

    private List<Execucao> execucaoList;
    private OnRecyclerViewSelectedExecucao onRecyclerViewSelectedExecucao;
    private Context context;

    AssistidosDetailAdapter(List<Execucao> execucaoList, Context context){
        this.execucaoList = execucaoList;
        this.context = context;
    }

    @Override
    public AssistidosDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.execucoes_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AssistidosDetailAdapter.ViewHolder holder, int position) {
        Execucao atividadesExecutadasEntity = execucaoList.get(position);
        holder.nomeExecucao.setText(getNomeAtividade(atividadesExecutadasEntity.getId_atividade(), context));
    }

    @Override
    public int getItemCount() {
        return execucaoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tx_nome_execucao)
        TextView nomeExecucao;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelectedExecucao != null)
                onRecyclerViewSelectedExecucao.onClick(view, getAdapterPosition());

        }

        //seta o clique longo
        @OnLongClick(R.id.container)
        boolean onLongItemClick(View view){
            if(onRecyclerViewSelectedExecucao != null)
                onRecyclerViewSelectedExecucao.onLongClick(view, getAdapterPosition());

            return true;
        }

    }
    public void setOnRecyclerViewSelectedExecucao(OnRecyclerViewSelectedExecucao onRecyclerViewSelectedExecucao){
        this.onRecyclerViewSelectedExecucao = onRecyclerViewSelectedExecucao;
    }

    public String getNomeAtividade(int atividade_id, Context contexto){
        String nome = null;
        AtividadeDAO atividadeDAO = new AtividadeDAO(contexto);
        Atividade atividadeExecutada = atividadeDAO.getAtividadeId(atividade_id);nome = atividadeExecutada.getNome();

        return nome;
    }
}
