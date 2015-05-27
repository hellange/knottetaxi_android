package com.example.knottetaxi_android;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.example.gpstracking.R;
 
public class MainActivity extends Activity {
     
    Button btnShowLocation;
     
    // GPSTracker class
    GPSTracker gps;
     
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);
         
        // show location button click event
        btnShowLocation.setOnClickListener(new View.OnClickListener() {
             
            @Override
            public void onClick(View arg0) {        
                // create class object
            	
            	Log.i("MainActivity", "onclick ");
                HttpClient httpclient = new DefaultHttpClient();

                HttpGet request = new HttpGet();
                try {
                URI website = new URI("http://weather2-helge.rhcloud.com/weather/history/0?format=json");
                request.setURI(website);
                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                
            	Log.e("MainActivity", "Response : "+responseString);

                } catch (Exception ex) {
                	Log.e("MainActivity", "Exception : "+ex.getMessage());

                }	
   
                
                gps = new GPSTracker(MainActivity.this);
 
                // check if GPS enabled     
                if(gps.canGetLocation()){
                     
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                     
                    // \n is for new line
                    Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();    
                }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }
                 
            }
        });
    }
     
}