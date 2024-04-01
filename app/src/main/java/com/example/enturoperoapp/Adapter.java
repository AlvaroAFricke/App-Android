package com.example.enturoperoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter {

    public TextView numArtTextView;
    public TextView precioArtTextView;
    public List<Articulo> list;
    public List<Articulo> copia;

    public Adapter(View itemView, List<Articulo> lista) {
        numArtTextView = itemView.findViewById(R.id.numArt);
        precioArtTextView = itemView.findViewById(R.id.precioArt);
        this.list = lista;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new RecyclerView.ViewHolder(view) {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.numArt);
        textView.setText(String.valueOf(list.get(position).getId()));
        textView = holder.itemView.findViewById(R.id.precioArt);
        textView.setText(String.valueOf(list.get(position).getPrecio()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filtrado(final Integer numBuscar) {
        if (numBuscar == null) {
            this.list.clear();
        } else {
            this.list.clear();
        }
        notifyDataSetChanged();
    }

}
