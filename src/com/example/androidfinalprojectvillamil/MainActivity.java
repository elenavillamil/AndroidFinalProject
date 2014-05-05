package com.example.androidfinalprojectvillamil;

import java.text.DecimalFormat;
import java.util.ArrayList;

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


	private Button startButton;
	private Button stopButton;
	private TextView distanceTextView;
	private LocationManager locationManager;
	private Spinner clubSelectionSpinner;
	private String clubSelection;
	private String provider;
	
	private Location mStartLocation, mLastKnownLocation;
	private DecimalFormat df = new DecimalFormat("#.00");

	private ArrayList<String> driveDistances = new ArrayList<String>();
	private ArrayList<String> threeWDistances = new ArrayList<String>();
	private ArrayList<String> fifeWDistances = new ArrayList<String>();
	private ArrayList<String> hibridDistances = new ArrayList<String>();
	private ArrayList<String> fifeIDistances = new ArrayList<String>();
	private ArrayList<String> sixIDistances = new ArrayList<String>();
	private ArrayList<String> sevenIDistances = new ArrayList<String>();
	private ArrayList<String> eightIDistances = new ArrayList<String>();
	private ArrayList<String> nineIDistances = new ArrayList<String>();

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
				Boolean inserted = false;
				Log.i("myDebugStopButton", "stop has been clicked");
				
				if (mStartLocation != null) {
					final float delta = mStartLocation.distanceTo(mLastKnownLocation);
					
	    			distanceTextView.setText(df.format(delta) + " meters");

	    			if (clubSelection.equals("Drive")) {
	    				for (int i = 0; i < driveDistances.size(); i ++) {
	    					if (Float.valueOf(driveDistances.get(i)) > delta) {
	    						driveDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					driveDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("3 Wood")) {
	    				for (int i = 0; i < threeWDistances.size(); i ++) {
	    					if (Float.valueOf(threeWDistances.get(i)) > delta) {
	    						threeWDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					threeWDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("5 Wood")) {
	    				for (int i = 0; i < fifeWDistances.size(); i ++) {
	    					if (Float.valueOf(fifeWDistances.get(i)) > delta) {
	    						fifeWDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					fifeWDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("Hibrid")) {
	    				for (int i = 0; i < hibridDistances.size(); i ++) {
	    					if (Float.valueOf(hibridDistances.get(i)) > delta) {
	    						hibridDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					hibridDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("5 Iron")) {
	    				for (int i = 0; i < fifeIDistances.size(); i ++) {
	    					if (Float.valueOf(fifeIDistances.get(i)) > delta) {
	    						fifeIDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					fifeIDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("6 Iron")) {
	    				for (int i = 0; i < sixIDistances.size(); i ++) {
	    					if (Float.valueOf(sixIDistances.get(i)) > delta) {
	    						sixIDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					sixIDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("7 Iron")) {
	    				for (int i = 0; i < sevenIDistances.size(); i ++) {
	    					if (Float.valueOf(sevenIDistances.get(i)) > delta) {
	    						sevenIDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					sevenIDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("8 Iron")) {
	    				for (int i = 0; i < eightIDistances.size(); i ++) {
	    					if (Float.valueOf(eightIDistances.get(i)) > delta) {
	    						eightIDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					eightIDistances.add(df.format(delta));
	    				}
	    			} else if (clubSelection.equals("9 Iron")) {
	    				for (int i = 0; i < nineIDistances.size(); i ++) {
	    					if (Float.valueOf(nineIDistances.get(i)) > delta) {
	    						nineIDistances.add(i, df.format(delta));
	    						inserted = true;
	    					}
	    				}
	    				
	    				if (!inserted) {
	    					nineIDistances.add(df.format(delta));
	    				}
	    			}
	    			mStartLocation = null;
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
			Intent intent = new Intent(this, Distances.class);
			intent.putExtra("drivers", driveDistances);
			intent.putExtra("3W", threeWDistances);
			intent.putExtra("5W", fifeWDistances);
			intent.putExtra("hibrid", hibridDistances);
			intent.putExtra("5I", fifeIDistances);
			intent.putExtra("6I", sixIDistances);
			intent.putExtra("7I", sevenIDistances);
			intent.putExtra("8I", eightIDistances);
			intent.putExtra("9I", nineIDistances);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onLocationChanged(Location location) {
		if (mStartLocation == null) {
			mStartLocation = mLastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		} else {
			mLastKnownLocation = location;
		}
		
		final float delta = mStartLocation.distanceTo(mLastKnownLocation);
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
