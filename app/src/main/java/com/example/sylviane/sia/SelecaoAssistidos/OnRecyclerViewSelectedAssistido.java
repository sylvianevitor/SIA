package com.example.sylviane.sia.SelecaoAssistidos;

import android.view.View;

public interface OnRecyclerViewSelectedAssistido {
    void onClick(View view, int position, boolean state);

    void onLongClick(View view, int position);
}
