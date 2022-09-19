package com.mng.inmobiliariagrosso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mng.inmobiliariagrosso.MainActivity;
import com.mng.inmobiliariagrosso.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel viewModel;
    private EditText etMail, etPassword;
    private Button btLogin;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initializeViews();
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())
                .create(LoginViewModel.class);
        viewModel.getErrorVisibility().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                tvError.setVisibility(visibility);
            }
        });

        //Button listener
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.login(
                        etMail.getText().toString(),
                        etPassword.getText().toString()
                );
            }
        });

        etMail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                tvError.setVisibility(View.INVISIBLE);
            }
        });


    }

    private void initializeViews() {
        etMail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btLogin = findViewById(R.id.btLogin);
        tvError = findViewById(R.id.tvError);

    }

}