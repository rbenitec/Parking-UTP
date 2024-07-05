package com.service.parking_spot_utp.presenter.controler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.dto.ParkingTicketDto;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class actTicketConfirmed extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_confirmation);

        TextView userInfoTextView = findViewById(R.id.datosCompletos);
        TextView placeIdTextView = findViewById(R.id.numEspacio);
        TextView ticketNumberTextView = findViewById(R.id.ticket);
        TextView basementTextView = findViewById(R.id.numSotano);
        TextView horaTextView = findViewById(R.id.horaRegistro);
        TextView fechaTextView = findViewById(R.id.fecha);
        Button btnAceptar = findViewById(R.id.btnAceptar);

        Intent intent = getIntent();
        ParkingTicketDto parkingDTO = (ParkingTicketDto) intent.getSerializableExtra("parkingDTO");
        String fullName = intent.getStringExtra("fullName");
        int ticketNumber = intent.getIntExtra("ticketNumber", 0);
        String basement = intent.getStringExtra("basement");
        String space = intent.getStringExtra("space");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        String currentDate = dateFormat.format(calendar.getTime());
        String currentTime = timeFormat.format(calendar.getTime());

        if (parkingDTO != null) {
            userInfoTextView.setText(fullName);
            placeIdTextView.setText(space);
            ticketNumberTextView.setText("#" + ticketNumber);
            basementTextView.setText(basement);

            horaTextView.setText(currentTime);
            fechaTextView.setText(currentDate);
        }

        btnAceptar.setOnClickListener(v -> {
            Intent newIntent = new Intent(actTicketConfirmed.this, actPrincipal.class);
            startActivity(newIntent);
        });
    }
}
