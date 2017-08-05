package com.telivergrocerydriver;


import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterProducts extends RecyclerView.Adapter<AdapterProducts.MyViewHolder> {

    private Context context;

    private ArrayList<Model> listProducts;

    public AdapterProducts(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_medical_products, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return listProducts.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Model model = listProducts.get(position);
        holder.txtName.setText(model.getName());
        holder.txtApproxPrice.setText(model.getApproxPrice());
        holder.txtFinalPrice.setText(model.getFinalPrice());
        holder.imgProduct.setImageResource(model.getImage());
    }

    public void setData(ArrayList<Model> listProducts) {
        this.listProducts = listProducts;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtApproxPrice, txtFinalPrice;

        private ImageView imgProduct;

        public MyViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtProduct);
            txtApproxPrice = (TextView) itemView.findViewById(R.id.txtApproxPrice);
            txtFinalPrice = (TextView) itemView.findViewById(R.id.txtFinalPrice);
            imgProduct = (ImageView) itemView.findViewById(R.id.imgProduct);
            txtApproxPrice.setPaintFlags(txtApproxPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}
