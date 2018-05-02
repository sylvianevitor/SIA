package com.example.sylviane.sia.ListaAssistidos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.Entity.AssistidosEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by Natasha on 25/04/2018.
 */

public class AssistidosAdapter extends RecyclerView.Adapter<AssistidosAdapter.ViewHolder>{

    private List<AssistidosEntity> assistidosList;
    OnRecyclerViewSelectedAssistidos onRecyclerViewSelectedAssistidos;
    private Context context;

    AssistidosAdapter(List<AssistidosEntity> assistidosList, Context context){
        this.assistidosList = assistidosList;
        this.context = context;
    }

    //infla o componente view
    @Override
    public AssistidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assistidos_item_list, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(AssistidosAdapter.ViewHolder holder, int position) {
        AssistidosEntity assistidosEntity = assistidosList.get(position);
    }

    @Override
    public int getItemCount() {
        return assistidosList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tx_nome_assistido)
        TextView nomeAssistido;

        public ViewHolder(View itemView) {

            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        //seta o clique rápido
        @OnClick(R.id.container)
        void onItemClick(View view){
            if(onRecyclerViewSelectedAssistidos != null)
                onRecyclerViewSelectedAssistidos.onClick(view, getAdapterPosition());

        }

        //seta o clique longo
        @OnLongClick(R.id.container)
        boolean onLongItemClick(View view){
            if(onRecyclerViewSelectedAssistidos != null)
                onRecyclerViewSelectedAssistidos.onLongClick(view, getAdapterPosition());

            return true;
        }

    }
    public void setOnRecyclerViewSelectedAssistidos(OnRecyclerViewSelectedAssistidos onRecyclerViewSelectedAssistidos){
        this.onRecyclerViewSelectedAssistidos = onRecyclerViewSelectedAssistidos;
    }
}
