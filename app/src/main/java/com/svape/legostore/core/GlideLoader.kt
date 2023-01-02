package com.svape.legostore.core

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.svape.legostore.R
import java.io.IOException

class GlideLoader(val context: Context) {

    fun loadUserPicture(image: Any, imageView: ImageView) {
        try {
            // Load the user image in the ImageView
            Glide
                .with(context)
                .load(image) // URI of the image
                .centerCrop()
                .placeholder(R.drawable.ic_user_background) // A default place holder if image is failed to load
                .into(imageView) // The view in which the image will be loaded.
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}