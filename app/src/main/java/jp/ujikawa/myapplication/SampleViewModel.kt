package jp.ujikawa.myapplication

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class SampleViewModel : ViewModel() {

    private val sampleRepo: SampleRepo

    val email: MutableLiveData<String>
    val password: MutableLiveData<String>

    init {
        this.email = MutableLiveData()
        this.password = MutableLiveData()
        this.sampleRepo = SampleRepo()
    }

    //Emailが変更された時に呼ばれるメソッド
    fun onEmailChanged(newVal: String){
        this.email.postValue(newVal)
    }


}