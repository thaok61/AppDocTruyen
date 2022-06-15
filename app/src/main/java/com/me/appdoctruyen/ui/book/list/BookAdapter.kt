package com.me.appdoctruyen.ui.book.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.me.appdoctruyen.data.models.Book
import com.me.appdoctruyen.databinding.SportsListItemBinding

class BookAdapter(private val onItemClicked: BookItemClicked) :
    ListAdapter<Book, BookAdapter.BookViewHolder>(DiffCallback) {

    private lateinit var context: Context

    class BookViewHolder(private var binding: SportsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            binding.title.text = book.name
            binding.subTitle.text = book.author
            // Load the images into the ImageView using the Coil library.
            if (book.image.isNotEmpty()) {
                binding.sportsImage.load(book.image)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        context = parent.context
        return BookViewHolder(
            SportsListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked.onItemClicked(current)
        }
        holder.bind(current)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return (oldItem.bid == newItem.bid ||
                        oldItem.content == newItem.content ||
                        oldItem.name == newItem.name
                        )
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}

interface BookItemClicked {
    fun onItemClicked(book: Book)
}