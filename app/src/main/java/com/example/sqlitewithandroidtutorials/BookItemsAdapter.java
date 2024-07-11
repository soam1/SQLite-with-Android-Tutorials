package com.example.sqlitewithandroidtutorials;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookItemsAdapter extends RecyclerView.Adapter<BookItemsAdapter.ViewHolder> {
    private ArrayList<String> book_id, book_title, book_author, book_pages;
    private Context context;

    public BookItemsAdapter(Context context, ArrayList<String> book_id, ArrayList<String> book_title, ArrayList<String> book_author, ArrayList<String> book_pages) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.book_id_txt.setText(book_id.get(position));
        holder.book_title_txt.setText(book_title.get(position));
        holder.book_author_txt.setText(book_author.get(position));
        holder.book_pages_txt.setText(book_pages.get(position));

    }

    @Override
    public int getItemCount() {
        return book_author.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;

        public ViewHolder(View view) {

            super(view);

            book_id_txt = view.findViewById(R.id.book_id_txt);
            book_title_txt = view.findViewById(R.id.book_title_txt);
            book_author_txt = view.findViewById(R.id.book_author_txt);
            book_pages_txt = view.findViewById(R.id.book_pages_txt);

        }


    }
}
