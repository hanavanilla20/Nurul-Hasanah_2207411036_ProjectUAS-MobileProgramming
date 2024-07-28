package com.vanilla.mobileprouas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
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
            .findFragmentById(R.id.mapFragmen) as SupportMapFragment
        mapFragment.getMapAsync(this)

        // Find the button and set an OnClickListener
        val penunjukArahButton: Button = findViewById(R.id.petunjukArah)
        penunjukArahButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://maps.app.goo.gl/RiSkWxpvFhAKupkm7"))
            startActivity(intent)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker at a specific coordinate and move the camera
        val location = LatLng(-6.374978173407569, 106.82483550047472)
        mMap.addMarker(MarkerOptions().position(location).title("Marker in Depok"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }
}
