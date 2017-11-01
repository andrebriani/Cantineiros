package com.example.a10516125940.cantineiros.Controller;

import android.util.Log;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.Model.Produto;
import com.example.a10516125940.cantineiros.Model.Usuario;

import java.util.ArrayList;

/**
 * Created by 10516125940 on 31/10/2017.
 */

public class ControllerCentral {

    public static Usuario u = new Usuario("Tiago", "123");

    public static ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public static boolean realizarPedido(Pedido pe){
        u.getListaPedidos().add(pe);
        Log.i("Pedido: ", pe.mostrarParaAdministrador());

        return true;
    }

    public static boolean removerPedido(Pedido pe){
        return u.getListaPedidos().remove(pe);
    }

    public static boolean alterarStatusPedido(Pedido p, boolean status){
        for(Pedido pe: u.getListaPedidos()){
            if(pe.equals(p)){
                pe.setEntregue(status);
                return true;
            }
        }
        return false;
    }
}
