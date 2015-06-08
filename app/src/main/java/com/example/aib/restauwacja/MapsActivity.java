package com.example.aib.restauwacja;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;

@EActivity(R.layout.activity_maps)
public class MapsActivity extends Activity {
    public GoogleMap map;

    @FragmentById
    MapFragment mapFragment;

    @AfterViews
    void init() {

        map = mapFragment.getMap();

        LatLng coordinates = new LatLng(50.2264425,18.8068379);

        int zoom = 15;

        map.setMyLocationEnabled(true);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, zoom));

        map.addMarker(new MarkerOptions()

                .title("Valdi Plus")

                .snippet("Przejdz do strony restauracji")

                .position(coordinates))
                .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.logo_valdi));

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent("com.example.aib.restauwacja.RestaurationDetails_");
                startActivity(intent);
            }
        });
        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));
            }
        });
    }
}
