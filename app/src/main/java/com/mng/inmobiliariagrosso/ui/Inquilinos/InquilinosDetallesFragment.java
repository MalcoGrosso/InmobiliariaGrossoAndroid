package com.mng.inmobiliariagrosso.ui.Inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mng.inmobiliariagrosso.databinding.FragmentInquilinosDetallesBinding;
import com.mng.inmobiliariagrosso.modelo.Inquilino;

public class InquilinosDetallesFragment extends Fragment {

    private InquilinosDetallesViewModel tViewModel;
    private FragmentInquilinosDetallesBinding binding;

    public static InquilinosDetallesFragment newInstance() {
        return new InquilinosDetallesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        tViewModel = new ViewModelProvider(this).get(InquilinosDetallesViewModel.class);
        binding = FragmentInquilinosDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tViewModel.getInquilinoMutable().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino i) {
                // TODO: cargar datos en las vistas
                binding.tvNombreApellido.setText(i.getNombre()+" "+i.getApellido());
                binding.tvDni.setText(String.valueOf(i.getDNI()));
                binding.tvTel.setText(i.getTelefono());
                binding.tvEmailAddress.setText(i.getEmail());
                binding.tvTrabajo.setText(i.getLugarDeTrabajo());
                binding.tvNombreGarante.setText(i.getNombreGarante());
                binding.tvTelGarante.setText(i.getTelefonoGarante());
            }
        });

        tViewModel.setInquilino(getArguments());
        return root;
    }
}