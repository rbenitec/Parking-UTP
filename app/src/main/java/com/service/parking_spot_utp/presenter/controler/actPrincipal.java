package com.service.parking_spot_utp.presenter.controler;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.entity.ParkingResponse;
import com.service.parking_spot_utp.presenter.connection.RetrofitClient;
import com.service.parking_spot_utp.presenter.connection.RetrofitParking;
import com.service.parking_spot_utp.presenter.service.ApiParking;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class actPrincipal extends AppCompatActivity {

    private static final String TAG = "actPrincipal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_admin);

        TextView totalSlotsTextView = findViewById(R.id.totalSlotsTextView);
        TextView availableSlotsTextView = findViewById(R.id.availableSlotsTextView);
        TextView basementTextView = findViewById(R.id.basementTextView);
        TextView spaceTextView = findViewById(R.id.spaceTextView);

        Intent intent = getIntent();
        String campus = intent.getStringExtra("campus");

        if (campus != null) {
            Log.d(TAG, "Campus recibido: " + campus);

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            Retrofit retrofit = RetrofitParking.getParking();

            ApiParking apiParking = retrofit.create(ApiParking.class);
            Call<ParkingResponse> call = apiParking.getParkingAvailability(campus);
            call.enqueue(new Callback<ParkingResponse>() {
                @Override
                public void onResponse(Call<ParkingResponse> call, Response<ParkingResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        ParkingResponse parkingResponse = response.body();
                        totalSlotsTextView.setText(String.valueOf(parkingResponse.getTotalSlots()));
                        availableSlotsTextView.setText(String.valueOf(parkingResponse.getAvailableSlots()));
                        basementTextView.setText(parkingResponse.getBasement());
                        spaceTextView.setText(String.valueOf(parkingResponse.getSpace()));
                    } else {
                        Log.d(TAG, "Error en la respuesta de la API: " + response.message());
                        Toast.makeText(actPrincipal.this, "Error al obtener la disponibilidad de estacionamientos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ParkingResponse> call, Throwable t) {
                    Toast.makeText(actPrincipal.this, "Error en la solicitud, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error en la solicitud de disponibilidad de estacionamientos", t);
                    t.printStackTrace();
                }
            });
        } else {
            Log.e(TAG, "Campus no recibido desde el intent");
            Toast.makeText(this, "Error al recibir los datos del campus", Toast.LENGTH_SHORT).show();
        }
    }
}
