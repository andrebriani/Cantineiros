package com.example.a10516125940.cantineiros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private TextView textView_Esqueceu_senha;
    private EditText campoNome;
    private EditText campoSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoEntrar = findViewById(R.id.botaoEntrar);
        textView_Esqueceu_senha = findViewById(R.id.textView_Esqueceu_Senha);
        campoNome = findViewById(R.id.editText_Usuario);


        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Activity_Administrador.class));
            }
        });

        textView_Esqueceu_senha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Não implementado ainda, birrl", Toast.LENGTH_LONG).show();
            }
        });
    }
}
