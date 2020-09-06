package io.vasilenko.myrecipes.core.ext

import android.widget.ImageView
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import io.vasilenko.myrecipes.R
import java.io.File

fun ImageView.load(path: String) {
    Picasso.get().load(File(path))
        .placeholder(R.drawable.ic_food_outline)
        .error(R.drawable.ic_food_outline)
        .fit()
        .centerCrop()
        .into(this)
}

fun ImageView.loadSearched(path: String) {
    Picasso.get().load(File(path))
        .memoryPolicy(MemoryPolicy.NO_CACHE)
        .placeholder(R.drawable.ic_image_search_outline)
        .error(R.drawable.ic_image_search_outline)
        .fit()
        .centerCrop()
        .into(this)
}