package com.vimalcvs.mvp.intent


import com.vimalcvs.mvp.repository.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PostPresenter(private val view: PostContract.View, private val scope: CoroutineScope) :
    PostContract.Presenter {

    override fun getPosts() {
        view.showLoading()
        scope.launch(Dispatchers.Main) {
            try {
                val posts = withContext(Dispatchers.IO) {
                    RetrofitInstance.api.getPosts()
                }
                if (posts.isEmpty()) {
                    view.showError("No data available")
                } else {
                    view.showPosts(posts)
                }
            } catch (e: Exception) {
                view.showError("Please check internet connection")
            }
        }
    }

    override fun onDestroy() {
        // Clean up if needed
    }
}


