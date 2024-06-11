package com.vimalcvs.mvp.repository

import com.vimalcvs.mvp.model.ModelPost
import retrofit2.http.GET

interface ApiService {
    @GET("/posts")
    suspend fun getPosts(): List<ModelPost>
}
