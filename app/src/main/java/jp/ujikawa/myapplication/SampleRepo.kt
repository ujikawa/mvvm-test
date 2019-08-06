package jp.ujikawa.myapplication

import io.reactivex.rxkotlin.Singles

class SampleRepo() {

    private val sampleDBDS : SampleDBDS

    init {
        this.sampleDBDS = SampleDBDS()
    }

    fun signIn(){

    }

    inner class SampleDBDS {
        fun writeEmail() : Singles {

        }
    }
}