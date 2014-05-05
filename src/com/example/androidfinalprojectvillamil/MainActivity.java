package com.example.androidfinalprojectvillamil;

import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements LocationListener {

	private double distance = 0.0;
	private boolean startButtonClicked;
	private boolean measuring;
	private boolean stopped;
	private boolean first;
	private Button startButton;
	private Button stopButton;
	private TextView distanceTextView;
	private LocationManager locationManager;
	private Spinner clubSelectionSpinner;
	private String clubSelection;
	private Location locA;
	private Location locB;
	private String provider;
	private Location oldLocation = null;
	
	private DecimalFormat df = new DecimalFormat("#.00");

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if (!enabled) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
		}
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		Criteria crta = new Criteria();
	    //crta.setAccuracy(Criteria.ACCURACY_FINE);
        provider = locationManager.getBestProvider(crta, false);

		clubSelectionSpinner = (Spinner) findViewById(R.id.clubs_sppiner);
		startButton = (Button) findViewById(R.id.startB);
		stopButton = (Button) findViewById(R.id.stopB);
		distanceTextView = (TextView) findViewById(R.id.showDistance);
		
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.clubs_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		clubSelectionSpinner.setAdapter(adapter);

		clubSelectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				clubSelection = parent.getItemAtPosition(position).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				clubSelection = "Drive";
			}
			
		});
		
		startButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("myDebugStartButton", "start has been clicked");
    			distanceTextView.setText("0 meters");
    			locationManager.requestLocationUpdates(provider, 1000, 1, MainActivity.this);
			}
		});
		
		stopButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log.i("myDebugStopButton", "stop has been clicked");
				if (locA != null) {
					final float delta = locA.distanceTo(locB);
					
	    			distanceTextView.setText(df.format(delta) + " meters");
					locA = null;

				}
    			locationManager.removeUpdates(MainActivity.this);

			}
		});
		
		
        // String provider = LocationManager.GPS_PROVIDER;
        //Location location = locationManager.getLastKnownLocation(provider);

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
		if (id == R.id.distances) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		
			if (locA == null) {
				locA = locB = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			} else {
				locB = location;
			}
			
			final float delta = locA.distanceTo(locB);
			distanceTextView.setText(String.valueOf(df.format(delta)) + " meters");
			Toast.makeText(this, "Delta = " + delta, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	
}
