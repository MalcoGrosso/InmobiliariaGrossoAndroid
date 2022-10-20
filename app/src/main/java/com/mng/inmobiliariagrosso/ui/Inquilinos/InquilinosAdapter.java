/*
package com.mng.inmobiliariagrosso.ui.Inquilinos;

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

public class InquilinosAdapter extends RecyclerView.Adapter<InquilinosAdapter.MiViewHolder> {



    private LayoutInflater layoutInflater;
    private Context context;
    private List<Inmueble> inmuebles;
    private View root;


    public InquilinosAdapter(
            View root,
            List<Inmueble> inmuebles
    ) {
        this.root = root;
        this.layoutInflater = LayoutInflater.from(root.getContext());
        this.context = root.getContext();
        this.inmuebles = inmuebles;
        this.api = ApiClient.getApi();
    }

    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_inmueble, parent, false);
        return new MiViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Inmueble i = inmuebles.get(position);
        holder.tvDireccion.setText(i.getDireccion());
        holder.tvDetalles.setText(api.obtenerInquilino(i).getNombre()+" "+api.obtenerInquilino(i).getApellido());
        Glide.with(root.getContext())
                .load(i.getImagen())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFoto);
        holder.cvInmuebles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles", i);
                Navigation.findNavController(root).navigate(R.id.inquilinosDetallesFragment, bundle);
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
*/