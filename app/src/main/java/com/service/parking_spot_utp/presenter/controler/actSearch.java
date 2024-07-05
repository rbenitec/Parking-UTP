package com.service.parking_spot_utp.presenter.controler;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.entity.ticketResponse;
import com.service.parking_spot_utp.presenter.connection.RetrofitParking;
    import com.service.parking_spot_utp.presenter.service.ApiSearch;
import com.service.parking_spot_utp.view.TicketAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class actSearch extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TicketAdapter ticketAdapter;
    private List<ticketResponse> ticketList;
    private ApiSearch apiService;
    private EditText plateNumberEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_search_admin);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        plateNumberEditText = findViewById(R.id.plate_number);
        ImageButton searchButton = findViewById(R.id.search_button);

        // Initialize Retrofit
        apiService = RetrofitParking.getParking().create(ApiSearch.class);

        // Fetch tickets
        fetchTickets();

        // Boton buscar
        searchButton.setOnClickListener(v -> {
            String query = plateNumberEditText.getText().toString().trim();
            ticketAdapter.filter(query);
        });

        // TextWatcher
        plateNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    ticketAdapter.filter("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void fetchTickets() {
        apiService.getAvailableTickets().enqueue(new Callback<List<ticketResponse>>() {
            @Override
            public void onResponse(Call<List<ticketResponse>> call, Response<List<ticketResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ticketList = response.body();
                    ticketAdapter = new TicketAdapter(ticketList, new TicketAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(ticketResponse ticket) {

                            Toast.makeText(actSearch.this, "Clicked: " + ticket.getPlaca(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    recyclerView.setAdapter(ticketAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<ticketResponse>> call, Throwable t) {
                Toast.makeText(actSearch.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
