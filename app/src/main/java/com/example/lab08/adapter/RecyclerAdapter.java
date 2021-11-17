package com.example.lab08.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab08.DetailActivity;
import com.example.lab08.R;
import com.example.lab08.model.ItemList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder> {

    private List<ItemList> items;
    private List<ItemList> originalItems;
    private RecyclerItemClick itemClick;

    public RecyclerAdapter(List<ItemList> items,RecyclerItemClick itemClick) {
        this.items = items;
        this.itemClick = itemClick;
        this.originalItems = new ArrayList<>();
        originalItems.addAll(items);
    }

    @NonNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list_view, parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerHolder holder, final int position) {
        final ItemList item = items.get(position);
        holder.imgItem.setImageResource(item.getImgResource());
        holder.tvNombre.setText(item.getNombre());
        holder.tvCelular.setText(String.valueOf(item.getCelular()));
        holder.tvEmail.setText(item.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClick.itemClick(item);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
                intent.putExtra("itemDetail", item);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void filter(String strSearch){
        if(strSearch.length()==0){
            items.clear();
            items.addAll(originalItems);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                List<ItemList> collect =items.stream()
                        .filter(i->i.getNombre().toLowerCase().contains(strSearch))
                        .collect(Collectors.toList());
                items.clear();
                items.addAll(collect);
            }
            else {
                items.clear();
                for (ItemList i:originalItems){
                    if(i.getNombre().toLowerCase().contains(strSearch)){
                        items.add(i);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }




    public static class RecyclerHolder extends RecyclerView.ViewHolder{
        private ImageView imgItem;
        private TextView tvNombre;
        private TextView tvCelular;
        private TextView tvEmail;

        public RecyclerHolder(@NonNull View itemView){
            super(itemView);
            imgItem = itemView.findViewById(R.id.imageView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvCelular = itemView.findViewById(R.id.tvCelular);
            tvEmail = itemView.findViewById(R.id.tvEmail);

        }
    }
    public interface RecyclerItemClick{
        void itemClick(ItemList item);
    }
}
