package ie.ul.dungeonfinder;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapFragment extends Fragment implements OnMapReadyCallback {
    //declaring map as a datafield
    GoogleMap map;
   
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync( this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //initialising datafield map
        map = googleMap;
        //co-ordinates
        LatLng Limerick = new LatLng(52.663157, -8);
        map.addMarker(new MarkerOptions().position(Limerick).title("Limerick"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Limerick));
        //method so that when map is tapped, location is pinpointed, marker is placed down and address is given.
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                try {
                    //class that finds location given co-ords
                    Geocoder geocoder = new Geocoder(getContext(), Locale.ENGLISH);
                    List<Address> addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(latLng);
                    markerOptions.title(addresses.get(0).getAddressLine(0));
                    //clears previous marker from map
                    map.clear();
                    //moves camera to new marker position
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, map.getCameraPosition().zoom));
                    map.addMarker(markerOptions);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}


