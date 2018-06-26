package com.example.sylviane.sia.SelecaoAssistidos;

import android.content.Context;
import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.dao.AssistidoDAO;
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
    private SparseBooleanArray itemStateArray= new SparseBooleanArray();

    Assistido assistido;
    int assistido2;

//    AssistidoDAO assistidoDAO = new AssistidoDAO(context);
//    List<Assistido> assistidosList2 = assistidoDAO.getAssistidos();

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
        assistido = assistidosList.get(position);
        //assistido2 = assistidosList2.get(position).getId();

        holder.nomeAssistido.setText(assistido.getNome_completo());

        holder.bind(position);
    }

    //tamanho da lista
    @Override
    public int getItemCount() {
        return assistidosList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tx_nome_assistido)
        TextView nomeAssistido;

        @BindView(R.id.brand_select)
        CheckBox selectionState;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            // use the sparse boolean array to check
            if (!itemStateArray.get(position, false)) {
                selectionState.setChecked(false);
            }
            else {
                selectionState.setChecked(true);
            }
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            if (!itemStateArray.get(adapterPosition, false)) {
                selectionState.setChecked(true);
                itemStateArray.put(adapterPosition, true);
                onRecyclerViewSelected.onClick(view, getAdapterPosition(), true);
                //Log.d("ID ASSISTIDO", String.valueOf(assistido2));
            }
            else  {
                selectionState.setChecked(false);
                itemStateArray.put(adapterPosition, false);
                onRecyclerViewSelected.onClick(view, getAdapterPosition(), false);

            }
        }

//        //seta o clique rápido
//        @OnClick(R.id.container)
//        void onItemClick(View view){
//            if(onRecyclerViewSelected != null)
//                onRecyclerViewSelected.onClick(view, getAdapterPosition());
//        }

//        //seta o clique longo
//        @OnLongClick(R.id.container)
//        boolean onLongClick(View view){
//            if(onRecyclerViewSelected != null)
//                onRecyclerViewSelected.onLongClick(view, getAdapterPosition());
//            return true;
//        }

    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelectedAssistido onRecyclerViewSelected){
        this.onRecyclerViewSelected = onRecyclerViewSelected;
    }
}