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
                "apellido_cliente 'varchar', nota_extra 'varchar', comensales 'int', mesa 'int')");

        //Tabla cuenta
        sqLiteDatabase.execSQL("create table IF NOT EXISTS cuenta(id_cuenta INTEGER PRIMARY KEY AUTOINCREMENT, monto_cuenta 'float', " +
                "fecha_pedido 'varchar')");

        //Table categoria de productos
        sqLiteDatabase.execSQL("create table IF NOT EXISTS categoria_productos(id_categoria_producto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre_categoria 'varchar', id_producto 'int')");

        //Table productos
        sqLiteDatabase.execSQL("create table IF NOT EXISTS productos(id_producto INTEGER PRIMARY KEY AUTOINCREMENT, nombre_producto 'varchar')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists pedido");
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
        long result = sqLiteDatabase.insert("mesero", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nuevo cliente
    public boolean insertarCliente(String nombre_cliente, String apellido_cliente, String nota_extra, int comensales, int mesa){
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
    public boolean insertarCuenta(float monto_cuenta, String fecha_pedido){
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

    //Insertar nuevo
    public boolean insertarCategoriaProductos(String nombre_categoria, int id_producto){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_categoria", nombre_categoria);
        values.put("id_producto", id_producto);
        long result = sqLiteDatabase.insert("categoria_productos", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }

    //Insertar nuevo
    public boolean insertarProducto(String nombre_producto){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre_producto", nombre_producto);
        long result = sqLiteDatabase.insert("productos", null, values);
        if (result == -1){
            return false;
        } else {
            return true;
        }
    }
}
