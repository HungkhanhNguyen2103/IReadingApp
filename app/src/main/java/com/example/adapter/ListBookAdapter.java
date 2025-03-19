package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.ireadingbook.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.model.Book;

import java.util.List;

public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.MyViewHolder> {
    Context context;
    List<Book> itemList;

    public ListBookAdapter(Context context, List<Book> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chitiet,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book item = itemList.get(position);
        holder.txtName.setText(item.getName() + "");
        //holder.txtsoluong.setText("Số lượng: " + item.getSoluong() + "");
        Glide.with(context).load(item.getPoster()).into(holder.imagePoster);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePoster;
        TextView txtName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imagePoster = itemView.findViewById(R.id.item_imgchitiet);
            txtName = itemView.findViewById(R.id.item_tenspchitiet);
            //txtsoluong = itemView.findViewById(R.id.item_soluongchitiet);
        }
    }
}
