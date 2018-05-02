package com.example.sylviane.sia.activities.Relatorios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.sylviane.sia.R;

import butterknife.ButterKnife;

/**
 * Created by Natasha on 26/04/2018.
 */

public class RelatoriosActivity extends AppCompatActivity implements RelatoriosView{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.layoutRelatorios);
        ButterKnife.bind(this);
    }
}
