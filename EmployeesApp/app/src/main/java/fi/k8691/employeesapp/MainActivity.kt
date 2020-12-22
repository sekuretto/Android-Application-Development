package fi.k8691.employeesapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.employee_item.view.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Use LinearManager as a layout manager for recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Instantiate the RequestQueue
        val queue = Volley.newRequestQueue(this)
        // URL to JSON data - remember use your own data here
        val url = "https://student.labranet.jamk.fi/~K8691/employees.json"
        // Create request and listeners
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->
                    // Get employees from JSON
                    val employees = response.getJSONArray("employees")
                    // Create Employees Adapter with employees data
                    recyclerView.adapter = EmployeesAdapter(employees)
                },
                Response.ErrorListener { error ->
                    Log.d("JSON", error.toString())
                }
        )
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    // Employees Adapter, used in RecyclerView in MainActivity
    class EmployeesAdapter(private val employees: JSONArray)
        : RecyclerView.Adapter<EmployeesAdapter.ViewHolder>() {
        // Create UI View Holder from XML layout
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesAdapter.ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.employee_item, parent, false)
            return ViewHolder(view)
        }

        // View Holder class to hold UI views
        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val nameTextView: TextView = view.nameTextView
            val titleTextView: TextView = view.titleTextView
            val emailTextView: TextView = view.emailTextView
            val phoneTextView: TextView = view.phoneTextView
            val departmentTextView: TextView = view.departmentTextView
            val imageView: ImageView = view.imageView

            // Add a item click listener
            init {
                itemView.setOnClickListener {
                    // create an explicit intent
                    val intent = Intent(view.context, EmployeeActivity::class.java)
                    // add selected employee JSON as a string data
                    intent.putExtra("employee",employees[adapterPosition].toString())
                    // start a new activity
                    view.context.startActivity(intent)
                }
            }
        }

        // Bind data to UI View Holder
        override fun onBindViewHolder(
                holder: EmployeesAdapter.ViewHolder,
                position: Int)
        {
            // employee to bind UI
            val employee: JSONObject = employees.getJSONObject(position)
            // employee lastname and firstname
            holder.nameTextView.text = employee["lastName"].toString() + " " + employee["firstName"].toString()
            holder.titleTextView.text = employee["title"].toString()
            holder.emailTextView.text = employee["email"].toString()
            holder.phoneTextView.text = employee["phone"].toString()
            holder.departmentTextView.text = employee["department"].toString()
            val url = employee["image"].toString()
            Glide.with(holder.imageView.context).load(url).placeholder(R.drawable.ic_launcher_foreground).circleCrop().into(holder.imageView)
        }

        // Return item count in employees
        override fun getItemCount(): Int = employees.length()
    }
}