package com.vimalcvs.mvp.intent

import com.vimalcvs.mvp.model.ModelPost

interface PostContract {
    interface View {
        fun showPosts(posts: List<ModelPost>)
        fun showError(error: String)
        fun showLoading()
    }

    interface Presenter {
        fun getPosts()
        fun onDestroy()
    }
}
