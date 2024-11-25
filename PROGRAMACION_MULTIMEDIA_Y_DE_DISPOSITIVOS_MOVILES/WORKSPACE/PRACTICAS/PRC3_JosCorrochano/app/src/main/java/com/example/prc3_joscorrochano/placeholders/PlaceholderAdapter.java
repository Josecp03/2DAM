package com.example.prc3_joscorrochano.placeholders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prc3_joscorrochano.R;

public class PlaceholderAdapter extends RecyclerView.Adapter<PlaceholderAdapter.PlaceholderViewHolder> {

    private final int itemCount;

    public PlaceholderAdapter(int itemCount) {
        this.itemCount = itemCount;
    }

    @NonNull
    @Override
    public PlaceholderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.placeholder_item, parent, false);
        return new PlaceholderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceholderViewHolder holder, int position) {
        // Nothing to bind for placeholders
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    static class PlaceholderViewHolder extends RecyclerView.ViewHolder {
        public PlaceholderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
