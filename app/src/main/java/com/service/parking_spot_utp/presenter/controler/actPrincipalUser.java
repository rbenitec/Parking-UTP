package com.service.parking_spot_utp.presenter.controler;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.dto.Lista;
import com.service.parking_spot_utp.model.dto.ParkingCampusDto;
import com.service.parking_spot_utp.presenter.connection.RetrofitParking;
import com.service.parking_spot_utp.presenter.service.ApiParking;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class actPrincipalUser extends AppCompatActivity {

    private Lista parkingList;
    private TextView petitTextView;
    private TextView arequipaTextView;
    private TextView pacificoTextView;
    private ImageView profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        petitTextView = findViewById(R.id.Petit);
        arequipaTextView = findViewById(R.id.Arequipa);
        pacificoTextView = findViewById(R.id.Pacifico);
        profileIcon = findViewById(R.id.profileIcon);

        Retrofit retrofit = RetrofitParking.getParking();
        ApiParking parkingService = retrofit.create(ApiParking.class);

        Call<Lista> call = parkingService.getParkingList();

        call.enqueue(new Callback<Lista>() {
            @Override
            public void onResponse(Call<Lista> call, Response<Lista> response) {
                System.out.println("Response succesfull  " + response.isSuccessful());
                response.body().getParkingCampus().forEach(x -> Log.d(TAG, x.toString()));

                Log.d(TAG, "Response succesfull");

                if (response.isSuccessful()) {
                    parkingList = response.body();
                    for (ParkingCampusDto campusDto : parkingList.getParkingCampus()) {
                        if ("PETITUARS".equals(campusDto.getCampus())) {
                            petitTextView.setText(campusDto.getAvailableSpot().toString());
                        } else if ("AREQUIPA".equals(campusDto.getCampus())) {
                            arequipaTextView.setText(campusDto.getAvailableSpot().toString());
                        } else if ("PACIFICO".equals(campusDto.getCampus())) {
                            pacificoTextView.setText(campusDto.getAvailableSpot().toString());
                        }
                    }
                } else {
                    Log.d(TAG, "Error obteniendo data: " + response.message());
                    Toast.makeText(actPrincipalUser.this, "Error obteniendo data", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<Lista> call, Throwable t) {
                Toast.makeText(actPrincipalUser.this, "Error de conexion", Toast.LENGTH_SHORT).show();

            }
        });

        // Método para ocultar la barra de navegación
        hideSystemUI();

        // Agregar OnClickListener al ImageView
        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(actPrincipalUser.this, actProfile.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}
