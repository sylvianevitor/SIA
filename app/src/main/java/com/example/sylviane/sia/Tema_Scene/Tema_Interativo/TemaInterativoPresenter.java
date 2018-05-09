package com.example.sylviane.sia.Tema_Scene.Tema_Interativo;

import java.util.List;

/**
 * Created by mariana on 25/04/18.
 */

public class TemaInterativoPresenter {

    private TemaInterativoView temaInterativoView;
    private List<TemaInterativoEntity> temaList;
    private TemaInterativoListEntity temaInterativoListEntity;

    public TemaInterativoPresenter(TemaInterativoView temaInterativoView) {
        this.temaInterativoView = temaInterativoView;
    }

/*
    protected void updateList(String jsonSocial) {

        //verifica se há informações no json
        if (jsonSocial != null) {
            temaInterativoListEntity = new Gson().fromJson(jsonSocial, TemaInterativoListEntity.class);
            temaList = temaInterativoListEntity.getTema();
            temaInterativoView.updateList(temaList);

        } else { //se não houver informações previamente no json, é necessário baixá-las

            final SocialApi socialApi = SocialApi.getInstance();
            socialView.showLoading();
            socialApi.getTema().enqueue(new Callback<SocialListEntity>() {
                @Override
                public void onResponse(Call<SocialListEntity> call, Response<SocialListEntity> response) {
                    socialView.hideLoading();
                    socialListEntity = response.body();

                    if (socialListEntity != null && socialListEntity.getSocial() != null) {
                        socialView.updateList(socialListEntity.getSocial());
                    } else {
                        socialView.showMessage("Falha no acesso");
                    }
                }

            });
        }
    }
*/
    TemaInterativoEntity getTemaId(int position) throws IndexOutOfBoundsException {
        return temaInterativoListEntity.getTema().get(position);

    }

    public void cadastrarTema() {
        temaInterativoView.cadastrar();
    }
}
