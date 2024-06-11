package com.vimalcvs.mvp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.vimalcvs.mvp.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val postTitle = intent.getStringExtra("EXTRA_POST_TITLE")
        val postBody = intent.getStringExtra("EXTRA_POST_BODY")

        binding.titleTextView.text = postTitle
        binding.bodyTextView.text = postBody
    }
}
