package com.arsoft.arp.helpers

import androidx.lifecycle.MutableLiveData

//Set default value for any type of MutableLivaData
fun <T: Any?> MutableLiveData<T>.default(defaultValue: T) = apply{ postValue(defaultValue)}

//Set new value for any type of MutableLivaData
fun <T> MutableLiveData<T>.set(newValue: T) = apply{ postValue(newValue)}