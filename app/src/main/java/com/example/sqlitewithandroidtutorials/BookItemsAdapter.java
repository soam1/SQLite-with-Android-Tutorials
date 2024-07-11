package com.example.sqlitewithandroidtutorials;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookItemsAdapter extends RecyclerView.Adapter<BookItemsAdapter.ViewHolder> {
    private ArrayList<String> book_id, book_title, book_author, book_pages;
    private Context context;

    int position;

    Activity activity;

    public BookItemsAdapter(Activity activity, Context context, ArrayList<String> book_id, ArrayList<String> book_title, ArrayList<String> book_author, ArrayList<String> book_pages) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
//        position = holder.getAdapterPosition();
        holder.book_id_txt.setText(book_id.get(position));
        holder.book_title_txt.setText(book_title.get(position));
        holder.book_author_txt.setText(book_author.get(position));
        holder.book_pages_txt.setText(book_pages.get(position));

        holder.itemLayout.setOnClickListener(view -> {
            Toast.makeText(context, "Clicked on " + book_id.get(position), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(book_id.get(position)));
            intent.putExtra("title", String.valueOf(book_title.get(position)));
            intent.putExtra("author", String.valueOf(book_author.get(position)));
            intent.putExtra("pages", String.valueOf(book_pages.get(position)));

            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return book_author.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout itemLayout;

        public ViewHolder(View view) {

            super(view);

            book_id_txt = view.findViewById(R.id.book_id_txt);
            book_title_txt = view.findViewById(R.id.book_title_txt);
            book_author_txt = view.findViewById(R.id.book_author_txt);
            book_pages_txt = view.findViewById(R.id.book_pages_txt);

            itemLayout = view.findViewById(R.id.itemLayout);

        }


    }
}
