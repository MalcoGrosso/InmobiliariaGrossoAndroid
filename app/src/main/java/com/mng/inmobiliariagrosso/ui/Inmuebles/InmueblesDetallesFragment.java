package com.mng.inmobiliariagrosso.ui.Inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mng.inmobiliariagrosso.databinding.FragmentInmueblesDetallesBinding;
import com.mng.inmobiliariagrosso.modelo.Inmueble;

public class InmueblesDetallesFragment extends Fragment {

    private InmueblesDetallesViewModel rViewModel;
    private FragmentInmueblesDetallesBinding binding;

    public static  InmueblesDetallesFragment newInstance() {
        return new InmueblesDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rViewModel = new ViewModelProvider(this).get(InmueblesDetallesViewModel.class);
        binding = FragmentInmueblesDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        rViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                // TODO: cargar datos en las vistas
                binding.tvAddress.setText(i.getDireccion());
                binding.tvPrecio.setText(String.valueOf(i.getPrecio()));
                binding.tvTipo.setText(i.getTipo());
                binding.tvUso.setText(i.getUso());
                binding.tvLatitud.setText(i.getLatitud());
                binding.tvLongitud.setText(i.getLongitud());
                binding.tvAmbientes.setText(String.valueOf(i.getAmbientes()));
                binding.cbDisponible.setChecked(i.isEstado());

                Glide.with(root.getContext())
                        .load(i.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
       //                 .encodeQuality(100)
                        .into(binding.ivPhoto);

                binding.cbDisponible.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rViewModel.setDisponible(binding.cbDisponible.isChecked());
                    }
                });

            }
        });
        rViewModel.setInmueble(getArguments());
        return root;
    }
}