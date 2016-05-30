package ohmsoftwaresinc.com.universalweatherapplication;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements LocationListener {

    private GoogleMap mMap;
    private GoogleMap googleMap; // Might be null if Google Play services APK is not available.
    LocationManager locationManager;
    String mprovider;
    double longitude, latitude;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mprovider = locationManager.getBestProvider(criteria, false);

        if (mprovider != null && !mprovider.equals("")) {

            location = locationManager.getLastKnownLocation(mprovider);
            // locationManager.requestLocationUpdates(mprovider,15000, 1,getApplicationContext());

            if (location != null)
                onLocationChanged(location);
            //   Toast.makeText(getBaseContext(),+location.getLatitude()+" "+location.getLongitude(), Toast.LENGTH_LONG).show();


        }


        try {
            // Loading map
            initilizeMap();

            // Changing map type
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
            // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

            // Showing / hiding your current location
            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            googleMap.setMyLocationEnabled(true);

            // Enable / Disable zooming controls
            googleMap.getUiSettings().setZoomControlsEnabled(false);

            // Enable / Disable my location button
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);

            // Enable / Disable Compass icon
            googleMap.getUiSettings().setCompassEnabled(true);

            // Enable / Disable Rotate gesture
            googleMap.getUiSettings().setRotateGesturesEnabled(true);

            // Enable / Disable zooming functionality
            googleMap.getUiSettings().setZoomGesturesEnabled(true);

            //	double latitude = 17.385044;
            //	double longitude = 78.486671;

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            MarkerOptions marker = new MarkerOptions().position(
                    new LatLng(latitude, longitude))
                    ;

            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title("Your Current Location - "+latitude+" , "+longitude)).showInfoWindow();

           // googleMap.addMarker(marker);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(new LatLng(latitude,longitude)).zoom(16).build();

            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
    private void initilizeMap() {
        if (googleMap == null) {

            googleMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {

    }
}
