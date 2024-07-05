package com.service.parking_spot_utp.presenter.controler;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.service.parking_spot_utp.R;

public class actProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);

        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        TextView userNameTextView = findViewById(R.id.userNameTextView);
        TextView userRoleTextView = findViewById(R.id.userRoleTextView);
        TextView vehicleTypeTextView = findViewById(R.id.vehicleTypeTextView);
        TextView vehicleModelTextView = findViewById(R.id.vehicleModelTextView);
        TextView vehiclePlacaTextView = findViewById(R.id.vehiclePlacaTextView);
        ImageView qrCodeImageView = findViewById(R.id.qrCodeImageView);

        // Recuperar datos de SharedPreferences
        String username = sharedPreferences.getString("username", "");
        String names = sharedPreferences.getString("names", "");
        String vehiclePlaca = sharedPreferences.getString("vehiclePlaca", "");
        String vehicleModelo = sharedPreferences.getString("vehicleModelo", "");
        String vehicleType = sharedPreferences.getString("vehicleType", "");
        String qrCodeString = sharedPreferences.getString("qr", "");

        // Mostrar los datos en el layout
        userNameTextView.setText(names);
        userRoleTextView.setText(username);
        vehicleTypeTextView.setText(vehicleType);
        vehicleModelTextView.setText(vehicleModelo);
        vehiclePlacaTextView.setText(vehiclePlaca);

        // Decodificar la cadena QR y establecerla en el ImageView
        if (!qrCodeString.isEmpty()) {
            Bitmap qrBitmap = decodeBase64ToBitmap(qrCodeString);
            qrCodeImageView.setImageBitmap(qrBitmap);
        }
    }

    private Bitmap decodeBase64ToBitmap(String base64Str) {
        try {
            byte[] decodedBytes = Base64.decode(base64Str, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return null;
        }
    }
}
