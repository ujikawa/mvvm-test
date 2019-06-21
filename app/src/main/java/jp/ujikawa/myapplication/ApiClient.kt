package jp.ujikawa.myapplication

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://api.github.com/"
    private const val ACCOUNT_NAME = "ujikawa"

    private fun restClient() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun fetchReposList() : Response<List<Repos>> {
        val service = restClient().create(GitHubService::class.java)
        return service.fetchReposList(ACCOUNT_NAME).execute()
    }

}