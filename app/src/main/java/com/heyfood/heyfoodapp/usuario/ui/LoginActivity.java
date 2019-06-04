package com.heyfood.heyfoodapp.usuario.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.heyfood.heyfoodapp.R;
import com.heyfood.heyfoodapp.cliente.negocio.ClienteServices;
import com.heyfood.heyfoodapp.cliente.ui.CadastrarClienteActivity;
import com.heyfood.heyfoodapp.infra.ui.MainClienteActivity;
import com.heyfood.heyfoodapp.infra.ui.MainProprietarioActivity;
import com.heyfood.heyfoodapp.proprietario.negocio.ProprietarioServices;
import com.heyfood.heyfoodapp.proprietario.ui.CadastrarProprietarioActivity;
import com.heyfood.heyfoodapp.restaurante.ui.InformacoesRestauranteActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText login;
    private EditText senha;
    private Switch switchEstado;

    private final ClienteServices clienteServices = new ClienteServices(this);
    private final ProprietarioServices proprietarioServices = new ProprietarioServices(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.campoLoginId);
        senha = findViewById(R.id.campoSenhaId);

        switchEstado = findViewById(R.id.switchEstado);

    }

    public void abrirTelaCadastro(View view){

        if(switchEstado.isChecked()){
            Intent novaTela = new Intent(this, CadastrarProprietarioActivity.class);
            startActivity(novaTela);

        }else{
            Intent novaTela = new Intent(this, CadastrarClienteActivity.class);
            startActivity(novaTela);
        }

    }

    public void logar(View view) {
        try{
            if(switchEstado.isChecked()) {
                proprietarioServices.login(login.getText().toString(), senha.getText().toString());
                Intent novaTela = new Intent(this, MainProprietarioActivity.class);
                startActivity(novaTela);
            }else{
                clienteServices.login(login.getText().toString(), senha.getText().toString());
                Intent novaTela = new Intent(this, MainClienteActivity.class);
                startActivity(novaTela);
            }
        }
        catch(Exception e){
            Toast.makeText(this, "Usuário não cadastrado", Toast.LENGTH_LONG).show();
        }

    }

    public void abrirTela(View view){


        Intent novaTela = new Intent(this, InformacoesRestauranteActivity.class);
        startActivity(novaTela);
        }

}
