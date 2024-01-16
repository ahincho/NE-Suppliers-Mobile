package com.unsa.suppliers.ui.view.main.categories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unsa.suppliers.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_detail)
    }
}