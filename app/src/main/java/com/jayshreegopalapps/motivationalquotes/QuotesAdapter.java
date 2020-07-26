package com.jayshreegopalapps.motivationalquotes;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class QuotesAdapter extends RecyclerView.Adapter<QuotesHolder> {
    private Context context;
    private ArrayList<QuotesModel> arrayList;
    public QuotesAdapter(Context applicationContext, ArrayList<QuotesModel> arrayList) {
        this.context = applicationContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public QuotesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_quotes_view, null);
        return new QuotesHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesHolder holder, int position) {
        holder.textView.setText(arrayList.get(position).quoteText);
        holder.layout.setBackgroundResource(arrayList.get(position).colorId);
        holder.author.setText(arrayList.get(position).authorName);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
