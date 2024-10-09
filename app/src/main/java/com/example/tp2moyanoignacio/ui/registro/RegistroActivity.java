package com.example.tp2moyanoignacio.ui.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2moyanoignacio.databinding.ActivityRegistroBinding;
import com.example.tp2moyanoignacio.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroActivityViewModel mv;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mv.getMUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                binding.etDni.setText(usuario.getDNI());
                binding.etNombre.setText(usuario.getNombre());
                binding.etApellido.setText(usuario.getApellido());
                binding.etCorreo.setText(usuario.getCorreo());
                binding.etPassword.setText(usuario.getPassword());
            }
        });
        Intent i = getIntent();
        boolean booleano = i.getBooleanExtra("login", false);
        mv.leerDatos(booleano);
        binding.btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = binding.etDni.getText().toString();
                String nombre = binding.etNombre.getText().toString();
                String apellido = binding.etApellido.getText().toString();
                String correo = binding.etCorreo.getText().toString();
                String password = binding.etPassword.getText().toString();
                mv.guardar(new Usuario(dni, nombre, apellido, correo, password));
            }
        });
    }
}