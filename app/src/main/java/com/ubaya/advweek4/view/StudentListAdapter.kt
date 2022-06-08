package com.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.StudentListItemBinding
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.util.loadImage
import kotlinx.android.synthetic.main.student_list_item.view.*

class StudentListAdapter(val studentList:ArrayList<Student>) :RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
StudentDetailClickListener{
    class StudentViewHolder(var view:StudentListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        // val view = inflater.inflate(R.layout.student_list_item, parent, false)
        // val view= DataBindingUtil.inflate(inflater, R.layout.student_list_item, false)
        // bisa pakai yg atas atau bawah.
        val view= StudentListItemBinding.inflate(inflater, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        with(holder.view){
            student= studentList[position]
            detailListener= this@StudentListAdapter
        }
        /*
        val student=studentList[position]
        val studentid= student.id
        with(holder.view){
            txtId.text=student.id
            txtName.text=student.name
            imageView.loadImage(student.photoUrl.toString(), progressBar)
            buttonDetail.setOnClickListener {
                val action= StudentListFragmentDirections.actionStudentDetail(studentid.toString())
                Navigation.findNavController(it).navigate(action)
            }
        }
        */

    }

    override fun getItemCount()=studentList.size

    fun updateStudentList(newStudentList:ArrayList<Student>){
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun onStudentDetailClick(view: View) {
        val action= StudentListFragmentDirections.actionStudentDetail(view.tag.toString())
        Navigation.findNavController(view).navigate(action)
    }
}