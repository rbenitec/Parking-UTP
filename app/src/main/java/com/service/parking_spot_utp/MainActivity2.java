package com.service.parking_spot_utp;

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

public class MainActivity2 extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        edtEmail = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        Button btn_login = findViewById(R.id.loginButton);

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
                    Toast.makeText(MainActivity2.this, "Por favor, ingrese ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                ApiLogin login = retrofit.create(ApiLogin.class);
                Call<User> call = login.LOGIN_CALL(email, password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            edtEmail.getText().clear();
                            edtPassword.getText().clear();
                            String tokenInter = response.body().getToken();

                            Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                            intent.putExtra("token", tokenInter);
                            startActivity(intent);

                            Toast.makeText(MainActivity2.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity2.this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(MainActivity2.this, "LO SENTIMOS HUBO UN ERROR, INTENTELO DE NUEVO", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}