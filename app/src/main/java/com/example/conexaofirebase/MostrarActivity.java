package com.example.conexaofirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MostrarActivity extends AppCompatActivity {

    ListView lvLista;
    List<Anotacao> lista;
    AdapterAnotacao adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;
    private Query query;
    private ChildEventListener childEventListener;

    @Override
    protected void onStart() {
        super.onStart();

        lista.clear();

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        query = reference.child("notas").orderByChild("nome");

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Anotacao nota = new Anotacao();
                nota.setId(dataSnapshot.getKey());
                nota.setNome(dataSnapshot.child("nome").getValue(String.class));
                lista.add(nota);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addChildEventListener(childEventListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        query.removeEventListener(childEventListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        lvLista = (ListView) findViewById(R.id.lvAnotacoes);

        lista = new ArrayList<>();

        carregarLista();

        lvLista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Anotacao notaSelecionada = lista.get(position);
                AlertDialog.Builder alerta = new AlertDialog.Builder(MostrarActivity.this);
                alerta.setTitle("Edição de anotação");
                alerta.setMessage("Você deseja editar a anotação " + notaSelecionada.getNome() + "?");
                alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MostrarActivity.this, AtualizarActivity.class);
                        startActivity(intent);
                    }
                });

                alerta.setNeutralButton("Não", null);
                alerta.show();
                return true;
            }
        });

    }

    private void carregarLista(){
        adapter = new AdapterAnotacao(this, lista);
        lvLista.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
