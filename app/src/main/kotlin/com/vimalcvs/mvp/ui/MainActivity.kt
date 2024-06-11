package com.vimalcvs.mvp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.vimalcvs.mvp.adapter.PostsAdapter
import com.vimalcvs.mvp.databinding.ActivityMainBinding
import com.vimalcvs.mvp.intent.PostContract
import com.vimalcvs.mvp.intent.PostPresenter
import com.vimalcvs.mvp.model.ModelPost
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity(), PostContract.View {

    private lateinit var presenter: PostContract.Presenter

    private lateinit var adapter: PostsAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = PostPresenter(this, CoroutineScope(Dispatchers.Main))
        adapter = PostsAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.retryButton.setOnClickListener {
            presenter.getPosts()
        }

        adapter.setOnItemClickListener(object : PostsAdapter.OnItemClickListener {
            override fun onItemClick(posts: ModelPost) {
                val intent = Intent(this@MainActivity, ActivityDetail::class.java).apply {
                    putExtra("EXTRA_POST_ID", posts.id)
                    putExtra("EXTRA_POST_TITLE", posts.title)
                    putExtra("EXTRA_POST_BODY", posts.body)
                }
                startActivity(intent)
            }
        })

        presenter.getPosts()
    }


    override fun showPosts(posts: List<ModelPost>) {
        binding.progressBar.visibility = View.GONE
        binding.messageTextView.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE

        adapter.submitList(posts)
    }

    override fun showError(error: String) {
        binding.progressBar.visibility = View.GONE
        binding.messageTextView.visibility = View.VISIBLE
        binding.retryButton.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.messageTextView.text = error
    }

    override fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.messageTextView.visibility = View.GONE
        binding.retryButton.visibility = View.GONE
        binding.recyclerView.visibility = View.GONE
    }

}
