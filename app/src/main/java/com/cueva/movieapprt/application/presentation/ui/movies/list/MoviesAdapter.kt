package com.cueva.movieapprt.application.presentation.ui.movies.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import com.cueva.movieapprt.application.presentation.util.Utils
import com.cueva.movieapprt.databinding.MovieItemBinding

class MoviesAdapter (private val onClickListener: OnClickListener): PagingDataAdapter<MovieApp, MoviesAdapter.MovieViewHolder>(LeagueDiffCallback()) {

    class MovieViewHolder(private var binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: MovieApp){
            Utils.setImage(movie.urlPoster,binding.moviePoster)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovieItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.itemView.setOnClickListener {
            movie?.let { it1 -> onClickListener.onClick(it1) }
        }
        movie?.let { holder.bind(it) }
    }

    class OnClickListener(val clickListener: (movie: MovieApp) -> Unit) {
        fun onClick(movie: MovieApp) = clickListener(movie)
    }
}

class LeagueDiffCallback : DiffUtil.ItemCallback<MovieApp>() {
    override fun areItemsTheSame(oldItem: MovieApp, newItem: MovieApp): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieApp, newItem: MovieApp): Boolean {
        return oldItem == newItem
    }
}