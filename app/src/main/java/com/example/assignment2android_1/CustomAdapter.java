package com.example.assignment2android_1;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final ArrayList<Item> dataSet;
    private final Context context;

    public CustomAdapter(ArrayList<Item> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textAmount;
        TextView textPrice;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView);
            textAmount = itemView.findViewById(R.id.textAmount);
            imageView = itemView.findViewById(R.id.imageView);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardrow, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {
        final Item dataModel = dataSet.get(position);
        holder.textViewName.setText(dataModel.getName());
        holder.textAmount.setText("amount left: "+String.valueOf(dataModel.getAmount()));
        holder.textPrice.setText(String.valueOf(dataModel.getPrice())+" ₪");

        //holder.imageView.setImageResource();

        // Set click listener for the item
        holder.itemView.setOnClickListener(v -> showPopupWithInfo(dataModel));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private void showPopupWithInfo(Item dataModel) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Full description");
        String message = "<b>Name:</b> " ;

        builder.setMessage(HtmlCompat.fromHtml(message, HtmlCompat.FROM_HTML_MODE_COMPACT));
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}


