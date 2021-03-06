package com.cueva.movieapprt.application.presentation.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import com.cueva.movieapprt.R
import com.cueva.movieapprt.application.presentation.di.movie.DaggerMovieComponent
import com.cueva.movieapprt.application.presentation.di.movie.RoomModule
import com.cueva.movieapprt.application.presentation.entity.MovieApp
import com.cueva.movieapprt.databinding.FragmentMoviesBinding
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MoviesFragment : Fragment() {


    @Inject
    lateinit var movieVieModel: MoviesViewModel

    lateinit var binding: FragmentMoviesBinding

    @ExperimentalPagingApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies, container, false
        )

        activity?.findViewById<AppBarLayout>(R.id.app_bar)?.visibility = AppBarLayout.VISIBLE

        DaggerMovieComponent.builder().roomModule(RoomModule(requireActivity().application)).build().inject(this)

        val adapter = MoviesAdapter(MoviesAdapter.OnClickListener{movie,image->
            navigateToDetail(movie,image)
        })
        binding.rvMovies.adapter = adapter

        //Page Adapter
        movieVieModel.getMovies().observe(viewLifecycleOwner, Observer {
            lifecycleScope.launch {
                adapter.submitData(it)
            }
        })

        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressBar.isVisible = loadStates.refresh is LoadState.Loading
                binding.errorImage.isVisible = loadStates.refresh is LoadState.Error
            }
        }
        binding.setLifecycleOwner(this)

        return binding.root
    }

    private fun navigateToDetail(movie: MovieApp,image:AppCompatImageView){
        val extras = FragmentNavigatorExtras(image to "movie_poster_${movie.id}")
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movie)
        findNavController().navigate(action,extras)
    }
}