package com.example.a10516125940.cantineiros.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.a10516125940.cantineiros.Model.Pedido;
import com.example.a10516125940.cantineiros.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class Activity_Comprador_Principal extends AppCompatActivity {

    private Button botaoFazerPedido;
    private Button botaoLogout;
    private ListView listViewPedidos;

    private AlertDialog.Builder dialogooCancelar;

    private int indicePedidoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__comprador_principal);

        botaoFazerPedido = findViewById(R.id.botaoPedir);
        botaoLogout = findViewById(R.id.botaoLogout);
        listViewPedidos = findViewById(R.id.listViewPedidos);

        inserirElementosListaInterface();

        dialogooCancelar = new AlertDialog.Builder(Activity_Comprador_Principal.this);
        dialogooCancelar.setTitle("Cancelar?");
        dialogooCancelar.setMessage("Relamente quer cancelar esse produto?");
        dialogooCancelar.setCancelable(true);
        dialogooCancelar.setIcon(android.R.drawable.ic_delete);

        dialogooCancelar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        dialogooCancelar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ControllerCentral.removerPedido(ControllerCentral.u.getListaPedidos().get(indicePedidoSelecionado));
                inserirElementosListaInterface();
            }
        });

        listViewPedidos.setLongClickable(true);
        listViewPedidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                indicePedidoSelecionado = position;
                dialogooCancelar.create().show();
                return true;
            }
        });

        botaoFazerPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Comprador_Principal.this, Activity_Fazer_Pedido.class));
            }
        });

        botaoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Activity_Comprador_Principal.this, MainActivity.class));
            }
        });
    }

    private void inserirElementosListaInterface(){
        ArrayList<Pedido> lista = ControllerCentral.u.getListaPedidos();

        if(lista.isEmpty()){
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, new String[]{"Vazio"}
            );

            listViewPedidos.setAdapter(adaptador);
        }else{
            ArrayList<String> listaDado = new ArrayList<>(lista.size());

            for(Pedido p: lista){
                listaDado.add(p.mostrarParaCliente());
            }
            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                    getApplicationContext(), android.R.layout.simple_list_item_1,
                    android.R.id.text1, listaDado
            );

            listViewPedidos.setAdapter(adaptador);
        }
    }
}
