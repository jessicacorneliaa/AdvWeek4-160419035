package com.ubaya.advweek4.view

import android.R.attr.category
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.FragmentStudentDetailBinding
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_student_detail.*
import kotlinx.android.synthetic.main.student_list_item.view.*
import java.util.concurrent.TimeUnit

class StudentDetailFragment : Fragment(), UpdateDetailClickListener, CreateNotificationDetailClickListener {
    private lateinit var viewModel: DetailViewModel
    private lateinit var dataBinding:FragmentStudentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding= DataBindingUtil.inflate(inflater, R.layout.fragment_student_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel= ViewModelProvider(this).get(DetailViewModel::class.java)
        if(arguments != null){
            var studentid= StudentDetailFragmentArgs.fromBundle(requireArguments()).studentid
            viewModel.fetch(studentid)
        }
        observeViewModel()

        //Instantiate listener
        dataBinding.updateDetailListener= this
        dataBinding.createNotificationDetailListener= this
    }
    private fun observeViewModel() {
        viewModel.studentLD.observe(viewLifecycleOwner) {
            dataBinding.student = it
            /*
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

            var student= it
            buttonNotif.setOnClickListener {
                Observable.timer(5,TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe{
                        MainActivity.showNotification(student.name.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
                    }
            }
            */
        }
    }

    override fun onUpdateDetailClickListener(view: View) {
        Toast.makeText(view.context, "Data updated", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateNotificationDetailClickListener(view: View) {
        Observable.timer(5,TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                MainActivity.showNotification(dataBinding.student?.name.toString(), "A new notification created", R.drawable.ic_baseline_person_24)
            }
    }
}