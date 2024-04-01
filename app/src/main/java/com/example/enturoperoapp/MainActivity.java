package com.example.enturoperoapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private DatabaseHelper dbHelper;
    private Adapter adapter;
    SearchView buscador;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        buscador = findViewById(R.id.buscador);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Code

        dbHelper = new DatabaseHelper(this);

        List<Articulo> articulos = new ArrayList<>();

        //Agregar los articulos
        articulos.add(new Articulo(1, 25));
        articulos.add(new Articulo(1, 30));
        articulos.add(new Articulo(3, 3));

        dbHelper.agregarArticulos(articulos);

        //Vaciar base de datos
        //dbHelper.vaciarTablaArticulos();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        adapter = new Adapter(this.findViewById(R.id.recyclerView), dbHelper.getAllArticulos());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);




    }



    @Override
    public boolean onQueryTextSubmit(String query) {
        // No es necesario hacer nada aquí, ya que el filtrado se realiza en onQueryTextChange
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        // Si newText está vacío, mostrar todos los elementos
        if (newText.isEmpty()) {
            adapter.filtrado(0); // 0 para mostrar todos los elementos
        } else {
            try {
                int num = Integer.parseInt(newText);
                adapter.filtrado(num);
            } catch (NumberFormatException e) {
                // Si el texto no es un número válido, no hacer nada o mostrar un mensaje de error
            }
        }
        return true;
    }

}