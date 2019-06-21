package jp.ujikawa.myapplication

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun fetchReposList(@Path("user") user: String) : Call<List<Repos>>
}