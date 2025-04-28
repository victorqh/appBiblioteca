package com.vquispeh.appBiblioteca.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.vquispeh.appBiblioteca.R;
import com.vquispeh.appBiblioteca.data.local.appDB;
import com.vquispeh.appBiblioteca.data.local.UsuarioRepoImpl;
import com.vquispeh.appBiblioteca.domain.usecase.RegisterUsuario;

public class RegisterActivity extends AppCompatActivity {
    private EditText etNombre, etEmail, etPassword;
    private RegisterUsuario registerUC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        appDB db = new appDB(this);
        registerUC = new RegisterUsuario(new UsuarioRepoImpl(db));

        etNombre = findViewById(R.id.etNombre);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnReg = findViewById(R.id.btnRegister);
        TextView tvLogin = findViewById(R.id.tvGoLogin);

        btnReg.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (nombre.isEmpty() || email.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos",
                        Toast.LENGTH_SHORT).show();
            } else if (registerUC.execute(nombre, email, pass)) {
                Toast.makeText(this, "Registro exitoso",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,
                        com.vquispeh.appBiblioteca.ui.login.LoginActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Ese email ya existe",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tvLogin.setOnClickListener(v -> finish());
    }
}