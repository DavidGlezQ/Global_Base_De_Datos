package com.davidglez.globaldb.Activities;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.davidglez.globaldb.DataBase.SQLiteRestauranteDB;
import com.davidglez.globaldb.R;
import com.davidglez.globaldb.databinding.ActivityPedidoBinding;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoActivity extends AppCompatActivity {

    private ActivityPedidoBinding binding;
    private SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(this, "Restaurante.db", null, 1);
    private String nombre_cliente, apellido_cliente, plato_entrada, plato_principal,
            postres, bebida, nota_extra, nombre_mesero, apellido_mesero, fecha, comensales, mesa, monto;

    private final String[] sp_plato_entrada = {"Sopa de elote", "Pollo a la crema", "Lasaña mexicana", "Enchiladas mineras", "enchiladas de baile",
            "Tostadas de elote", "Burritos de chile con carne", "Nachos mexicanos", "Tostadas de camaron", "Calabazas con elote"};
    private final String[] sp_plato_principal = {"Enchiladas", "Pizza", "Hamburguesa", "Chilaquiles con chile", "Albondigas de pescado",
            "Quesadilla de pollo", "Tamales", "Chiles en nogada", "Ceviche de pescado", "Tacos de pescado"};
    private final String[] sp_plato_postre = {"Tarta de Calabaza", "Pay de Queso", "Pastel de elote", "Helado", "Cocada", "Mousse", "Jericalla",
            "Picarones", "Ate", "Crepe"};
    private final String[] sp_bebiba = {"Cola-Cola", "Fanta", "Agua", "Cerveza", "Vino", "Limonada", "Agua de jamaica", "Te verde", "Jugo de naranja",
            "Sidral"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedidoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setSpinners();
        getDate();

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tilValidate();
                if (!nombre_cliente.isEmpty() && !apellido_cliente.isEmpty() && !comensales.isEmpty() && !mesa.isEmpty() && !nota_extra.isEmpty()
                && !nombre_mesero.isEmpty() && !apellido_mesero.isEmpty() && !monto.isEmpty()){

                    //Insert a la base de datos
                    restauranteDB.insertarCuenta(monto, fecha);
                    restauranteDB.insertarMesero(nombre_mesero, apellido_mesero);
                    restauranteDB.insertarCliente(nombre_cliente, apellido_cliente, nota_extra, comensales, mesa);
                    restauranteDB.insertarProducto();
                    restauranteDB.insertarCategoriaProductos();


                    //obtener ultimo id de cada tabla para insertar un pedido
                    SQLiteDatabase db = restauranteDB.getReadableDatabase();
                    final String myQueryCuenta = "SELECT MAX(id_cuenta) FROM cuenta";
                    Cursor cursorCuenta = db.rawQuery(myQueryCuenta, null);
                    cursorCuenta.moveToFirst();
                    int lastIdCuenta = cursorCuenta.getInt(0);
                    cursorCuenta.close();

                    final String myQueryMesero = "SELECT MAX(id_mesero) FROM mesero";
                    Cursor cursorMesero = db.rawQuery(myQueryMesero, null);
                    cursorMesero.moveToFirst();
                    int lastIdMesero = cursorMesero.getInt(0);
                    cursorMesero.close();

                    final String myQueryCliente = "SELECT MAX(id_cliente) FROM cliente";
                    Cursor cursorCliente = db.rawQuery(myQueryCliente, null);
                    cursorCliente.moveToFirst();
                    int lastIdCliente = cursorCliente.getInt(0);
                    cursorCliente.close();

                    restauranteDB.insertarPedido(lastIdCuenta, lastIdMesero, lastIdCliente, 1);

                    Toast.makeText(PedidoActivity.this, "Pedido agregado con exito!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PedidoActivity.this, "Debes de llenar todos los campos!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Validacion de campos
    public void tilValidate(){
        nombre_cliente = binding.tieNameClient.getText().toString();
        apellido_cliente = binding.tieLastnameClient.getText().toString();
        comensales = binding.tieNumComensales.getText().toString();
        mesa = binding.tieMesa.getText().toString();
        nota_extra = binding.tieNotaExtra.getText().toString();
        nombre_mesero = binding.tieNameMesero.getText().toString();
        apellido_mesero = binding.tieLastnameMesero.getText().toString();
        monto = binding.tieMonto.getText().toString();
    }

    //set spinners
    public void setSpinners(){
        ArrayAdapter adapterEntrada = new ArrayAdapter(this, R.layout.option_item, sp_plato_entrada);
        binding.actPlatoEntrada.setText(adapterEntrada.getItem(0).toString(), false);
        binding.actPlatoEntrada.setAdapter(adapterEntrada);

        ArrayAdapter adapterPrincipal = new ArrayAdapter(this, R.layout.option_item, sp_plato_principal);
        binding.actPlatoPrincipal.setText(adapterPrincipal.getItem(0).toString(), false);
        binding.actPlatoPrincipal.setAdapter(adapterPrincipal);

        ArrayAdapter adapterPostre = new ArrayAdapter(this, R.layout.option_item, sp_plato_postre);
        binding.actPlatoPostre.setText(adapterPostre.getItem(0).toString(), false);
        binding.actPlatoPostre.setAdapter(adapterPostre);

        ArrayAdapter adapterBebida = new ArrayAdapter(this, R.layout.option_item, sp_bebiba);
        binding.actBebida.setText(adapterBebida.getItem(0).toString(), false);
        binding.actBebida.setAdapter(adapterBebida);
    }

    public void getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        fecha = simpleDateFormat.format(date);
        binding.txtFecha.setText("Fecha del registro: " + fecha);
    }
}