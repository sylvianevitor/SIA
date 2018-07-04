package com.example.sylviane.sia.ListaAtividades;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sylviane.sia.R;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import com.example.sylviane.sia.persist.model.Atividade;
/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesAdapter extends RecyclerView.Adapter<AtividadesAdapter.ViewHolder>{

    private List<Atividade> atividadesList;
    private OnRecyclerViewSelectedAtividades onRecyclerViewSelectedAtividades;
    private Context context;

    AtividadesAdapter(List<Atividade> atividadesList, Context context){
        this.atividadesList = atividadesList;
        this.context = context;
    }

    @Override
    public AtividadesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.atividades_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AtividadesAdapter.ViewHolder holder, int position) {
        Atividade atividadesEntity = atividadesList.get(position);
        holder.nomeAtividade.setText(atividadesEntity.getNome());
    }

    @Override
    public int getItemCount() {
        return atividadesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tx_nome_atividade)
        TextView nomeAtividade;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelectedAtividades != null)
                onRecyclerViewSelectedAtividades.onClick(view, getAdapterPosition());

        }

        //seta o clique longo
        @OnLongClick(R.id.container)
        boolean onLongItemClick(View view){
            if(onRecyclerViewSelectedAtividades != null)
                onRecyclerViewSelectedAtividades.onLongClick(view, getAdapterPosition());

            return true;
        }

    }
    public void setOnRecyclerViewSelectedAtividades(OnRecyclerViewSelectedAtividades onRecyclerViewSelectedAtividades){
        this.onRecyclerViewSelectedAtividades = onRecyclerViewSelectedAtividades;
    }
}
