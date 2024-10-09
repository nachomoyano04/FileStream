package com.example.tp2moyanoignacio.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2moyanoignacio.model.Usuario;
import com.example.tp2moyanoignacio.request.ApiClient;
import com.example.tp2moyanoignacio.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Usuario> mUsuario;

    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Usuario> getMUsuario(){
        if(mUsuario == null){
            mUsuario = new MutableLiveData<>();
        }
        return mUsuario;
    }

    public void guardar(Usuario usuario){
        if(usuario.getNombre().isEmpty() || usuario.getApellido().isEmpty() || usuario.getDNI().isEmpty() || usuario.getCorreo().isEmpty() || usuario.getPassword().isEmpty()){
            Toast.makeText(context, "Debe completar todos los campos", Toast.LENGTH_SHORT).show();
        }else{
            if(ApiClient.Guardar(context, usuario)){
                Toast.makeText(context, "Guardado correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }else{
                Toast.makeText(context, "No se pudo registrar/editar al usuario", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void leerDatos(boolean booleano){
        if(booleano){
            mUsuario.setValue(ApiClient.leer(context));
        }
    }
}
