package com.davidglez.globaldb.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import com.davidglez.globaldb.Adapters.RestaurantAdapter;
import com.davidglez.globaldb.DataBase.SQLiteRestauranteDB;
import com.davidglez.globaldb.Pojos.PedidoPojo;
import com.davidglez.globaldb.R;
import com.davidglez.globaldb.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(this, "Restaurante.db", null, 1);
    private RestaurantAdapter restaurantAdapter;
    private ArrayList<PedidoPojo> pedidoPojoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setRecyclerView();

        binding.fabAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PedidoActivity.class);
                startActivityForResult(intent, 23);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 23){
            refresh();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void refresh(){
        pedidoPojoArrayList = new ArrayList<>();
        restauranteDB = new SQLiteRestauranteDB(this, "cliente", null, 1);
        restauranteDB = new SQLiteRestauranteDB(this, "cuenta", null, 1);
        restaurantAdapter = new RestaurantAdapter(pedidoPojoArrayList, pedidoPojoArrayList, this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.recyclerView.setAdapter(restaurantAdapter);
        getDBData();
    }

    public void getDBData(){
        SQLiteDatabase db = restauranteDB.getReadableDatabase();
        PedidoPojo pedidoPojo = null;
        Cursor cursorCliente = db.rawQuery("SELECT * FROM cliente", null);
        Cursor cursorCuenta = db.rawQuery("SELECT * FROM cuenta", null);
        while (cursorCliente.moveToNext() && cursorCuenta.moveToNext()){
            pedidoPojo = new PedidoPojo();
            pedidoPojo.setNombre_cliente("Comensal: " + cursorCliente.getString(1));
            pedidoPojo.setMesa("Mesa: " + cursorCliente.getString(5));
            pedidoPojo.setFecha("Fecha: " + cursorCuenta.getString(2));
            restaurantAdapter.addPedido(pedidoPojo);
        }
    }

    public void setRecyclerView(){
        binding.recyclerView.setAdapter(restaurantAdapter);
        binding.recyclerView.setHasFixedSize(true);
        refresh();
    }
}