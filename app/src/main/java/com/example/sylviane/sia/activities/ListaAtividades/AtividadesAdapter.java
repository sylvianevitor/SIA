package com.example.sylviane.sia.activities.ListaAtividades;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.activities.Entity.AssistidosEntity;
import com.example.sylviane.sia.activities.Entity.AtividadesEntity;
import com.example.sylviane.sia.activities.ListaAssistidos.AssistidosAdapter;
import com.example.sylviane.sia.activities.ListaAssistidos.OnRecyclerViewSelectedAssistidos;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AtividadesAdapter extends RecyclerView.Adapter<AtividadesAdapter.ViewHolder>{

    private List<AtividadesEntity> atividadesList;
    OnRecyclerViewSelectedAtividades onRecyclerViewSelectedAtividades;
    private Context context;

    AtividadesAdapter(List<AtividadesEntity> atividadesList, Context context){
        this.atividadesList = atividadesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.atividades_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AtividadesEntity atividadesEntity = atividadesList.get(position);
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
