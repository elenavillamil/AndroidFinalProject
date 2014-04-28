package com.example.androidfinalprojectvillamil;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	private double startLatitude;
	private double startLongitude;
	private double distance;
	private boolean startButtonClicked;
	private boolean measuring;
	private boolean stopped;
	private Button startButton;
	private Button stopButton;
	private TextView distanceTextView;
	private LocationManager locationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startButton = (Button) findViewById(R.id.startB);
		stopButton = (Button) findViewById(R.id.stopB);
		distanceTextView = (TextView) findViewById(R.id.showDistance);
		
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startButtonClicked = true;
			}
		});
		
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startButtonClicked = false;
				measuring = false;
				stopped = true;
			}
		});
		
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		Criteria crta = new Criteria();
	    crta.setAccuracy(Criteria.ACCURACY_FINE);
        String provider = locationManager.getBestProvider(crta, true);

        // String provider = LocationManager.GPS_PROVIDER;
        //Location location = locationManager.getLastKnownLocation(provider);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
              // Called when a new location is found by the network location provider.
            	if (startButtonClicked & !measuring & !stopped){
        			measuring = true;
        			startLatitude = location.getLatitude();
        			startLongitude = location.getLongitude();
        			distanceTextView.setText("0 meters");
        		}
        		else if(startButtonClicked & measuring & !stopped) {
        			//6371*cos-1(cos(Long1-Long2)cos(Lat1)cos(Lat2)+sin(Lat1)sin(Lat2)) 
        			distance = 6371 * Math.acos(Math.cos(startLongitude - location.getLongitude()) * Math.cos(startLatitude) * Math.cos(location.getLatitude() + Math.sin(startLatitude) * Math.sin(location.getLatitude())));
        			distanceTextView.setText(String.valueOf(distance) + " meters");
        		}
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
          };
          
          
        locationManager.requestLocationUpdates(provider, 1000, 0, locationListener);
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
