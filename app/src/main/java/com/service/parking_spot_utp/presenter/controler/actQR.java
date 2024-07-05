package com.service.parking_spot_utp.presenter.controler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.dto.ParkingTicketDto;
import com.service.parking_spot_utp.model.entity.QRResponse;
import com.service.parking_spot_utp.model.entity.ticketGenerated;
import com.service.parking_spot_utp.presenter.connection.RetrofitClient;
import com.service.parking_spot_utp.presenter.service.ApiTicket;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class actQR extends AppCompatActivity {

    private int idSlot;
    private String basement;
    private String space;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_view);

        idSlot = getIntent().getIntExtra("idSlot", 0);
        basement = getIntent().getStringExtra("basement");
        space = getIntent().getStringExtra("space");

        TextView idSlotTextView = findViewById(R.id.idSlotTextView);
        idSlotTextView.setText("ID del Slot: " + idSlot);

        ImageView qrImageView = findViewById(R.id.qrImageView);
        qrImageView.setOnClickListener(v -> initiateQrCodeScan());
    }

    private void initiateQrCodeScan() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("Scan a QR code");
        integrator.setCameraId(0);
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(false);
        integrator.setCaptureActivity(CaptureActivityPortrait.class);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();

                // Deserializar JSON
                Gson gson = new Gson();
                QRResponse qrResponse = gson.fromJson(result.getContents(), QRResponse.class);

                // Crear DTO
                ParkingTicketDto parkingDTO = new ParkingTicketDto(qrResponse.getClient().getUsername(), qrResponse.getClient().getId(), idSlot);

                // Enviar datos a la API
                sendParkingTicketToApi(parkingDTO, qrResponse);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void sendParkingTicketToApi(ParkingTicketDto parkingDTO, QRResponse qrResponse) {
        Retrofit retrofit = RetrofitClient.getClient();
        ApiTicket apiService = retrofit.create(ApiTicket.class);

        Call<ticketGenerated> call = apiService.createParkingTicket(parkingDTO);
        call.enqueue(new Callback<ticketGenerated>() {
            @Override
            public void onResponse(Call<ticketGenerated> call, Response<ticketGenerated> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ticketGenerated apiResponseTicketGenerado = response.body();
                    if (apiResponseTicketGenerado.getTicketNumber() != null) {
                        Toast.makeText(actQR.this, "Ticket creado exitosamente", Toast.LENGTH_LONG).show();

                        // Enviar datos a la siguiente vista
                        Intent intent = new Intent(actQR.this, actTicketConfirmed.class);
                        intent.putExtra("parkingDTO", parkingDTO);
                        intent.putExtra("fullName", qrResponse.getClient().getNames() + " " + qrResponse.getClient().getLastname());
                        intent.putExtra("ticketNumber", apiResponseTicketGenerado.getTicketNumber());
                        intent.putExtra("basement", basement);
                        intent.putExtra("space", space);
                        startActivity(intent);
                    } else {
                        Toast.makeText(actQR.this, "Error al crear ticket", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(actQR.this, "Error en la respuesta del servidor", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ticketGenerated> call, Throwable t) {
                Toast.makeText(actQR.this, "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
