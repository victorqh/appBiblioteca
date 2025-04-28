package com.vquispeh.appBiblioteca.ui.login;

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
import com.vquispeh.appBiblioteca.domain.usecase.LoginUsuario;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private LoginUsuario loginUC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        appDB db = new appDB(this);
        loginUC = new LoginUsuario(new UsuarioRepoImpl(db));

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegister = findViewById(R.id.tvGoRegister);

        btnLogin.setOnClickListener(v -> {
            String email = etEmail.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();
            if (loginUC.execute(email, pass)) {
                startActivity(new Intent(this,
                        com.vquispeh.appBiblioteca.ui.books.MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Credenciales inválidas",
                        Toast.LENGTH_SHORT).show();
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this,
                    com.vquispeh.appBiblioteca.ui.register.RegisterActivity.class));
        });
    }
}