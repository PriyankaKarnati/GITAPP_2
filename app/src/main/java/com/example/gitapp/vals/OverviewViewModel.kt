package com.example.gitapp.vals
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitapp.network.GitApi
import com.example.gitapp.network.GitProperty
import kotlinx.coroutines.*
import java.lang.Exception

class OverviewViewModel:ViewModel() {
    private val _response = MutableLiveData<String>()//internal immutable string storing the most recent response
    val response : LiveData<String>//external mutable LiveData for response String
        get() = _response

    private val _properties = MutableLiveData<List<GitProperty>>()
    val properties: LiveData<List<GitProperty>>
        get() = _properties
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private val _navigateToSelected = MutableLiveData<GitProperty>()
    val navigateToSelected: LiveData<GitProperty>
        get() = _navigateToSelected

    private val _sendList = MutableLiveData<MutableList<GitProperty>>()
    val sendList :LiveData<MutableList<GitProperty>>
    get() = _sendList

    init{
        //Log.i("start","started")
        getGitApiResponse(0)
        Log.i("on Failed","failed")
    }

    fun displaySelectedProperties(gitProperty: GitProperty) {
        _navigateToSelected.value = gitProperty

    }

    fun displayCompleted() {
        _navigateToSelected.value = null
    }

    fun sendToDetails(gitProperty: GitProperty,list: MutableList<GitProperty>){

    }
    fun getGitApiResponse( x:Int){
        coroutineScope.launch {
            var getPropertiesDeferred = GitApi.retrofitService.getPropertiesAsync(x)

            try {
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success : ${listResult.size} git properties retrieved."
                if (listResult.isNotEmpty()) {
                    _properties.value = listResult
                    for(i in listResult){
                        _sendList.value?.add(i)
                    }

                    Log.i("viewModel","Loaded ${listResult.size}")

                }
            } catch (e: Exception) {
                _response.value = "Failed: ${e.message}"
            }


        }

//        GitApi.retrofitService.getProperties(5).enqueue(object : Callback<List<GitProperty>> {
//            override fun onFailure(call: Call<List<GitProperty>>, t: Throwable) {
//                    _response.value =  "Failure: " + t.message
//                    Log.i("on Failed","failed")
//                }
//
//
//            override fun onResponse(
//                call: Call<List<GitProperty>>,
//                response: Response<List<GitProperty>>
//            ) {
//                _response.value = "Success :${response.body()?.size} git properties retrieved"
//                //Log.i("onSuccess",response.body())
//            }
//        })
    }


    override fun onCleared() {
        super.onCleared()
        //if fragment cancelled loading should be stopped
        viewModelJob.cancel()
    }
//    override fun onFailure(call: Call<String>, t: Throwable) {
//        _response.value = "Failure: " + t.message
//    }
//
//    override fun onResponse(call: Call<String>,
//                            response: Response<String>
//    ) {
//        _response.value = response.body()
//    }
}



