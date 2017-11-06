package com.example.a10516125940.cantineiros.Controller;

import android.util.Log;
import android.widget.Toast;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.Model.Produto;
import com.example.a10516125940.cantineiros.Model.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 10516125940 on 31/10/2017.
 */

public class ControllerCentral {

    private static DatabaseReference br = FirebaseDatabase.getInstance().getReference();

    public static Usuario u = new Usuario("Tiago", "123");

    public static ArrayList<Pedido> listaPedidos = new ArrayList<>();

    public static boolean realizarPedido(Pedido pe){
        u.getListaPedidos().add(pe);
        listaPedidos.add(pe);
        Log.i("Pedido: ", pe.mostrarParaAdministrador());

        String nomeUsuario = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        nomeUsuario = nomeUsuario.substring(0, nomeUsuario.indexOf("@"));

        //String dataPedido = pe.getData().replaceAll("/", "");
        Log.i("Pedido: ", pe.getData());
        br.child(nomeUsuario).child(pe.getData()).setValue(pe);

        return true;
    }

    public static ArrayList<Pedido> buscarPedido(){
        final ArrayList<Pedido> listaPedidos = new ArrayList<>();

        String nome = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        nome = nome.substring(0, nome.indexOf("@"));

        br.child(nome).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    listaPedidos.add(postSnapshot.getValue(Pedido.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return listaPedidos;
    }

    public static boolean removerPedido(Pedido pe){
        return u.getListaPedidos().remove(pe) && listaPedidos.remove(pe);
    }

    public static boolean alterarStatusEntrgue(Pedido p, boolean entregue){
        for(Pedido pe: u.getListaPedidos()){
            if(pe.equals(p)){
                pe.setEntregue(entregue);
                return true;
            }
        }
        return false;
    }

    public static boolean alterarStatusFeito(Pedido p, boolean feito){
        for(Pedido pe: u.getListaPedidos()){
            if(pe.equals(p)){
                pe.setFeito(feito);
                return true;
            }
        }
        return false;
    }
}
