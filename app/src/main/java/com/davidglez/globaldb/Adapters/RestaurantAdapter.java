package com.davidglez.globaldb.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.davidglez.globaldb.Pojos.PedidoPojo;
import com.davidglez.globaldb.R;

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

        }
    }

    public void addPedido(PedidoPojo pedidoPojo){
        mData.add(pedidoPojo);
        this.notifyDataSetChanged();
    }
}
