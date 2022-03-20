package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.navArgument
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.view.StudentDetailFragmentArgs

class DetailViewModel(application: Application):AndroidViewModel(application) {
    val studentLD= MutableLiveData<Student>()
    val studentsLoadErrorLiveData= MutableLiveData<Boolean>()
    val loadingLiveData= MutableLiveData<Boolean>()
    val TAG= "volleyTag"
    private var queue: RequestQueue?= null

    fun fetch(studentId:String){
        /*val student1= Student("16055","Nonie","1998/03/28","5718444778","http://dummyimage.com/75x100.jpg/cc0000/ffffff")
        studentLD.value= student1
         */

        studentsLoadErrorLiveData.value= false
        loadingLiveData.value= true

        queue= Volley.newRequestQueue(getApplication())

        Log.d("Student ID", studentId)
        
        val url= "http://adv.jitusolution.com/student.php?id=$studentId"
        val stringRequest= StringRequest(
            Request.Method.GET, url,
            {
                val result= Gson().fromJson<Student>(it, Student::class.java)
                studentLD.value= result
                loadingLiveData.value= false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value= false
                studentsLoadErrorLiveData.value= true
                Log.d("errorvolley", it.toString())
            }
        ).apply {
            tag= "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}