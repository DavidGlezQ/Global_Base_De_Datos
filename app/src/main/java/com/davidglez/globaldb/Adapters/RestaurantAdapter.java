package com.davidglez.globaldb.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.davidglez.globaldb.DataBase.SQLiteRestauranteDB;
import com.davidglez.globaldb.Pojos.PedidoPojo;
import com.davidglez.globaldb.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder>{

    private List<PedidoPojo> mData = new ArrayList<>();
    private ArrayList<PedidoPojo> pedidoPojos;
    private Context context;

    public RestaurantAdapter(List<PedidoPojo> mData, ArrayList<PedidoPojo> pedidoPojos, Context context) {
        this.mData = mData;
        this.pedidoPojos = pedidoPojos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PedidoPojo pedidoPojo = mData.get(position);
        holder.txtNombreComensal.setText(pedidoPojo.getNombre_cliente());
        holder.txtFecha.setText(pedidoPojo.getFecha());
        holder.txtMesa.setText(pedidoPojo.getMesa());

        holder.ivMenuOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.ivMenuOption);
                popupMenu.inflate(R.menu.menu_recycler_options);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_item_edit:

                                return true;
                            case R.id.menu_item_delete:
                                deletePedido(pedidoPojo);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtFecha, txtNombreComensal, txtMesa;
        private ImageView ivMenuOption;

        public ViewHolder(View itemView) {
            super(itemView);

            txtFecha = itemView.findViewById(R.id.txtFechaReg);
            txtNombreComensal = itemView.findViewById(R.id.txtNombreComensal);
            txtMesa = itemView.findViewById(R.id.txtNumMesa);

            ivMenuOption = itemView.findViewById(R.id.ivOptions);

        }
    }

    public void addPedido(PedidoPojo pedidoPojo){
        mData.add(pedidoPojo);
        this.notifyDataSetChanged();
    }

    public void deletePedido(PedidoPojo pedidoPojo){
        new MaterialAlertDialogBuilder(context, R.style.MaterialAlertDialog_MaterialComponents_Title_Icon)
                .setTitle("Eliminar Pedido")
                .setMessage("Estas seguro de que quieres eliminar este pedido?")
                .setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SQLiteRestauranteDB restauranteDB = new SQLiteRestauranteDB(context, "Restaurante.db", null, 1);
                        SQLiteDatabase sqLiteDatabase = restauranteDB.getReadableDatabase();
                        String id = String.valueOf(pedidoPojo.getId());
                        if (!id.isEmpty()){
                            sqLiteDatabase.delete("mesero", "id_mesero=" + id, null);
                            sqLiteDatabase.delete("cliente", "id_cliente=" + id, null);
                            sqLiteDatabase.delete("cuenta", "id_cuenta=" + id, null);
                            sqLiteDatabase.delete("productos", "id_producto=" + id, null);
                            sqLiteDatabase.delete("pedido", "id_pedido=" + id, null);
                            deleteItem(pedidoPojo);
                            sqLiteDatabase.close();
                        } else {
                            Toast.makeText(context, "No se pudo eliminar el pedido", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("cancelar", null)
                .show();
    }

    public void deleteItem(PedidoPojo pedidoPojo){
        mData.remove(pedidoPojo);
        this.notifyDataSetChanged();
    }
}
