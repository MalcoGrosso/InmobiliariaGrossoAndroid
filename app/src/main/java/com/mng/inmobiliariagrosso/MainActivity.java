package com.mng.inmobiliariagrosso;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.mng.inmobiliariagrosso.databinding.ActivityMainBinding;
import com.mng.inmobiliariagrosso.modelo.Propietario;
import com.mng.inmobiliariagrosso.request.ApiRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        setHeader(navigationView);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        if("com.mng.inmobiliariagrosso:id/nav_Inmuebles".equals(navController.getCurrentBackStackEntry().getDestination().getDisplayName())){
            navController.navigate(R.id.nav_home);
            return true;
        }else{

            return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                    || super.onSupportNavigateUp();
        }

    }
    private void setHeader(NavigationView navigationView) {

        View header = navigationView.getHeaderView(0);
        TextView nombre = header.findViewById(R.id.tvNombre);
        TextView email = header.findViewById(R.id.tvMail);
        SharedPreferences sp= navigationView.getContext().getSharedPreferences("token",0);
        String token =sp.getString("token","");

        Call<Propietario> propietarioCall = ApiRetrofit.getServiceInmobiliaria().obtenerPerfil(token);
        propietarioCall.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    Propietario p = response.body();

                    nombre.setText(p.getNombre());
                    email.setText(p.getEmail());

                }

            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {

            }
        });

    }
}