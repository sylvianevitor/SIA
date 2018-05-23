package com.example.sylviane.sia.AssistidosDetail;

import com.example.sylviane.sia.persist.model.Assistido;

/**
 * Created by Natasha on 22/05/2018.
 */

public interface AssistidosDetailView {
    void showDetails(Assistido assistido);

    void showError();
}
