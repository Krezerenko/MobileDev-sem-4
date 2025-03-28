package com.example.pract5;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{
    private final List<CustomItem> items;

    public CustomAdapter(List<CustomItem> items)
    {
        this.items = items;
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_third, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.ViewHolder holder, int position)
    {
        CustomItem item = items.get(position);

        holder.textView.setText(item.getText());
        holder.imageView.setImageResource(item.getImageId());
    }

    @Override
    public int getItemCount()
    {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textView;
        private final ImageView imageView;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView = itemView.findViewById(R.id.text_itemThird);
            imageView = itemView.findViewById(R.id.image_itemThird);
        }
    }
}
