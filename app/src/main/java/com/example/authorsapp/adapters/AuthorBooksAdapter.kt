package com.example.authorsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.authorsapp.databinding.LayoutRvBookItemBinding
import com.example.authorsapp.ui.AuthorBooksActivity

class AuthorBooksAdapter(activity: AuthorBooksActivity) : RecyclerView.Adapter<AuthorBooksAdapter.AuthorBooksViewHolder>()  {

    private var authorBooks = ArrayList<String>()

    fun setAuthorBooksList(result: ArrayList<String>) {
        this.authorBooks = result
        notifyDataSetChanged()
    }

    class AuthorBooksViewHolder(val binding: LayoutRvBookItemBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AuthorBooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = LayoutRvBookItemBinding.inflate(inflater, parent, false)
        return AuthorBooksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return authorBooks.size
    }

    override fun onBindViewHolder(holder: AuthorBooksViewHolder, position: Int) {
        val authorBook = authorBooks[position]
        holder.binding.authorTitle.text = authorBook
    }
}