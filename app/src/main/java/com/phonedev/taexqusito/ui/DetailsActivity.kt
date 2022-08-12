package com.phonedev.taexqusito.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.bumptech.glide.Glide
import com.phonedev.taexqusito.databinding.ActivityDetailsBinding
import com.phonedev.taexqusito.models.Productos

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        supportActionBar?.hide()

        val product = intent.getParcelableExtra<Productos>("product")
        if (product != null) {
            binding.txtName.text = product.nombre
            binding.txtDescr.text = product.descripcion
            Glide.with(binding.imgProduct).load(product.img).into(binding.imgProduct)
        }
    }
}