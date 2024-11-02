package com.example.appuserlist;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.appuserlist.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private UserViewModelActivity userVW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Mi VM para garantizar que los datos sobrevivan a cambios de config
        userVW= new ViewModelProvider(this).get(UserViewModelActivity.class);
        tarea();

    }

    public void tarea() {
        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etNombre.getText().toString();
                int edad;
                if (!nombre.isEmpty() && !binding.etEdad.getText().toString().isEmpty()) {
                    try {
                        edad = Integer.parseInt(binding.etEdad.getText().toString());
                        Usuario usuario = new Usuario(nombre, edad);
                        userVW.addUser(usuario);
                        binding.etNombre.setText("");
                        binding.etEdad.setText("");
                    } catch (NumberFormatException e) {
                        binding.tvVerUsuario.setText("Error de formato en la edad");
                    }
                } else {
                    binding.tvVerUsuario.setText("ERROR");
                }
                binding.etNombre.setText("");
                binding.etEdad.setText("");
            }
        });
        binding.btVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Usuario> lista = userVW.getUserList();
                String texto = "";
                for (Usuario u : lista) {
                    texto += u.getNombre() + " " + u.getEdad() + " Años"+"\n";
                }
                binding.tvVerUsuario.setText(texto);
            }

        });

    }
}