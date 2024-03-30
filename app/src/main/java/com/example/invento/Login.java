package com.example.invento;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.invento.entities.User;
import com.example.invento.utils.DB;

public class Login extends AppCompatActivity {
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        this.etUsername = findViewById(R.id.lUsername);
        this.etPassword = findViewById(R.id.lPassword);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void login(View view) {
        DB db = DB.getInstance(this);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    User user = db.authDao().GetUser(etUsername.getText().toString());
                    if(user.password.equals(etPassword.getText().toString())) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                } catch (Exception e) {
                    Log.d("ERROR", "err");
                }
            }
        });
    }
}