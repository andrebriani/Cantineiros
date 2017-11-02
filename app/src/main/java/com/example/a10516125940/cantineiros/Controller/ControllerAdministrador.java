package com.example.a10516125940.cantineiros.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a10516125940.cantineiros.R;

public class ControllerAdministrador extends AppCompatActivity {

    private Button botaoPedidos;
    private Button botaoEstabelecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controller_administrador);

        botaoPedidos = findViewById(R.id.botaoPedidos);
        botaoEstabelecimento = findViewById(R.id.botaoEstabelecimento);

        botaoPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControllerAdministrador.this, Gerenciamento_Pedidos.class));
            }
        });

        botaoEstabelecimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControllerAdministrador.this, Activity_Administrador.class));
            }
        });
    }
}
