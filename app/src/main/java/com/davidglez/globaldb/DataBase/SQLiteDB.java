package com.davidglez.globaldb.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteDB extends SQLiteOpenHelper {

    public SQLiteDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "MiniSuper.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Table products
        sqLiteDatabase.execSQL("create table IF NOT EXISTS products(id_product INTEGER PRIMARY KEY AUTOINCREMENT, name_product 'varchar', " +
                "date_registration 'varchar', seller 'varchar', product_price 'varchar')");

        //Table seller
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS seller(id_seller INTEGER PRIMARY KEY AUTOINCREMENT, name_seller 'varchar', " +
                "id_product 'int')");

        //Table price
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS price(id_price INTEGER PRIMARY KEY AUTOINCREMENT, product_price 'int')");

        //Table liquids
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS liquids(id_liquid INTEGER PRIMARY KEY AUTOINCREMENT, name_liquid 'varchar', " +
                "product_brand 'varchar', id_price 'int', id_product 'int')");

        //Table dairy products
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS dairy_products(id_dairy_products INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "name_dairy_product 'varchar', product_brand 'varchar', id_price 'int', id_product 'int')");

        //Table meats
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS meats(id_meats INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "name_meats 'varchar', product_brand 'varchar', id_price 'int', id_product 'int')");

        //Table fruits and vegetables
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS fruits_vegetables(id_fruits_vegetables INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "name_fuits_vegetables 'varchar', product_brand 'varchar', id_price 'int', id_product 'int')");

        //Table medicine
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS fruits_vegetables(id_fruits_vegetables INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "name_fuits_vegetables 'varchar', product_brand 'varchar', id_price 'int', id_product 'int')");

        //Table candies
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS candies(id_cnadies INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "name_candies 'varchar', product_brand 'varchar', id_price 'int', id_product 'int')");

        //Table spices
        sqLiteDatabase.execSQL("create TABLE IF NOT EXISTS spices(id_spice INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                "name_spice 'varchar', product_brand 'varchar', id_price 'int', id_product 'int')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists products");
    }
}
