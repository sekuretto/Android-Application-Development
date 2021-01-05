package fi.k8691.weatherapp.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import fi.k8691.weatherapp.MainActivity
import fi.k8691.weatherapp.R


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // A new placeholder fragment
        return PlaceholderFragment.newInstance(position)
    }


    override fun getCount(): Int {
        // Size of the forecast in MainActivity
        return MainActivity.forecasts.size
    }
}