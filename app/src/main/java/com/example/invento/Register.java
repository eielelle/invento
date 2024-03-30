package com.example.invento;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.invento.utils.DB;
import com.example.invento.entities.User;

public class Register extends AppCompatActivity {
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        this.etUsername = findViewById(R.id.rUsername);
        this.etPassword = findViewById(R.id.rPassword);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void register(View view) {
        DB db = DB.getInstance(this);
        Toast toast = Toast.makeText(this, "Registered", Toast.LENGTH_SHORT);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                User user = new User();
                user.username = etUsername.getText().toString();
                user.password = etPassword.getText().toString();

                try {
                    db.authDao().Register(user);
                    toast.show();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                } catch (Exception e) {
                    toast.setText("Something went wrong");
                    toast.show();
                }
            }
        });
    }
}