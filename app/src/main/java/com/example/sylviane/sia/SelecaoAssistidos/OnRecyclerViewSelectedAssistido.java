package com.example.sylviane.sia.SelecaoAssistidos;

import android.view.View;

/**
 * Created by sylviane on 19/05/18.
 */

public interface OnRecyclerViewSelectedAssistido {
    void onClick(View view, int position, boolean state);

    void onLongClick(View view, int position);
}
