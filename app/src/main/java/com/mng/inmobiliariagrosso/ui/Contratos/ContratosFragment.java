package com.mng.inmobiliariagrosso.ui.Contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mng.inmobiliariagrosso.databinding.FragmentContratosBinding;
import com.mng.inmobiliariagrosso.modelo.Contrato;

import java.util.List;

public class ContratosFragment extends Fragment {

    private ContratosViewModel cViewModel;
    private FragmentContratosBinding binding;
    private ContratosAdapter adapter;
    private RecyclerView recyclerViewLista;

    public static ContratosFragment newInstance() {
        return new ContratosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        cViewModel = new ViewModelProvider(this).get(ContratosViewModel.class);
        binding = FragmentContratosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recyclerViewLista = binding.RVLista;

        cViewModel.getContratosMutable().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                        getContext(),
                        LinearLayoutManager.VERTICAL,
                        false
                );
                recyclerViewLista.setLayoutManager(linearLayoutManager);
                adapter = new ContratosAdapter(root, contratos);
                recyclerViewLista.setAdapter(adapter);
            }

        });
        cViewModel.setInmuebles();

        return root;
    }
}