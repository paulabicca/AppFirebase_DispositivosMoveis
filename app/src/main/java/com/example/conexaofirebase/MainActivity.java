package com.example.conexaofirebase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MainActivity extends AppCompatActivity {

    EditText etAnotacao;
    Button btnSalvar, btnShow;
    DatabaseReference reff;
    Anotacao anotacao;
    long maxid = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etAnotacao = (EditText) findViewById(R.id.etAnotacao);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnShow = (Button) findViewById(R.id.btnShow);
        anotacao = new Anotacao();//objeto
        reff = FirebaseDatabase.getInstance().getReference().child("Anotacao"); //insere os dados no firebase


        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anotacao.setNome(etAnotacao.getText().toString().trim());
                reff.push().setValue(anotacao);
                Toast.makeText(MainActivity.this, "Anotacao salva com sucesso", Toast.LENGTH_SHORT).show();
                anotacao.setNome("");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExibeActivity.class);
                startActivity(intent);
            }
        });

    }



}
