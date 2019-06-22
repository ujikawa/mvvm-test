package jp.ujikawa.myapplication

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Inet6Address

class ExampleUnitTest {

    @Test
    fun run() {
        var server = MockWebServer()

        val response = MockResponse()
            .addHeader("Content-Type", "application/json") //header
            .setResponseCode(200)
            .setBody("{\"name\":\"test-repos\"}")

        server.enqueue(response)
        //server.start()

        val retrofit = Retrofit.Builder()
            .baseUrl(server.url("/").toString())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(GitHubService::class.java)
        val result = service.fetchReposList("test")

//        assertEquals(result.body()!![0].name, "test-repos")
    }

    @Test
    fun run2() {
        val server = MockWebServer()

        val response = MockResponse()
            .addHeader("Content-Type", "text/plain") //header
            .setResponseCode(200)
            .setBody("TEST")

        server.enqueue(response)
        server.start(8888)
        val test: jp.ujikawa.myapplication.Test = Test(server.url("/").toString())

        val result = test.run()

        assertEquals(result, "TEST")

//        assertEquals(result.body()!![0].name, "test-repos")
    }
}
