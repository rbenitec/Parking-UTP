package com.service.parking_spot_utp.presenter.controler;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.service.parking_spot_utp.R;

public class actQR extends AppCompatActivity {

    private int idSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_view);

        idSlot = getIntent().getIntExtra("idSlot", 0);

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

                Intent intent = new Intent();
                intent.putExtra("qrResult", result.getContents());
                setResult(RESULT_OK, intent);
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
