package com.service.parking_spot_utp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.service.parking_spot_utp.R;
import com.service.parking_spot_utp.model.entity.ticketResponse;

import java.util.ArrayList;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {
    private List<ticketResponse> ticketList;
    private List<ticketResponse> ticketListFull; // Copia completa de la lista para el filtrado
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(ticketResponse ticket);
    }

    public static class TicketViewHolder extends RecyclerView.ViewHolder {
        public TextView plateNumber, date, time;

        public TicketViewHolder(View view, final OnItemClickListener listener, final List<ticketResponse> tickets) {
            super(view);
            plateNumber = view.findViewById(R.id.tv_plate_number);
            date = view.findViewById(R.id.tv_date);
            time = view.findViewById(R.id.tv_time);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(tickets.get(position));
                    }
                }
            });
        }
    }

    public TicketAdapter(List<ticketResponse> ticketList, OnItemClickListener listener) {
        this.ticketList = ticketList;
        this.ticketListFull = new ArrayList<>(ticketList); // Inicializar la lista completa
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(itemView, listener, ticketList);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        ticketResponse ticket = ticketList.get(position);
        holder.plateNumber.setText(ticket.getPlaca());
        holder.date.setText(ticket.getDate());
        holder.time.setText(ticket.getHour());
    }

    @Override
    public int getItemCount() {
        return ticketList.size();
    }

    public void filter(String text) {
        ticketList.clear();
        if (text.isEmpty()) {
            ticketList.addAll(ticketListFull);
        } else {
            text = text.toLowerCase();
            for (ticketResponse ticket : ticketListFull) {
                if (ticket.getPlaca().toLowerCase().contains(text)) {
                    ticketList.add(ticket);
                }
            }
        }
        notifyDataSetChanged();
    }
}
