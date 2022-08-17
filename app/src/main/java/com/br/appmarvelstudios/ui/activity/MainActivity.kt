package com.br.appmarvelstudios.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.br.appmarvelstudios.R
import com.br.appmarvelstudios.ui.viewmodel.StateAppViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val controller by lazy { findNavController(R.id.nav_graph_fragment) }
    private val viewModel: StateAppViewModel by viewModel()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        controller.addOnDestinationChangedListener { _,
                                                     destination,
                                                     _ ->
            title = destination.label
            viewModel.componentes.observe(this, Observer {
                it?.let { temComponentes ->
                    if (temComponentes.appBar) {
                        supportActionBar?.show()
                    } else {
                        supportActionBar?.hide()
                    }
                    if (temComponentes.bottomNavigation) {
                        bottom_nav.visibility = View.VISIBLE
                    } else {
                        bottom_nav.visibility = View.GONE
                    }
                }
            })
        }
        bottom_nav.setupWithNavController(controller)
    }
}