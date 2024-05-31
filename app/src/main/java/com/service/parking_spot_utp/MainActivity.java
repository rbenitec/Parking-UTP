package com.service.parking_spot_utp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity<actLogin> extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private EditText placa1holder;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        edtEmail = findViewById(R.id.usuarioholder);
        placa1holder = findViewById(R.id.placa1holder);
        edtPassword = findViewById(R.id.passholder);
        Button btn_login = findViewById(R.id.button);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8110/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor, ingrese ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                ApiRegister register = retrofit.create(ApiRegister.class);
                Call<User> call = register.REGISTER_CALL(email, password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            edtEmail.getText().clear();
                            edtPassword.getText().clear();
                            String tokenInter = response.body().getToken();

                            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                            intent.putExtra("token", tokenInter);
                            startActivity(intent);

                            Toast.makeText(MainActivity.this, "Registrado correctamente!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Error, intentelo de nuevo.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "LO SENTIMOS HUBO UN ERROR, INTENTELO DE NUEVO", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}