package com.example.authorsapp.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.authorsapp.database.Author
import com.example.authorsapp.databinding.LayoutRvBookItemBinding
import com.example.authorsapp.ui.AuthorBooksActivity

class BooksAdapter(val activity: Activity): RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    var authors = listOf<Author?>()

    fun setAuthorList(result: List<Author?>) {
        this.authors = result
        notifyDataSetChanged()
    }

    class BooksViewHolder(val binding: LayoutRvBookItemBinding) : RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = LayoutRvBookItemBinding.inflate(inflater, parent, false)
        return BooksViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return authors.size
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val author = authors[position]
        holder.binding.authorTitle.text = author?.name

        holder.itemView.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, AuthorBooksActivity::class.java)
            intent.putStringArrayListExtra("authorbooks", author?.authorBooks)
            activity.startActivity(intent)
        })
    }
}