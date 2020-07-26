package com.jayshreegopalapps.motivationalquotes;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class QuotesHolder extends RecyclerView.ViewHolder {
    TextView textView;
    LinearLayout layout;
    TextView author;
    public QuotesHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.text_quote);
        layout = itemView.findViewById(R.id.linear_layout_quotes);
        author = itemView.findViewById(R.id.text_author);
    }
}
