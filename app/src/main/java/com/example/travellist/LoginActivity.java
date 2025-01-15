package com.example.travellist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.travellist.databinding.ActivityLoginBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return;
        }

        binding.loginButton.setOnClickListener(v -> loginUser());
        binding.registerButton.setOnClickListener(v -> registerUser());
    }

    private void loginUser() {
        String email = binding.emailInput.getText().toString().trim();
        String password = binding.passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completați toate câmpurile", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.loginButton.setEnabled(false);
        binding.registerButton.setEnabled(false);

        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                Toast.makeText(this, "Autentificare reușită", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            })
            .addOnFailureListener(e -> {
                binding.loginButton.setEnabled(true);
                binding.registerButton.setEnabled(true);

                String message;
                if (e instanceof FirebaseAuthInvalidUserException) {
                    message = "Nu există niciun cont cu acest email";
                } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    message = "Email sau parolă incorectă";
                } else {
                    message = "Eroare: " + e.getMessage();
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            });
    }

    private void registerUser() {
        String email = binding.emailInput.getText().toString().trim();
        String password = binding.passwordInput.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Completați toate câmpurile", Toast.LENGTH_SHORT).show();
            return;
        }

        binding.loginButton.setEnabled(false);
        binding.registerButton.setEnabled(false);

        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener(authResult -> {
                Toast.makeText(this, "Cont creat cu succes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
                finish();
            })
            .addOnFailureListener(e -> {
                binding.loginButton.setEnabled(true);
                binding.registerButton.setEnabled(true);

                String message;
                if (e instanceof FirebaseAuthWeakPasswordException) {
                    message = "Parola trebuie să aibă cel puțin 6 caractere";
                } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    message = "Adresa de email nu este validă";
                } else {
                    message = "Eroare: " + e.getMessage();
                }
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            });
    }
} 