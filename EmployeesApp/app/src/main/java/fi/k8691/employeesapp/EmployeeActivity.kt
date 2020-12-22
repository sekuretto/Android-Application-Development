package fi.k8691.employeesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_employee.*
import kotlinx.android.synthetic.main.employee_item.view.*
import org.json.JSONObject

class EmployeeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee)

        // get data from intent
        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val employeeString = bundle.getString("employee")
            val employee = JSONObject(employeeString)
            val fullName = employee["firstName"].toString() + " " + employee["lastName"].toString()
            //val title = employee["title"].toString()
            //val email = employee["email"].toString()
            //val phone = employee["phone"].toString()
            //val department = employee["department"].toString()
            val image = employee["image"]
            val nameView = (TextView) findViewById(R.id.empName)
            //val titleView = (TextView(findViewById(R.id.empTitle)))
            //val emailView = (TextView(findViewById(R.id.empEmail)))
            //val phoneView = (TextView(findViewById(R.id.empPhone)))
            //val depView = (TextView(findViewById(R.id.empDep)))

            Glide.with(this)
                .load(image)
                .into(employeeImageView)
            nameView.text = fullName
            //titleView.text = title
            //emailView.text = email
            //phoneView.text = phone
            //depView.text = department

            // modify here to display other employee's data too!
            Toast.makeText(this, "Name is $fullName", Toast.LENGTH_LONG).show()
        }
    }
}




