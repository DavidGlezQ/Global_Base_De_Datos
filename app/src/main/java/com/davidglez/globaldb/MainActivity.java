package com.davidglez.globaldb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.davidglez.globaldb.DataBase.SQLiteDB;
import com.davidglez.globaldb.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final SQLiteDB sqLiteDB = new SQLiteDB(this, "MiniSuper.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


    }

    public void onClickBntInsert(){
        sqLiteDB.insertNewProducts("Leche", "8/21/2021", "LALA", 20);
    }
}