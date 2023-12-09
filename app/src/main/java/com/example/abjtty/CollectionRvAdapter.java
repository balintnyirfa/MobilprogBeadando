package com.example.abjtty;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CollectionRvAdapter extends RecyclerView.Adapter<CollectionRvAdapter.ViewHolder>{
    private ArrayList<RecordCollection> collectionArrayList;
    private Context context;
    private OnRecordListener mOnRecordListener;

    public CollectionRvAdapter(ArrayList<RecordCollection> collectionArrayList, Context context, OnRecordListener onRecordListener) {
        this.collectionArrayList = collectionArrayList;
        this.context = context;
        this.mOnRecordListener = onRecordListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_record, parent, false);
        return new ViewHolder(view, mOnRecordListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecordCollection modal = collectionArrayList.get(position);
        holder.artistTextView.setText(modal.getArtist());
        holder.titleTextView.setText(modal.getTitle());
        holder.formatTextView.setText(modal.getFormat());
        holder.valueTextView.setText(String.valueOf(modal.getValue()));
    }

    @Override
    public int getItemCount() {
        return collectionArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView artistTextView, titleTextView, formatTextView, valueTextView;
        OnRecordListener onRecordListener;
        public ViewHolder(@NonNull View itemView, OnRecordListener onRecordListener) {
            super(itemView);
            artistTextView = itemView.findViewById(R.id.artistListText);
            titleTextView = itemView.findViewById(R.id.titleListText);
            formatTextView = itemView.findViewById(R.id.formatListText);
            valueTextView = itemView.findViewById(R.id.valueListText);
            this.onRecordListener = onRecordListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecordListener.onRecordClick(getAdapterPosition());
        }
    }

    public interface OnRecordListener {
        void onRecordClick(int position);
    }
}
