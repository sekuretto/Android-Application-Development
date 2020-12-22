package fi.k8691.showgolfcoursesinmapwithclustering

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import org.json.JSONArray

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


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        class GolfCourseItem(
            lat: Double,
            lng: Double,
            title: String,
            type: String,
            address: String,
            phone: String,
            email: String,
            web_url: String,
            snippet: String
        ) : ClusterItem {

            private val position: LatLng = LatLng(lat, lng)
            private val title: String = title
            private val snippet: String = snippet

            override fun getPosition(): LatLng {
                return position
            }

            override fun getTitle(): String {
                return title
            }

            override fun getSnippet(): String {
                return snippet
            }

        }

        fun loadData() {
            val queue = Volley.newRequestQueue(this)
            val url = "https://ptm.fi/materials/golfcourses/golf_courses.json"
            var golf_courses: JSONArray
            var course_types: Map<String, Float> = mapOf(
                "?" to BitmapDescriptorFactory.HUE_VIOLET,
                "Etu" to BitmapDescriptorFactory.HUE_BLUE,
                "Kulta" to BitmapDescriptorFactory.HUE_GREEN,
                "Kulta/Etu" to BitmapDescriptorFactory.HUE_YELLOW
            )
            // create JSON request object
            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    // JSON loaded successfully
                    golf_courses = response.getJSONArray("courses")
                    // loop through all objects
                    for (i in 0 until golf_courses.length()){
                        // get course data
                        val course = golf_courses.getJSONObject(i)
                        //val coord = LatLng(lat, lng)
                        GolfCourseItem(
                            lat=course["lat"].toString().toDouble(),
                            lng= course["lng"].toString().toDouble(),
                            title= course["course"].toString(),
                            type = course["type"].toString(),
                            address= course["address"].toString(),
                            email= course["email"].toString(),
                            phone = course["phone"].toString(),
                            web_url = course["web"].toString(),
                            snippet = " "
                        )

                        /**if (course_types.containsKey(type)){
                            var m = mMap.addMarker(
                                MarkerOptions()
                                    .position(coord)
                                    .title(title)
                                    .icon(BitmapDescriptorFactory
                                        .defaultMarker(course_types.getOrDefault(type, BitmapDescriptorFactory.HUE_RED)
                                        )
                                    )
                            )
                            // pass data to marker via Tag
                            val list = listOf(address, phone, email, web_url)
                            m.setTag(list)
                        } else {
                            Log.d(TAG, "This course type does not exist in evaluation ${type}")
                        }**/
                    }
                },
                { error ->
                    // Error loading JSON
                }
            )
            // Add the request to the RequestQueue
            queue.add(jsonObjectRequest)
            // ADD LATER custom info window adapter here

        }

        loadData()

        // Declare a variable for the cluster manager.
        lateinit var clusterManager: ClusterManager<GolfCourseItem>

        var MarkerClusterRenderer: ClusterManager<GolfCourseItem>
        {

            // Set some lat/lng coordinates to start with.
            var lat = 51.5145160
            var lng = -0.1270060

            // Add ten cluster items in close proximity, for purposes of this example.
            for (i in 0..9) {
                val offset = i / 60.0
                lat += offset
                lng += offset
                val offsetItem =
                    GolfCourseItem(lat, lng, "Title $i", "Snippet $i")
                clusterManager.addItem(offsetItem)
            }
        }

        fun setUpClusterer() {
            // Position the map.
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(65.5, 26.0),5.0F))

            // Initialize the manager with the context and the map.
            // (Activity extends context, so we can pass 'this' in the constructor.)
            clusterManager = ClusterManager(context, mMap)

            // Point the map's listeners at the listeners implemented by the cluster
            // manager.
            mMap.setOnCameraIdleListener(clusterManager)
            mMap.setOnMarkerClickListener(clusterManager)

            // Add cluster items (markers) to the cluster manager.
            addItems()
        }

        setUpClusterer()
    }
}