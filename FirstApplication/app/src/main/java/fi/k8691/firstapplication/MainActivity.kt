package fi.k8691.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
// import View class
import android.view.View
// import all id's from activity_main layout (now only textView is used)
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // layout will be drawn to the screen from activity_main.xml file
        setContentView(R.layout.activity_main)
    }

    // View class need to be used in parameter, even it is not used
    fun buttonClicked(view: View) {
        // activity_main layout has textView id in TextView element
        textView.setText(R.string.button_clicked_txt)
    }
}
