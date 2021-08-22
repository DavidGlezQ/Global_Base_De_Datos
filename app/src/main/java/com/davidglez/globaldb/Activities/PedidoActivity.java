package com.davidglez.globaldb.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.davidglez.globaldb.R;
import com.davidglez.globaldb.databinding.ActivityMainBinding;
import com.davidglez.globaldb.databinding.ActivityPedidoBinding;

public class PedidoActivity extends AppCompatActivity {

    private ActivityPedidoBinding binding;
    private String n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPedidoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}