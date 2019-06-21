package jp.ujikawa.myapplication

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()

        // メインスレッドでネットワーク通信を行うことができない
        thread {
            try {
                val response = ApiClient.fetchReposList()
                val firstRepos = response.body()!![0]

                // 別スレッドからUI操作ができないのでhandlerを使用する
                handler.post {
                    text.text = firstRepos.name
                }

                Log.d("retrofit", "リポジトリのID" + response.body())
            } catch (e: Exception) {
                Log.w("retrofit", "fetchReposList :" + e)
            }
        }
    }
}
