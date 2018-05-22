package com.example.sylviane.sia.Atividade.Template1_Scene;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.sylviane.sia.R;

/**
 * Created by sylviane on 12/05/18.
 */

public class ExecutarTemplate1Presenter {
    ExecutarTemplate1View executarTemplate1View;

    public ExecutarTemplate1Presenter(ExecutarTemplate1View executarTemplate1View){
        this.executarTemplate1View = executarTemplate1View;
    }

    public void load_info(){executarTemplate1View.load_audio();}

}
