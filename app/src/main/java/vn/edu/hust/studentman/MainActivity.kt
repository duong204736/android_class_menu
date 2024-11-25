package vn.edu.hust.studentman

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import android.widget.Toolbar
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    private lateinit var studentAdapter: StudentAdapter
    var selectedPosition : Int = -1

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }
    val students = mutableListOf(
        StudentModel("Nguyễn Văn An", "SV001"),
        StudentModel("Trần Thị Bảo", "SV002"),
        StudentModel("Lê Hoàng Cường", "SV003"),
        StudentModel("Phạm Thị Dung", "SV004"),
        StudentModel("Đỗ Minh Đức", "SV005"),
        StudentModel("Vũ Thị Hoa", "SV006"),
        StudentModel("Hoàng Văn Hải", "SV007"),
        StudentModel("Bùi Thị Hạnh", "SV008"),
        StudentModel("Đinh Văn Hùng", "SV009"),
        StudentModel("Nguyễn Thị Linh", "SV010"),
        StudentModel("Phạm Văn Long", "SV011"),
        StudentModel("Trần Thị Mai", "SV012"),
        StudentModel("Lê Thị Ngọc", "SV013"),
        StudentModel("Vũ Văn Nam", "SV014"),
        StudentModel("Hoàng Thị Phương", "SV015"),
        StudentModel("Đỗ Văn Quân", "SV016"),
        StudentModel("Nguyễn Thị Thu", "SV017"),
        StudentModel("Trần Văn Tài", "SV018"),
        StudentModel("Phạm Thị Tuyết", "SV019"),
        StudentModel("Lê Văn Vũ", "SV020")
    )

//    fun showCustomDialog(isAddNew: Boolean, position: Int) {
//        val dialog = Dialog(this)
//        dialog.setContentView(R.layout.dialog)
//        val editFullName = dialog.findViewById<EditText>(R.id.editTextText)
//        val editSID = dialog.findViewById<EditText>(R.id.editTextText2)
//        if (!isAddNew) {
//            editFullName.setText(students[position].studentName)
//            editSID.setText(students[position].studentId)
//            Toast.makeText(this, "Edit: ${students[position]}", Toast.LENGTH_SHORT).show()
//        }
//        dialog.findViewById<Button>(R.id.dialogBtnSave).setOnClickListener {

//            if (isAddNew) {
//                students.add(students.size, StudentModel(editFullName.text.toString(), editSID.text.toString()))
//            }else {
//                students[position] = StudentModel(editFullName.text.toString(), editSID.text.toString())
//            }
//            dialog.dismiss()
//        }
//        dialog.findViewById<Button>(R.id.dialogBtnCancel).setOnClickListener {
//            dialog.dismiss()
//        }
//        dialog.window?.setLayout(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.WRAP_CONTENT
//        )
//        dialog.show()
//    }
    private lateinit var intent:Intent
    private val laucher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ) { it: ActivityResult ->
        if (it.resultCode == RESULT_OK) {
            val name = it.data?.getStringExtra("name")
            val sid = it.data?.getStringExtra("sid")
            Toast.makeText(this, " add ${name}", Toast.LENGTH_SHORT)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent = Intent(this, FormActivity::class.java)
        val listView = findViewById<ListView>(R.id.recycler_view_students)
        studentAdapter = StudentAdapter(this, students)
        listView.adapter = studentAdapter
//        val addBtn = findViewById<Button>(R.id.btn_add_new)
//        addBtn.setOnClickListener{
//            laucher.launch(intent)
//        }
        // Register ListView for context menu
        registerForContextMenu(listView)

        // Track selected item position
        listView.setOnItemLongClickListener { _, _, position, _ ->
            selectedPosition = position

            false // Return false to show the context menu
    }



    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context_menu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        val student = students[selectedPosition]

        return when (item.itemId) {
            R.id.menu_edit -> {
                Toast.makeText(this, "Edit: ${student.studentName}", Toast.LENGTH_SHORT).show()
                // Add edit logic here

                intent.putExtra("name", students[selectedPosition].studentName)
                intent.putExtra("sid", students[selectedPosition].studentId)
                laucher.launch(intent)
                true
            }
            R.id.menu_delete -> {
                Toast.makeText(this, "Remove: ${student.studentName}", Toast.LENGTH_SHORT).show()
                // Remove the student
                students.removeAt(selectedPosition)
                studentAdapter.notifyDataSetChanged() // Refresh the ListView
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.optionAdd -> {
                Toast.makeText(this, "Nhập thông tin cho sinh viên mới", Toast.LENGTH_LONG).show()
                val intent = Intent(this, FormActivity::class.java)
                intent.putExtra("isAddNew", true)

                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
