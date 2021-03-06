package com.cueva.movieapprt.application.presentation.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cueva.movieapprt.R
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Utils {
    companion object{
        fun setImage(urlImage: String, imageItem: AppCompatImageView){
            val urlModified= "https://image.tmdb.org/t/p/w500/"+urlImage
            val imgUri = urlModified.toUri().buildUpon().scheme("https").build()
            Glide.with(imageItem.context)
                .load(imgUri)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image))
                .into(imageItem)
        }


        fun getDateFormat(date: String):String{

            if(""==date)
                return "No Date"

            val dateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
            val date: Date =
                dateFormat.parse(date)
            val formatter: DateFormat =
                SimpleDateFormat("MMM dd, yyyy")
            return formatter.format(date)
        }
    }


}