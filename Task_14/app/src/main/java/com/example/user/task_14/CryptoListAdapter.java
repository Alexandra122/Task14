package com.example.user.task_14;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

public class CryptoListAdapter extends RecyclerView.Adapter<CryptoListAdapter.ViewHolder> {
    private List<Datum> mData;
    private ItemClickListener mClickListener;
    CryptoListAdapter(List<Datum> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.crypto_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Datum datum = mData.get(position);
        TextView name = holder.name;
        name.setText(datum.getName() + " (" + datum.getSymbol() + ")");
        TextView price = holder.price;
        price.setText("Price: $" + String.format("%,f", datum.getQuote().getUSD().getPrice()));
        TextView marketCap = holder.marketCap;
        marketCap.setText("Market Cap: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getMarketCap())));
        TextView volume24h = holder.volume24h;
        volume24h.setText("Volume/24h: $" + String.format("%,d", Math.round(datum.getQuote().getUSD().getVolume24h())));
        TextView textView1h = holder.textView1h;
        textView1h.setText(String.format("1h: %.2f", datum.getQuote().getUSD().getPercentChange1h()) + "%");
        TextView textView24h = holder.textView24h;
        textView24h.setText(String.format("24h: %.2f", datum.getQuote().getUSD().getPercentChange24h()) + "%");
        TextView textView7d = holder.textView7d;
        textView7d.setText(String.format("7d: %.2f", datum.getQuote().getUSD().getPercentChange7d()) + "%");

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView price;
        TextView marketCap;
        TextView volume24h;
        TextView textView1h;
        TextView textView24h;
        TextView textView7d;
        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            marketCap = itemView.findViewById(R.id.marketCap);
            volume24h = itemView.findViewById(R.id.volume24h);
            textView1h = itemView.findViewById(R.id.textView1h);
            textView24h = itemView.findViewById(R.id.textView24h);
            textView7d = itemView.findViewById(R.id.textView7d);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    Datum getItem(int id){
        return mData.get(id);
    }

    void setClickListener(ItemClickListener itemClickListener){
        this.mClickListener = itemClickListener;

    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
