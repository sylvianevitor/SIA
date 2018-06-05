package com.example.sylviane.sia.SelecaoAssistidos;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

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
    OnRecyclerViewSelectedAssistido onRecyclerViewSelected;

    SelecaoAssistidosAdapter(List<Assistido> assistidosList, Context context) {
        this.assistidosList = assistidosList;
        this.context = context;
    }

    //infla o component view
    @Override
    public SelecaoAssistidosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.assistidos_item_list, parent, false);
        return new ViewHolder(v);
    }

    //seta dados nos view holders
    @Override
    public void onBindViewHolder(SelecaoAssistidosAdapter.ViewHolder holder, int position) {
            Assistido assistido = assistidosList.get(position);
            holder.nomeAssistido.setText(assistido.getNome_completo());

    }

    //tamanho da lista
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
            if(onRecyclerViewSelected != null)
                onRecyclerViewSelected.onClick(view, getAdapterPosition());
        }

        //seta o clique longo
        @OnLongClick(R.id.container)
        boolean onLongClick(View view){
            if(onRecyclerViewSelected != null)
                onRecyclerViewSelected.onLongClick(view, getAdapterPosition());
            return true;
        }

    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelectedAssistido onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}