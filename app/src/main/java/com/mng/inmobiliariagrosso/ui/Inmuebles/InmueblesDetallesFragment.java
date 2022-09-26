package com.mng.inmobiliariagrosso.ui.Inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.mng.inmobiliariagrosso.databinding.FragmentInmueblesDetallesBinding;
import com.mng.inmobiliariagrosso.modelo.Inmueble;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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

        binding.cbDisponible.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rViewModel.setDisponible(b);
            }
        });

        rViewModel.getInmuebleMutable().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                // TODO: cargar datos en las vistas
                binding.tvAddress.setText(i.getDireccion());
                binding.tvPrecio.setText(String.valueOf(i.getPrecio()));
                binding.tvTipo.setText(i.getTipo());
                binding.tvUso.setText(i.getUso());
                binding.tvAmbientes.setText(String.valueOf(i.getAmbientes()));
                binding.tvPropietario.setText(i.getPropietario().getNombre()+" "+i.getPropietario().getApellido());

                binding.cbDisponible.setChecked(i.isEstado());

                Glide.with(root.getContext())
                        .load(i.getImagen())
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivPhoto);
            }
        });
        rViewModel.setInmueble(getArguments());
        return root;
    }
}