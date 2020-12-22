package fi.k8691.citymapexercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Finland and move the camera
        val finland = LatLng(61.95912437300109, 25.880369110121418)
        val jyvaskyla = LatLng(62.28438512029319, 25.746426649393698)
        val tampere = LatLng(61.51593246211015, 23.7588630748561)
        val kannonkoski = LatLng(62.97634573731601, 25.261284177135426)
        val rovaniemi = LatLng(66.56785846632373, 25.745963485074554)
        val kokkola = LatLng(63.84228157095331, 23.125244425622213)
        mMap.addMarker(MarkerOptions().position(jyvaskyla).title("Marker in Jyväskylä"))
        mMap.addMarker(MarkerOptions().position(tampere).title("Marker in Tampere"))
        mMap.addMarker(MarkerOptions().position(kannonkoski).title("Marker in Kannonkoski"))
        mMap.addMarker(MarkerOptions().position(rovaniemi).title("Marker in Rovaniemi"))
        mMap.addMarker(MarkerOptions().position(kokkola).title("Marker in Kokkola"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(finland))
    }
}