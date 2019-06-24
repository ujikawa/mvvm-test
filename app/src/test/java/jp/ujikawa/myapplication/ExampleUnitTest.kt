package jp.ujikawa.myapplication

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals


class ExampleUnitTest {

    lateinit var server: MockWebServer

    @Before
    fun setup() {
        server = MockWebServer()
    }

    private fun mockServerSetup(jsonName: String) {
        server = MockWebServer()

        server.start()
        val baseUrl = server.url("/")
        val response = MockResponse()
            .setResponseCode(200)
            .setHeader("Content-Type", "application/json")
            .setBody("test")
        server.enqueue(response)
    }

    @Test
    fun run() {
        server.start()

        val response = MockResponse()
            .addHeader("Content-Type", "application/json") //header
            .setResponseCode(200)
            .setBody("[{\"name\":\"test-repos\"}]")
        server.enqueue(response)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)
        val result = service.fetchReposList("test").execute()

        assertEquals(result.body()!![0].name, "test-repos")
    }

    @Test
    fun run2() {
        val server = MockWebServer()

        // Schedule some responses.
        server.enqueue(MockResponse().setBody("hello, world!"))
        server.enqueue(MockResponse().setBody("sup, bra?"))
        server.enqueue(MockResponse().setBody("yo dog"))

        // Start the server.
        server.start()

        // Ask the server for its URL. You'll need this to make HTTP requests.
        val client = OkHttpClient()

        val request = Request.Builder().url(server.url("/v1/chat/")).build()
        val response = client.newCall(request).execute()

        System.out.println(response.headers())
        System.out.println(response.body()!!.string())

        // Shut down the server. Instances cannot be reused.
        server.shutdown()
    }
}
