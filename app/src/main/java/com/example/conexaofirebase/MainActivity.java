package com.example.conexaofirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etAnotacao;
    Button btnSalvar, btnMostrar;
    //private FirebaseDatabase database;
    //private DatabaseReference reference;
    private  DatabaseReference databaseAnotacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAnotacao = (EditText) findViewById(R.id.etAnotacao);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);

        databaseAnotacao = FirebaseDatabase.getInstance().getReference("Anotacao");

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
                limparCampos();
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MostrarListaActivity.class);
                startActivity(intent);
            }
        });


    }

    private void salvar() {
        if(etAnotacao.length() == 0){
            Toast.makeText(this, "Você deve digitar um nome", Toast.LENGTH_LONG).show();
        }else{
            String nome = etAnotacao.getText().toString().trim();
            String id = databaseAnotacao.push().getKey(); //vai criar a chava primaria

           Anotacao anotacao = new Anotacao(id,nome);
           databaseAnotacao.child(id).setValue(anotacao);

            /*
            funciona
            Anotacao nota = new Anotacao();
            nota.setNome(etAnotacao.getText().toString().trim());
            database = FirebaseDatabase.getInstance();
            reference = database.getReference();
            reference.child("notas").push().setValue(nota);*/


            Toast.makeText(this, "Nome adicionado com sucesso!", Toast.LENGTH_SHORT).show();
        }

    }

    private void limparCampos(){
        etAnotacao.setText("");
    }


}
