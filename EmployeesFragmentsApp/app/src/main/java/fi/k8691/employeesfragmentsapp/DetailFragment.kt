package fi.k8691.employeesfragmentsapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.employee_item.view.*

class DetailFragment: androidx.fragment.app.Fragment() {
    // create view
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // get root view
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // show employee if there is a selection made in recycler view's adapter
        if (EmployeesAdapter.position != -1) {
            val employee = MainActivity.employees.getJSONObject(EmployeesAdapter.position)
            // show data in UI
            employee?.let {
                rootView.nameTextView.text = it.getString("lastName") + " " + it.getString("firstName")
                rootView.titleTextView.text = it.getString("title")
                rootView.emailTextView.text = it.getString("email")
                rootView.phoneTextView.text = it.getString("phone")
                rootView.departmentTextView.text = it.getString("department")
                val requestOptions = RequestOptions()
                requestOptions.override(500, 500)
                Glide.with(this).load(it.getString("image")).apply(requestOptions).into(rootView.imageView)
            }
        }
        // return view
        return rootView
    }
}