package com.me.appdoctruyen.ui.book.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.databinding.SportsListItemBinding;

public class BookAdapter extends ListAdapter<Book, BookAdapter.BookViewHolder> {
    private Context context;

    private BookItemClicked itemClicked;
    public BookAdapter(BookItemClicked itemClicked) {
        super(DIFF_CALLBACK);
        this.itemClicked = itemClicked;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new BookViewHolder(SportsListItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book currentBook = getItem(position);
        holder.itemView.setOnClickListener(view -> itemClicked.onItemClicked(currentBook));
        holder.bind(currentBook);
    }

    public static final DiffUtil.ItemCallback<Book> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Book>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull Book oldBook, @NonNull Book newBook) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return (oldBook.name.equals(newBook.name) || oldBook.content.equals(newBook.content) || oldBook.bid == newBook.bid);
                }

                @Override
                public boolean areContentsTheSame(
                        @NonNull Book oldBook, @NonNull Book newBook) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldBook.equals(newBook);
                }
            };

    class BookViewHolder extends RecyclerView.ViewHolder {
        private SportsListItemBinding binding;

        public BookViewHolder(@NonNull SportsListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Book book) {
            binding.title.setText(book.name);
            binding.subTitle.setText(book.author);
            if (!book.image.isEmpty()) {
                Glide.with(context).load(book.image).into(binding.sportsImage);
            }
        }
    }
}
