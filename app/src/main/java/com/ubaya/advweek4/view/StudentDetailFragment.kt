package com.ubaya.advweek4.view

import android.R.attr.category
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ubaya.advweek4.R
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.view.*


class StudentDetailFragment : Fragment() {
    companion object{
        val EXTRA_STUDENT_ID= "EXTRA_STUDENT_ID"
    }

    private lateinit var viewModel: DetailViewModel
    private val studentListAdapter= StudentListAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null){
            var studentid= StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
            viewModel.fetch(studentid)
        }
        observeViewModel()
    }
    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner){
            val studentId= it.id
            val studentName= it.name
            val studentDob= it.dob
            val studentPhone=it.phone
            val studentImage =it.photoUrl

            editID.setText(studentId)
            editName.setText(studentName)
            editDOB.setText(studentDob)
            editPhone.setText(studentPhone)
            imageStudent.loadImage(studentImage.toString(), progressBarStudentDetail)
        }
    }
}