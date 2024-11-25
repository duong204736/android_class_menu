package vn.edu.hust.studentman

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class FormActivity : ComponentActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog)
        val name = findViewById<EditText>(R.id.editTextText)
        if (intent.getStringExtra("name") != null) {
            name.setText( intent.getStringExtra("name").toString())
        }

         val sid = findViewById<EditText>(R.id.editTextText2)
        if (intent.getStringExtra("sid") != null) {
            sid.setText( intent.getStringExtra("sid").toString())
        }

        findViewById<Button>(R.id.dialogBtnSave).setOnClickListener {
            val hoten = name.text.toString()
            val mssv = sid.text.toString()

            intent.putExtra("name", hoten)
            intent.putExtra("sid", mssv)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        findViewById<Button>(R.id.dialogBtnCancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
    }
}

}