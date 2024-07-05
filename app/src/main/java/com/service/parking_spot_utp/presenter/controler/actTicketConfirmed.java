package com.service.parking_spot_utp.presenter.controler;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.dto.ParkingTicketDto;

public class actTicketConfirmed extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticket_confirmation);

        //userInfoTextView = findViewById(R.id.userInfoTextView);
        //placeIdTextView = findViewById(R.id.placeIdTextView);
        //ticketNumberTextView = findViewById(R.id.ticketNumberTextView);

        Intent intent = getIntent();
        ParkingTicketDto parkingDTO = (ParkingTicketDto) intent.getSerializableExtra("parkingDTO");
        String fullName = intent.getStringExtra("fullName");
        Integer ticketNumber = intent.getIntExtra("ticketNumber", 0);

        if (parkingDTO != null) {
            //userInfoTextView.setText("User: " + fullName + "\nUsername: " + parkingDTO.getUsername());
            //placeIdTextView.setText("Place ID: " + parkingDTO.getPlaceId());
            //ticketNumberTextView.setText("Ticket Number: " + ticketNumber);
        }
    }
}
