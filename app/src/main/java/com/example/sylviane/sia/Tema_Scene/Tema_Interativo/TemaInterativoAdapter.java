package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sylviane.sia.R;
import com.example.sylviane.sia.persist.model.Tema;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TemaInterativoAdapter extends RecyclerView.Adapter<TemaInterativoAdapter.ViewHolder> {
    private Context context;
    private OnRecyclerViewSelected mOnRecyclerViewSelected;
    private List<Tema> temaList;

    public TemaInterativoAdapter(List<Tema> temaList, Context context) {
        this.temaList = temaList;
        this.context = context;
    }

    //infla o componente view
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_temas, parent, false);
        return new ViewHolder(v);
    }

    //seta os dados nas views
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tema socialEntity = temaList.get(position);
        holder.nomeTema.setText(socialEntity.getTema());
    }

    //retorna o tamanho da lista
    @Override
    public int getItemCount() {
        return temaList.size();
    }

    //mapeamento dos componentes da view
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nome_tema_row)
        TextView nomeTema;

        @BindView(R.id.foto_tema_row)
        ImageView fotoTema;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.container)
        void onItemClick(View view){
            if(mOnRecyclerViewSelected != null)
                mOnRecyclerViewSelected.onClick(view, getAdapterPosition());
        }
    }

    public void setOnRecyclerViewSelected(OnRecyclerViewSelected onRecyclerViewSelected){
        this.mOnRecyclerViewSelected = onRecyclerViewSelected;
    }
}
