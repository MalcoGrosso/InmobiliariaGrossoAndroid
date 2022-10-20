package com.mng.inmobiliariagrosso.ui.Inmuebles;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.mng.inmobiliariagrosso.R;
import com.mng.inmobiliariagrosso.databinding.CrearInmuebleFragmentBinding;
import com.mng.inmobiliariagrosso.modelo.Inmueble;

import java.util.Base64;

public class CrearInmueble extends Fragment {
    private ImageView imagen1;
    private Inmueble inmueble;
    private static int REQUEST_IMAGE_CAPTURE=1;
    private CrearInmuebleViewModel mViewModel;
    private CrearInmuebleFragmentBinding binding;
    private Context context;
    private Spinner spinner1;
    private Spinner spinner2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CrearInmuebleFragmentBinding.inflate(inflater, container, false);
        View root= binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(CrearInmuebleViewModel.class);
        mViewModel.obtenerTipoInmueble();
        mViewModel.obtenerUsoInmueble();
        inmueble=new Inmueble();
        configView();

        mViewModel.getTipoInmueble().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] inmueble2) {

                spinner1 = root.findViewById(R.id.spTipo);

                Context context = root.getContext();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, inmueble2);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                spinner1.setAdapter(adapter);

                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }});
            }
        });

        mViewModel.getUsoInmueble().observe(getViewLifecycleOwner(), new Observer<String[]>() {
            @Override
            public void onChanged(String[] inmueble2) {

                spinner2 = root.findViewById(R.id.spUso);

                Context context = root.getContext();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, inmueble2);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

                spinner2.setAdapter(adapter);

                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }});
            }
        });


        mViewModel.getInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble2) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("inmuebles",inmueble2);
                Navigation.findNavController(root).navigate(R.id.InmueblesDetallesFragment,bundle);

            }
        });

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto(v);
            }
        });
        binding.BTCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inmueble.setIdInmueble(0);
                inmueble.setAmbientes(Integer.parseInt(binding.ETAmbiente.getText().toString()));
                inmueble.setEstado(binding.cbDisponible.isChecked());
                inmueble.setIdPropietario(0);
                inmueble.setDireccion(binding.ETDirrecion.getText().toString());
                inmueble.setLatitud(binding.ETLatitud.getText().toString());
                inmueble.setLongitud(binding.ETLongitud.getText().toString());
                inmueble.setSuperficie(Integer.parseInt(binding.ETSuperf.getText().toString()));
                inmueble.setPrecio(Double.parseDouble(binding.ETPrecio.getText().toString()));
                inmueble.setTipo(binding.spTipo.getSelectedItem().toString());
                inmueble.setUso(binding.spUso.getSelectedItem().toString());

                mViewModel.crearInmueble(inmueble);

            }
        });


        return root;
    }
    public void configView(){
        //imagen1=binding.imageView;


        mViewModel.getFoto().observe(getViewLifecycleOwner(), new Observer<byte []>() {
            @Override
            public void onChanged(byte [] bitmap) {
               // imagen1.setImageBitmap(bitmap);

                    String encoded = Base64.getEncoder().encodeToString(bitmap);

                    inmueble.setImagen(encoded);

                    binding.TVFotoE.setText("Imagen Guardada");
            }
        });
    }


    public void tomarFoto(View v){
//startActivityForResult es otra forma de iniciar una activity, pero esperando desde donde la llamé un resultado
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    //Este método es llamado automáticamente cuando retorna de la cámara.
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mViewModel.respuestaDeCamara(requestCode,resultCode,data,REQUEST_IMAGE_CAPTURE);
    }



}