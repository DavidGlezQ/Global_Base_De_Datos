package com.davidglez.globaldb.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteRestauranteDB extends SQLiteOpenHelper {

    public SQLiteRestauranteDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Restaurante.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tabla pedido
        sqLiteDatabase.execSQL("create table IF NOT EXISTS pedido(id_pedido INTEGER PRIMARY KEY AUTOINCREMENT, id_cuenta 'int', " +
                "id_mesero 'int', id_cliente 'int', id_categoria_producto 'int')");

        //Tabla mesero
        sqLiteDatabase.execSQL("create table IF NOT EXISTS mesero(id_mesero INTEGER PRIMARY KEY AUTOINCREMENT, nombre_mesero 'varchar', " +
                "apellido_mesero 'varchar')");

        //Tabla cliente
        sqLiteDatabase.execSQL("create table IF NOT EXISTS cliente(id_cliente INTEGER PRIMARY KEY AUTOINCREMENT, nombre_cliente 'varchar'," +
                "apellido_cliente 'varchar', nota_extra 'varchar', comensales 'varchar', mesa 'varchar')");

        //Tabla cuenta
        sqLiteDatabase.execSQL("create table IF NOT EXISTS cuenta(id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT, monto_cuenta 'varchar', " +
                "fecha_pedido 'varchar')");

        //Table categoria de productos
        sqLiteDatabase.execSQL("create table IF NOT EXISTS productos(id_producto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "plato_entrada 'varchar', plato_principal 'varchar', plato_postre, bebida 'varchar')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists pedido");
    }

    //Update
    public boolean updatePedido(int id_pedido){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_pedido", id_pedido);
        long result = sqLiteDatabase.update("pedido", values, "id_pedido='" + id_pedido + "'", null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateMesero(int id_pedido, String nombre_mesero, String apellido_mesero){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_mesero", nombre_mesero);
        values.put("apellido_mesero", apellido_mesero);
        long result = sqLiteDatabase.update("mesero", values, "id_mesero='" + id_pedido + "'", null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateCliente(int id_pedido, String nombre_cliente, String apellido_cliente, String nota_extra, String comensales, String mesa){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_cliente", nombre_cliente);
        values.put("apellido_cliente", apellido_cliente);
        values.put("nota_extra", nota_extra);
        values.put("comensales", comensales);
        values.put("mesa", mesa);
        long result = sqLiteDatabase.update("cliente", values, "id_cliente='" + id_pedido + "'", null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateCuenta(int id_pedido, String monto_cuenta){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("monto_cuenta", monto_cuenta);
        long result = sqLiteDatabase.update("cuenta", values, "id_cuenta='" + id_pedido + "'", null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean updateProductos(int id_pedido, String plato_entrada, String plato_principal, String plato_postre, String bebida){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("plato_entrada", plato_entrada);
        values.put("plato_principal", plato_principal);
        values.put("plato_postre", plato_postre);
        values.put("bebida", bebida);
        long result = sqLiteDatabase.update("productos", values, "id_producto='" + id_pedido + "'", null);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nuevo pedido
    public boolean insertarPedido(int id_cuenta, int id_mesero, int id_cliente, int id_categoria_producto){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id_cuenta", id_cuenta);
        values.put("id_mesero", id_mesero);
        values.put("id_cliente", id_cliente);
        values.put("id_categoria_producto", id_categoria_producto);
        long result = sqLiteDatabase.insert("pedido", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nuevo mesero
    public boolean insertarMesero(String nombre_mesero, String apellido_mesero){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_mesero", nombre_mesero);
        values.put("apellido_mesero", apellido_mesero);
        long result = sqLiteDatabase.insert("mesero", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nuevo cliente
    public boolean insertarCliente(String nombre_cliente, String apellido_cliente, String nota_extra, String comensales, String mesa){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_cliente", nombre_cliente);
        values.put("apellido_cliente", apellido_cliente);
        values.put("nota_extra", nota_extra);
        values.put("comensales", comensales);
        values.put("mesa", mesa);
        long result = sqLiteDatabase.insert("cliente", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nuevo cuenta
    public boolean insertarCuenta(String monto_cuenta, String fecha_pedido){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("monto_cuenta", monto_cuenta);
        values.put("fecha_pedido", fecha_pedido);
        long result = sqLiteDatabase.insert("cuenta", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nueva categoria de productos
    public boolean insertarProductos(String plato_entrada, String plato_principal, String plato_postre, String bebida){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("plato_entrada", plato_entrada);
        values.put("plato_principal", plato_principal);
        values.put("plato_postre", plato_postre);
        values.put("bebida", bebida);
        long result = sqLiteDatabase.insert("productos", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }


}
