package com.davidglez.globaldb.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.davidglez.globaldb.DataBase.SQLiteRestauranteDB;
import com.davidglez.globaldb.Pojos.PedidoPojo;
import com.davidglez.globaldb.R;
import com.davidglez.globaldb.databinding.ActivityPedidoBinding;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PedidoActivity extends AppCompatActivity {

    private ActivityPedidoBinding binding;
    private SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(this, "Restaurante.db", null, 1);
    private String nombre_cliente, apellido_cliente, plato_entrada, plato_principal,
            plato_postre, bebida, nota_extra, nombre_mesero, apellido_mesero, fecha, comensales, mesa, monto;
    private PedidoPojo pedidoPojo;

    private final String[] sp_plato_entrada = {"Sopa de elote", "Pollo a la crema", "Lasaña mexicana", "Enchiladas mineras", "Enchiladas de baile",
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

        pedidoPojo = (PedidoPojo) getIntent().getSerializableExtra("pedido");
        restauranteDB = new SQLiteRestauranteDB(this, "pedido", null, 1);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pedidoPojo != null){
                    editarPedido(pedidoPojo);
                } else {
                    insertPedido();
                }
            }
        });

        if (pedidoPojo != null){
            getCampos(pedidoPojo);
        }
    }

    public void getCampos(PedidoPojo pedidoPojo){
        pedidoPojo.getId();
        binding.tieNameClient.setText(pedidoPojo.getNombre_cliente());
        binding.tieLastnameClient.setText(pedidoPojo.getApellido_cliente());
        binding.tieNumComensales.setText(String.valueOf(pedidoPojo.getComensales()));
        binding.tieMesa.setText(pedidoPojo.getMesa());
        binding.tieNotaExtra.setText(pedidoPojo.getNota_extra());
        binding.tieNameMesero.setText(pedidoPojo.getNombre_mesero());
        binding.tieLastnameMesero.setText(pedidoPojo.getApellido_mesero());
        binding.tieMonto.setText(String.valueOf(pedidoPojo.getMonto()));
    }

    public void editarPedido(PedidoPojo pedidoPojo){
        SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(PedidoActivity.this, "pedido", null, 1);

        int id = pedidoPojo.getId();
        plato_entrada = binding.actPlatoEntrada.getText().toString();
        plato_principal = binding.actPlatoPrincipal.getText().toString();
        plato_postre = binding.actPlatoPostre.getText().toString();
        bebida = binding.actBebida.getText().toString();

            new MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_MaterialComponents_Title_Icon)
                    .setTitle("Editar Pedido")
                    .setMessage("Seguro que quieres editar este pedido?")
                    .setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            tilValidate();
                            if (!nombre_cliente.isEmpty() && !apellido_cliente.isEmpty() && !comensales.isEmpty() && !mesa.isEmpty() && !nota_extra.isEmpty()
                                    && !nombre_mesero.isEmpty() && !apellido_mesero.isEmpty() && !monto.isEmpty()){

                                SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(PedidoActivity.this, "pedido", null, 1);

                                SQLiteDatabase db = restauranteDB.getReadableDatabase();


                                final String myQueryMesero = "SELECT MAX(id_mesero) FROM mesero";
                                Cursor cursorMesero = db.rawQuery(myQueryMesero, null);
                                cursorMesero.moveToFirst();
                                int lastIdMesero = cursorMesero.getInt(0);
                                cursorMesero.close();

                                //obtener ultimo id de cada tabla para insertar un pedido
                                final String myQueryCuenta = "SELECT MAX(id_cuenta) FROM cuenta";
                                Cursor cursorCuenta = db.rawQuery(myQueryCuenta, null);
                                cursorCuenta.moveToFirst();
                                int lastIdCuenta = cursorCuenta.getInt(0);
                                cursorCuenta.close();

                                final String myQueryCliente = "SELECT MAX(id_cliente) FROM cliente";
                                Cursor cursorCliente = db.rawQuery(myQueryCliente, null);
                                cursorCliente.moveToFirst();
                                int lastIdCliente = cursorCliente.getInt(0);
                                cursorCliente.close();

                                final String myQueryProducto = "SELECT MAX(id_producto) FROM productos";
                                Cursor cursorProducto = db.rawQuery(myQueryProducto, null);
                                cursorProducto.moveToFirst();
                                int lastIdProducto = cursorProducto.getInt(0);
                                cursorProducto.close();

                                restauranteDB.updatePedido(id);
                                restauranteDB.updateCuenta(lastIdCuenta, monto);
                                restauranteDB.updateProductos(lastIdProducto, plato_entrada, plato_principal, plato_postre, bebida);
                                restauranteDB.updateCliente(lastIdMesero, nombre_cliente, apellido_cliente, nota_extra, comensales, mesa);
                                restauranteDB.updateMesero(lastIdMesero, nombre_mesero, apellido_mesero);

                                //restauranteDB.insertarPedido(lastIdCuenta, lastIdMesero, lastIdCliente, lastIdProducto);
                                setResult(Activity.RESULT_OK);
                                finish();
                                Toast.makeText(PedidoActivity.this, "Pedido editado con exito!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
    }

    public void insertPedido(){
        tilValidate();
        if (!nombre_cliente.isEmpty() && !apellido_cliente.isEmpty() && !comensales.isEmpty() && !mesa.isEmpty() && !nota_extra.isEmpty()
                && !nombre_mesero.isEmpty() && !apellido_mesero.isEmpty() && !monto.isEmpty()){

            plato_entrada = binding.actPlatoEntrada.getText().toString();
            plato_principal = binding.actPlatoPrincipal.getText().toString();
            plato_postre = binding.actPlatoPostre.getText().toString();
            bebida = binding.actBebida.getText().toString();

            //Insert a la base de datos
            restauranteDB.insertarCuenta(monto, fecha);
            restauranteDB.insertarMesero(nombre_mesero, apellido_mesero);
            restauranteDB.insertarCliente(nombre_cliente, apellido_cliente, nota_extra, comensales, mesa);
            restauranteDB.insertarProductos(plato_entrada, plato_principal, plato_postre, bebida);

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

            final String myQueryProducto = "SELECT MAX(id_producto) FROM productos";
            Cursor cursorProducto = db.rawQuery(myQueryProducto, null);
            cursorProducto.moveToFirst();
            int lastIdProducto = cursorProducto.getInt(0);
            cursorProducto.close();

            restauranteDB.insertarPedido(lastIdCuenta, lastIdMesero, lastIdCliente, lastIdProducto);
            setResult(Activity.RESULT_OK);
            finish();
            Toast.makeText(PedidoActivity.this, "Pedido agregado con exito!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(PedidoActivity.this, "Debes de llenar todos los campos!", Toast.LENGTH_SHORT).show();
        }
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