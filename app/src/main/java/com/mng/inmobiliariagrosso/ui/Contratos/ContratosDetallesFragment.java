package com.mng.inmobiliariagrosso.ui.Contratos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mng.inmobiliariagrosso.databinding.FragmentContratosDetallesBinding;
import com.mng.inmobiliariagrosso.modelo.Contrato;

public class ContratosDetallesFragment extends Fragment {

    private ContratosDetallesViewModel cViewModel;
    private FragmentContratosDetallesBinding binding;


    public static ContratosDetallesFragment newInstance() {
        return new ContratosDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cViewModel = new ViewModelProvider(this).get(ContratosDetallesViewModel.class);
        binding = FragmentContratosDetallesBinding.inflate(inflater, container,false);
        View root = binding.getRoot();

        cViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato c) {
                // TODO: cargar datos de contrato en la vista
                Log.d("salida",c.toString());
                binding.tvId.setText(String.valueOf(c.getIdContrato()));
                binding.tvDesde.setText(c.getFechaInicio());
                binding.tvHasta.setText(c.getFechaFin());
                binding.tvMonto.setText("$"+c.getInmueble().getPrecio());
                binding.tvInquilino.setText(c.getInquilino().getNombre()+" "+c.getInquilino().getApellido());
                binding.tvInmueble.setText(c.getInmueble().getDireccion());
            }
        });
/*
        binding.btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cViewModel.openPagos(root);
            }
        });
*/
        cViewModel.setContrato(getArguments());
        return root;
    }

}