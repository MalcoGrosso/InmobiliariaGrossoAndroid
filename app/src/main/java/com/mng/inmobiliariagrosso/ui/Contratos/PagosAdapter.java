/*
package com.mng.inmobiliariagrosso.ui.Contratos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mng.inmobiliariagrosso.R;
import com.mng.inmobiliariagrosso.modelo.Pago;
import com.mng.inmobiliariagrosso.request.ApiClient;

import java.util.List;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.MiViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Pago> pagos;
    private View root;
    private ApiClient api;

    public PagosAdapter(
            View root,
            List<Pago> pagos
    ) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.pagos = pagos;
        this.api = ApiClient.getApi();
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_pagos, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Pago p = pagos.get(position);
        holder.tvId.setText("Código de Pago: "+p.getIdPago());
        holder.tvNum.setText("Número de Pago: "+p.getNumero());
        holder.tvIdContrato.setText("Código de Contrato: "+p.getContrato().getIdContrato());
        holder.tvImporte.setText("Importe: $"+p.getImporte());
        holder.tvFecha.setText("Fecha de Pago: "+p.getFechaDePago());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvId, tvNum, tvIdContrato, tvImporte, tvFecha;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvIdPago);
            tvNum = itemView.findViewById(R.id.tvNum);
            tvIdContrato = itemView.findViewById(R.id.tvIdContrato);
            tvImporte = itemView.findViewById(R.id.tvImporte);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }
}
*/