package bobo.getpostdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by Bobo on 2016/10/26.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView txtResult = null;
    double lat, lon;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Button button = (Button)findViewById(R.id.to_page_1);

        Bundle bundle = getIntent().getExtras();
        lat = bundle.getDouble("lat");
        lon = bundle.getDouble("lon");
        System.out.println(lat + "page2 lat");

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
            // TODO Auto-generated method stub
                Intent intent = new Intent();
                intent.setClass(MapActivity.this, MainActivity.class);
                startActivity(intent);
                MapActivity.this.finish();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        txtResult = (TextView) findViewById(R.id.first_test);
        txtResult.setText("latitude : " + lat + "\n" + "longitude : "+ lon);
        LatLng position = new LatLng(lat, lon);
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        CameraUpdate center = CameraUpdateFactory.newLatLngZoom(position, 15);
        mMap.animateCamera(center);
        //標示現在位置
        MarkerOptions markerOpt = new MarkerOptions();
        markerOpt.position(position);
        markerOpt.title("現在位置");
        markerOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        mMap.addMarker(markerOpt).showInfoWindow();
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
