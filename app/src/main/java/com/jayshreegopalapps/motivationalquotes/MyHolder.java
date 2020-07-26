package com.jayshreegopalapps.motivationalquotes;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyHolder extends RecyclerView.ViewHolder {
    TextView text;
    LinearLayout parent;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.text_custom_category_view);
        parent = itemView.findViewById(R.id.categoryLinearLayout);
    }
}
