package com.cueva.movieapprt.application.presentation.ui.movies.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.transition.TransitionInflater
import com.cueva.movieapprt.R
import com.cueva.movieapprt.application.presentation.util.Utils
import com.cueva.movieapprt.databinding.FragmentMovieDetailBinding
import com.google.android.material.appbar.AppBarLayout


class MovieDetailFragment : Fragment() {

    lateinit var binding: FragmentMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_detail, container, false
        )
        activity?.findViewById<AppBarLayout>(R.id.app_bar)?.visibility = AppBarLayout.GONE

        sharedElementEnterTransition = TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)
        sharedElementReturnTransition =  TransitionInflater.from(this.context).inflateTransition(R.transition.change_bounds)

        val movie = MovieDetailFragmentArgs.fromBundle(requireArguments()).selectedMovie
        ViewCompat.setTransitionName(binding.moviePoster, "movie_poster_${movie.id}")


        val viewModelFactory = MovieDetailViewModelFactory(movie)
        binding.viewModel = ViewModelProvider(this,viewModelFactory).get(MovieDetailViewModel::class.java)
        Utils.setImage(movie.urlPoster,binding.moviePoster)
        Utils.setImage(movie.backPoster,binding.movieBackPoster)

        binding.setLifecycleOwner(this)

        return binding.root
    }
}