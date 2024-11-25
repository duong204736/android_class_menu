package vn.edu.hust.studentman

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
class StudentAdapter(
    private var context: Context,

    private val students: List<StudentModel>
) : BaseAdapter() {

    override fun getCount(): Int = students.size

    override fun getItem(position: Int): Any = students[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            R.layout.layout_student_item,
            parent,
            false
        )

        val student = students[position]
        val textStudentName: TextView = view.findViewById(R.id.text_student_name)
        val textStudentId: TextView = view.findViewById(R.id.text_student_id)

        textStudentName.text = student.studentName
        textStudentId.text = student.studentId

        return view
    }}