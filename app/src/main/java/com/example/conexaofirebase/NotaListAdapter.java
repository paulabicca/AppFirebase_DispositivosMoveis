package com.example.conexaofirebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NotaListAdapter extends BaseAdapter   {
    private Context context;
    private List<Anotacao> lista;
    private LayoutInflater inflater;


    public NotaListAdapter(Context context, List<Anotacao> lista){
        this.context = context;
        this.lista = lista;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Suporte item;

        if( convertView == null ){
            convertView = inflater.inflate(R.layout.layout_nota_item,null);
            item = new Suporte();
            item.tvNomeAnotacao = (TextView) convertView.findViewById(R.id.tvNome);
            convertView.setTag(item);
        }else{
            item = (Suporte) convertView.getTag();
        }

        Anotacao nota = lista.get(position);
        item.tvNomeAnotacao.setText(nota.getNome());

        return convertView;
    }

    private class Suporte{
        TextView tvNomeAnotacao;
    }
}