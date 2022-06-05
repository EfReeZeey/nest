package com.testjob.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData

class UserViewModel(application: Application): AndroidViewModel(application) {

    private val repository: UserRepository
    val readAllData: LiveData<List<User>>
    val returnedVal = MutableLiveData<User>()

    init {
        val userDao = UserDB.getDB(application).userDao()
        repository = UserRepository(userDao)
        readAllData = repository.readAllData
    }

    fun register(user: User) {
        viewModelScope.launch(Dispatchers.IO) {repository.register(user)}
    }

    fun findUser(lgn: String, psswrd: String) {
        viewModelScope.launch(Dispatchers.IO) {
            returnedVal.postValue(repository.findUser(lgn, psswrd)) }
    }
    fun checkUser(lgn: String) {
        viewModelScope.launch(Dispatchers.IO) {
            returnedVal.postValue(repository.checkUser(lgn)) }
    }
}