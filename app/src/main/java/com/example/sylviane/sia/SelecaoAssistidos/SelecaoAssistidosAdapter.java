package com.example.sylviane.sia.SelecaoAssistidos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sylviane.sia.ListaAssistidos.*;
import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.model.Assistido;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

/**
 * Created by sylviane on 14/05/18.
 */

public class SelecaoAssistidosAdapter extends RecyclerView.Adapter<SelecaoAssistidosAdapter.ViewHolder> {
    private List<Assistido> assistidosList;
    private Context context;

    SelecaoAssistidosAdapter(List<Assistido> assistidosList, Context context) {
        this.assistidosList = assistidosList;
        this.context = context;
    }

    @Override
    public SelecaoAssistidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assistidos_item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(SelecaoAssistidosAdapter.ViewHolder holder, int position) {
            Assistido assistidos = assistidosList.get(position);
    }
    @Override
    public int getItemCount() {
        return assistidosList.size();
    }

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

        }

        //seta o clique longo
        @OnLongClick(R.id.container)
        boolean onLongItemClick(View view){

            return true;
        }
    }
}