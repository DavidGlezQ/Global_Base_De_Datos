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
        restauranteDB = new SQLiteRestauranteDB(this, "pedido", null, 1);
        restaurantAdapter = new RestaurantAdapter(pedidoPojoArrayList, pedidoPojoArrayList, this, this);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.recyclerView.setAdapter(restaurantAdapter);
        getDBData();
    }

    public void getDBData(){
        SQLiteDatabase db = restauranteDB.getReadableDatabase();
        PedidoPojo pedidoPojo = null;
        Cursor cursorCliente = db.rawQuery("SELECT * FROM cliente", null);
        Cursor cursorCuenta = db.rawQuery("SELECT * FROM cuenta", null);
        Cursor cursorPedido = db.rawQuery("SELECT * FROM pedido", null);
        Cursor cursorMesero = db.rawQuery("SELECT * FROM mesero", null);
        while (cursorCliente.moveToNext() && cursorCuenta.moveToNext() && cursorPedido.moveToNext() && cursorMesero.moveToNext()){
            pedidoPojo = new PedidoPojo();

            pedidoPojo.setNombre_cliente(cursorCliente.getString(1));
            pedidoPojo.setApellido_cliente(cursorCliente.getString(2));
            pedidoPojo.setNota_extra(cursorCliente.getString(3));
            pedidoPojo.setComensales(Integer.parseInt(cursorCliente.getString(4)));
            pedidoPojo.setMesa(cursorCliente.getString(5));

            pedidoPojo.setNombre_mesero(cursorMesero.getString(1));
            pedidoPojo.setApellido_mesero(cursorMesero.getString(2));

            pedidoPojo.setMonto(Float.parseFloat(cursorCuenta.getString(1)));
            pedidoPojo.setFecha(cursorCuenta.getString(2));

            pedidoPojo.setId(cursorPedido.getInt(0));
            restaurantAdapter.addPedido(pedidoPojo);
        }
    }

    public void setRecyclerView(){
        binding.recyclerView.setAdapter(restaurantAdapter);
        binding.recyclerView.setHasFixedSize(true);
        refresh();
    }
}