package com.example.a10516125940.cantineiros.Controller;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.R;

import java.util.ArrayList;

public class Gerenciamento_Pedidos extends AppCompatActivity {

    private ListView listaPedidosFeitos;

    private AlertDialog.Builder dialogo;

    private int indicePedidoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciamento__pedidos);

        listaPedidosFeitos = findViewById(R.id.listViewPedidosFeitos);

        criarDialogo();
        inserirElementosListView();

        listaPedidosFeitos.setLongClickable(true);
        listaPedidosFeitos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indicePedidoSelecionado = position;
                dialogo.create().show();
                return true;
            }
        });
    }

    private void criarDialogo(){
        dialogo = new AlertDialog.Builder(Gerenciamento_Pedidos.this);
        dialogo.setTitle("Mudar status pedido.");
        dialogo.setMessage("Gostaria de fazer qual mudança nesse pedido?");
        dialogo.setCancelable(true);

        dialogo.setNegativeButton("Marcar como feito", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ControllerCentral.listaPedidos.get(indicePedidoSelecionado).setFeito(true);
                inserirElementosListView();
            }
        });

        dialogo.setPositiveButton("Marcar como entregue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ControllerCentral.listaPedidos.get(indicePedidoSelecionado).setEntregue(true);
                inserirElementosListView();
            }
        });
    }

    private void inserirElementosListView() {
        ArrayList<Pedido> lista = ControllerCentral.listaPedidos;
        if (lista.isEmpty()) {
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listaPedidosFeitos.setAdapter(adaptador);
        } else {
            ArrayList<String> listaDado = new ArrayList<>(lista.size());

            for (Pedido p : lista) {
                listaDado.add(p.mostrarParaAdministrador());
            }

            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, listaDado
            );

            listaPedidosFeitos.setAdapter(adaptador);
        }
    }
}
