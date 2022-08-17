package com.br.appmarvelstudios.ui.fragment.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showMensseger(
    myMenssenger : String?
) {
    Toast.makeText(
        requireContext(),
        myMenssenger,
        Toast.LENGTH_LONG
    ).show()
}