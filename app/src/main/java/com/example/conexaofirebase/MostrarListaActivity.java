package com.example.conexaofirebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class MostrarListaActivity extends AppCompatActivity {

    ListView lvLista;
    List<Anotacao> lista;
    NotaListAdapter adapter;

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
                nota.setNome(dataSnapshot.child("nome").getValue(String.class) );
                lista.add(nota);
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
        query.addChildEventListener( childEventListener );
    }

    @Override
    protected void onStop() {
        super.onStop();
        query.removeEventListener( childEventListener );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista);

        lvLista = (ListView) findViewById(R.id.lvAnotacoes);

        lista = new ArrayList<>();

        carregarLista();
    }

    private void carregarLista(){
        adapter = new NotaListAdapter(this, lista);
        lvLista.setAdapter(adapter);
    }

}
