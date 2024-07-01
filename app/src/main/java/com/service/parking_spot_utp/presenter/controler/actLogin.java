package com.service.parking_spot_utp.presenter.controler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.presenter.connection.RetrofitClient;
import com.service.parking_spot_utp.presenter.service.ApiLogin;
import com.service.parking_spot_utp.model.dto.LoginRequest;
import com.service.parking_spot_utp.model.entity.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class actLogin extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private static final String TAG = "actLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        edtUsername = findViewById(R.id.email);
        edtPassword = findViewById(R.id.password);
        Button btnLogin = findViewById(R.id.loginButton);
        Button btnRegister = findViewById(R.id.registerButton);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Retrofit retrofit = RetrofitClient.getClient();

        btnRegister.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(actLogin.this, actRegistro.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(actLogin.this, "Por favor, ingrese ambos campos", Toast.LENGTH_SHORT).show();
                    return;
                }


                ApiLogin login = retrofit.create(ApiLogin.class);
                LoginRequest loginRequest = new LoginRequest(username, password);
                Call<User> call = login.LOGIN_CALL(loginRequest);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if (response.isSuccessful() && response.body() != null) {

                            User user = response.body();
                            System.out.println(user);
                            if (user.isValid()) {

                                edtUsername.getText().clear();
                                edtPassword.getText().clear();

                                //intent.putExtra("username", user.getUsername());
                                //intent.putExtra("password", user.getPassword());

                                startActivity(new Intent(actLogin.this, actPrincipalUser.class));


                                Toast.makeText(actLogin.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(actLogin.this, user.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.d(TAG, "Error en las credenciales: " + response.message());
                            Toast.makeText(actLogin.this, "Error en las credenciales", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(actLogin.this, "LO SENTIMOS HUBO UN ERROR, INTENTELO DE NUEVO", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, "Error en la solicitud de login", t);
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}
