package com.service.parking_spot_utp.presenter.controler;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.entity.ParkingResponse;
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
    private int idSlot;
    private String basement;
    private String space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view_admin);

        TextView totalSlotsTextView = findViewById(R.id.totalSlotsTextView);
        TextView availableSlotsTextView = findViewById(R.id.availableSlotsTextView);
        TextView basementTextView = findViewById(R.id.basementTextView);
        TextView spaceTextView = findViewById(R.id.spaceTextView);
        Button continuarButton = findViewById(R.id.continuarButton);
        ImageView searchImageView = findViewById(R.id.searchImageView);

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
                        spaceTextView.setText(String.valueOf(parkingResponse.getSeries()));

                        // Guardar idSlot, basement y space
                        idSlot = parkingResponse.getIdSlot();
                        basement = parkingResponse.getBasement();
                        space = String.valueOf(parkingResponse.getSeries());
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

        // Click
        continuarButton.setOnClickListener(v -> {
            if (idSlot != 0 && basement != null && space != null) {
                showIdSlotPopup(basement + " - " + space);
            } else {
                Toast.makeText(actPrincipal.this, "Información del slot no está disponible", Toast.LENGTH_SHORT).show();
            }
        });

        searchImageView.setOnClickListener(v -> {
            Intent searchIntent = new Intent(actPrincipal.this, actSearch.class);
            startActivity(searchIntent);
        });
    }

    private void showIdSlotPopup(String slotInfo) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.parking_asignation_confirmation, null);

        TextView dialogMessage = dialogView.findViewById(R.id.dialog_message);
        dialogMessage.setText("¿Estás seguro de asignar el espacio " + slotInfo + "?");

        Dialog dialog = new Dialog(this);
        dialog.setContentView(dialogView);

        Button cancelButton = dialogView.findViewById(R.id.cancel_button);
        Button confirmButton = dialogView.findViewById(R.id.confirm_button);

        cancelButton.setOnClickListener(v -> dialog.dismiss());
        confirmButton.setOnClickListener(v -> {
            // Acción de confirmación
            Intent intent = new Intent(actPrincipal.this, actQR.class);
            intent.putExtra("idSlot", idSlot);
            intent.putExtra("basement", basement);
            intent.putExtra("space", space);
            startActivity(intent);
            dialog.dismiss();
        });

        dialog.show();
    }
}
