package com.mng.inmobiliariagrosso.ui.Inmuebles;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mng.inmobiliariagrosso.R;
import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.MiViewHolder> {


    private LayoutInflater layoutInflater;
    private Context context;
    private List<Inmueble> inmuebles;
    private View root;

    public InmueblesAdapter(
            View root,
            List<Inmueble> inmuebles
    ) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.inmuebles = inmuebles;
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inmueble, parent, false);
        return new MiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Inmueble inmu = inmuebles.get(position);
        holder.tvDireccion.setText(inmu.getDireccion());
        holder.tvDetalles.setText("$"+inmu.getPrecio());
        Glide.with(context)
                .load(inmu.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFoto);
        holder.cvInmuebles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles", inmu);
                Navigation.findNavController(root).navigate(R.id.InmueblesDetallesFragment, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder {

        private CardView cvInmuebles;
        private TextView tvDetalles, tvDireccion;
        private ImageView ivFoto;

        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            cvInmuebles = itemView.findViewById(R.id.cvInmuebles);
            ivFoto = itemView.findViewById(R.id.ivFoto);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            tvDetalles = itemView.findViewById(R.id.tvDetalles);
        }
    }
}
