package com.davidglez.globaldb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.davidglez.globaldb.DataBase.SQLiteRestauranteDB;
import com.davidglez.globaldb.R;
import com.davidglez.globaldb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(this, "Restaurante.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.fabAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PedidoActivity.class);
                startActivity(intent);
            }
        });
    }
}