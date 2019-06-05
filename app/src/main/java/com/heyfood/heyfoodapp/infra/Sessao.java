package com.heyfood.heyfoodapp.infra;

import android.annotation.SuppressLint;

import com.heyfood.heyfoodapp.cliente.dominio.Cliente;
import com.heyfood.heyfoodapp.proprietario.dominio.Proprietario;
import com.heyfood.heyfoodapp.restaurante.dominio.Restaurante;
import com.heyfood.heyfoodapp.usuario.dominio.Usuario;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Sessao {
    public static final Sessao instance = new Sessao();
    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private final Map<String,Object> values = new HashMap<>();

    public void setUsuario(Usuario usuario) {
        setValue("sessao.Usuario", usuario);
    }

    public Usuario getUsuario() {
        return (Usuario)values.get("sessao.Usuario");
    }

    public void setCliente(Cliente cliente) {
        setValue("sessao.Cliente", cliente);
    }

    public Cliente getCliente() {
        return (Cliente)values.get("sessao.Cliente");
    }

    public void setProprietario(Proprietario proprietario){
        setValue("sessao.Proprietario", proprietario);
    }

    public Proprietario getProprietario() {
        return (Proprietario) values.get("sessao.Proprietario");
    }


    @SuppressWarnings("WeakerAccess")
    public void setValue(String key, Object value) {
        values.put(key, value);

    }

    public Object getValue(String key) {
        return values.get(key);
    }

    public void reset() {
        this.values.clear();
    }

    public String getRestaurante(){ return (String) values.get("sessao.Restaurante");}

    public void setRestaurante(Restaurante restaurante) { setValue("sessao.Restaurante", restaurante);}

}
