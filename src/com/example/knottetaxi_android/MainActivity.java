package com.example.knottetaxi_android;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tv1 = (TextView)findViewById(R.id.position);
		tv1.setText("Your position 2");
        Log.i("Main stuff", "Yes sir !");

		
		LocationManager locationManager = (LocationManager)
				getSystemService(Context.LOCATION_SERVICE);
		
        Log.i("locationManager", locationManager.toString());

		
		LocationListener locationListener = new MyLocationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
}








class MyLocationListener implements LocationListener {

    @Override
    public void onLocationChanged(Location loc) {
    //    editLocation.setText("");
    //    pb.setVisibility(View.INVISIBLE);
    //    Toast.makeText(
    //            getBaseContext(),
    //            "Location changed: Lat: " + loc.getLatitude() + " Lng: "
    //                + loc.getLongitude(), Toast.LENGTH_SHORT).show();
        String longitude = "Longitude: " + loc.getLongitude();
    //    Log.v(TAG, longitude);
        String latitude = "Latitude: " + loc.getLatitude();
    //    Log.v(TAG, latitude);

        Log.i("Helge", latitude);
		
       
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i("GPS", "Not enabled");

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("GPS", "Enabled");

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
