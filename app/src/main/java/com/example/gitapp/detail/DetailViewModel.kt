package com.example.gitapp.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitapp.network.GitProperty

class DetailViewModel(gitProperty: GitProperty, app: Application) : AndroidViewModel(app) {

    private val _position = MutableLiveData<Int>()
    val position :LiveData<Int>
        get() = _position

    private val _selectedProper = MutableLiveData<GitProperty>()
    val selectedProper: LiveData<GitProperty>
        get() = _selectedProper


    init {

        _selectedProper.value = gitProperty

    }


}


