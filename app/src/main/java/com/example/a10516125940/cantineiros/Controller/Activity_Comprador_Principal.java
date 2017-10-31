package com.example.a10516125940.cantineiros.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.R;

import java.util.ArrayList;

public class Activity_Comprador_Principal extends AppCompatActivity {

    private Button botaoFazerPedido;
    private ListView listViewPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__comprador_principal);

        botaoFazerPedido = findViewById(R.id.botaoPedir);
        listViewPedidos = findViewById(R.id.listViewPedidos);

        inserirElementosListaInterface(com.example.a10516125940.cantineiros.Constantes.u.getListaPedidos());

        botaoFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Comprador_Principal.this, Activity_Fazer_Pedido.class));
            }
        });
    }

    private void inserirElementosListaInterface(ArrayList<Pedido> lista){
        if(lista.isEmpty()){
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listViewPedidos.setAdapter(adaptador);
        }else{
            ArrayList<String> listaDado = new ArrayList<>(lista.size());

            for(Pedido p: lista){
                listaDado.add(p.toString());
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, listaDado
            );

            listViewPedidos.setAdapter(adaptador);
        }
    }
}
