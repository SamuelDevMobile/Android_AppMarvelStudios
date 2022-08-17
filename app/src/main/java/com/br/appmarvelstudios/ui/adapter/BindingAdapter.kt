package com.br.appmarvelstudios.ui.adapter

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.br.appmarvelstudios.R
import com.squareup.picasso.Picasso

@BindingAdapter(value = ["app:imageUri","app:context"], requireAll = true )
fun image(view : ImageView, uri: String, context: Context){
    Picasso
        .with(context)
        .load(uri)
        .placeholder(R.drawable.ic_launcher_foreground)
        .into(view)
}