package com.heyfood.heyfoodapp.prato.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.infra.Sessao;
import com.heyfood.heyfoodapp.prato.dominio.Prato;
import com.heyfood.heyfoodapp.prato.persistencia.PratoDAO;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.restaurante.ui.AtualizarRestauranteActivity;
import com.heyfood.heyfoodapp.restaurante.ui.ListarRestaurantes;
import com.heyfood.heyfoodapp.util.RecyclerItemClickListener;

import java.util.List;

public class ListarPratosActivity extends AppCompatActivity {
    private RecyclerView recyclerPrato;
    PratoDAO pratoDAO;
    Context contexto;
    Restaurante restaurante = Sessao.instance.getRestaurante();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_pratos);
        contexto = this;

        pratoDAO = new PratoDAO(this);
        List<Prato> listaPrato;

        recyclerPrato = findViewById(R.id.recyclerPratoId);


        listaPrato = pratoDAO.getPratoByRestaurante(restaurante.getId());

        AdapterPratos adapter = new AdapterPratos(listaPrato);
        final List<Prato> finalListaPrato = listaPrato;

        //Configurar Recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPrato.setLayoutManager(layoutManager);
        recyclerPrato.setHasFixedSize(true);
        recyclerPrato.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerPrato.setAdapter( adapter );

        recyclerPrato.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerPrato,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                final Prato prato = finalListaPrato.get(position);
                                StringBuilder mensagem = new StringBuilder();
                                mensagem.append("Descrição: \n");
                                mensagem.append(prato.getDescricao() + "\n");

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ListarPratosActivity.this);

                                //Configura titulo e mensagem
                                dialog.setTitle(prato.getNome());
                                dialog.setMessage(mensagem.toString());
                                dialog.setCancelable(true);

                                //Configura acoes para o botao
                                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                    }
                                });
                                if (Sessao.instance.getCliente() != null){
                                    RatingBar ratingBar = new RatingBar(contexto);
                                    dialog.setView(ratingBar);

                                }

                                //Criar e exbir o alertDialog
                                dialog.create();
                                dialog.show();

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );



    }

}