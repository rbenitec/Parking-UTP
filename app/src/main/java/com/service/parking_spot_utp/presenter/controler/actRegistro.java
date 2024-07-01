package com.service.parking_spot_utp.presenter.controler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.dto.RegisterRequest;
import com.service.parking_spot_utp.model.entity.User;
import com.service.parking_spot_utp.model.entity.Vehicle;
import com.service.parking_spot_utp.presenter.connection.RetrofitClient;
import com.service.parking_spot_utp.presenter.service.ApiRegister;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class actRegistro extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtPlate;
    private EditText edtTipo;
    private EditText edtModelo;

    private static final String TAG = "actRegistro";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form);

        edtUsername = findViewById(R.id.usuario_register);
        edtPassword = findViewById(R.id.password_register);
        edtPlate = findViewById(R.id.plate_register);
        edtTipo = findViewById(R.id.tipo_register);
        edtModelo = findViewById(R.id.modelo_register);
        Button btnRegister = findViewById(R.id.btnRegister);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = RetrofitClient.getClient();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();
                String plate = edtPlate.getText().toString().trim();
                String tipo = edtTipo.getText().toString().trim();
                String modelo = edtModelo.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty() || plate.isEmpty() || tipo.isEmpty() || modelo.isEmpty()) {
                    Toast.makeText(actRegistro.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                Vehicle vehicle = new Vehicle(plate, modelo, tipo);

                RegisterRequest registerRequest = new RegisterRequest(username, password, vehicle);

                ApiRegister register = retrofit.create(ApiRegister.class);

                Call<User> call = register.REGISTER_CALL(registerRequest);

                System.out.println(call);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            System.out.println(response.isSuccessful());
                            User user = response.body();
                            Toast.makeText(actRegistro.this, "Registro exitoso. Por favor, inicie sesión.", Toast.LENGTH_LONG).show();
                            edtUsername.getText().clear();
                            edtPassword.getText().clear();
                            edtPlate.getText().clear();
                            edtTipo.getText().clear();
                            edtModelo.getText().clear();

                            startActivity(new Intent(actRegistro.this, actLogin.class));
                        } else {
                            Log.d(TAG, "Error en el registro: " + response.message());
                            Toast.makeText(actRegistro.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(actRegistro.this, "Error en el registro", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
