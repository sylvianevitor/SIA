package com.example.sylviane.sia.SelecaoAssistidos;

import android.view.View;

/**
 * Created by sylviane on 19/05/18.
 */

public interface OnRecyclerViewSelectedAssistido {
    abstract void onClick(View view, int position);
    void onLongClick(View view, int position);
}
