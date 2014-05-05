package com.example.androidfinalprojectvillamil;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Distances extends Activity {
	
	private ArrayList<String> driveDistances;
	private ArrayList<String> threeWDistances;
	private ArrayList<String> fifeWDistances;
	private ArrayList<String> hibridDistances;
	private ArrayList<String> fifeIDistances;
	private ArrayList<String> sixIDistances;
	private ArrayList<String> sevenIDistances;
	private ArrayList<String> eightIDistances;
	private ArrayList<String> nineIDistances;
	
	private ArrayAdapter<String> drivesAdapter;
	private ArrayAdapter<String> threeWAdapter;
	private ArrayAdapter<String> fifeWAdapter;
	private ArrayAdapter<String> hibridAdapter;
	private ArrayAdapter<String> fifeIAdapter;
	private ArrayAdapter<String> sixIAdapter;
	private ArrayAdapter<String> sevenIAdapter;
	private ArrayAdapter<String> eightIAdapter;
	private ArrayAdapter<String> nineIAdapter;
	
	private ListView drivesList;
	private ListView threeWList;
	private ListView fifeWList;
	private ListView hibridList;
	private ListView fifeIList;
	private ListView sixIList;
	private ListView sevenIList;
	private ListView eightIList;
	private ListView nineIList;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distances);
		
		//find list views
		drivesList = (ListView) findViewById(R.id.driversList);
		threeWList = (ListView) findViewById(R.id.threeWList);
		fifeWList = (ListView) findViewById(R.id.fifeWList);
		hibridList = (ListView) findViewById(R.id.hibridList);
		fifeIList = (ListView) findViewById(R.id.fifeIList);
		sixIList = (ListView) findViewById(R.id.sixIList);
		sevenIList = (ListView) findViewById(R.id.sevenIList);
		eightIList = (ListView) findViewById(R.id.eightIList);
		nineIList = (ListView) findViewById(R.id.nineIList);

		//get arrays passed in the intent
		Intent intent = getIntent();
		driveDistances = intent.getStringArrayListExtra("drivers");
		threeWDistances = intent.getStringArrayListExtra("3W");
		fifeWDistances = intent.getStringArrayListExtra("5W");
		hibridDistances = intent.getStringArrayListExtra("hibrid");
		fifeIDistances = intent.getStringArrayListExtra("5I");
		sixIDistances = intent.getStringArrayListExtra("6I");
		sevenIDistances = intent.getStringArrayListExtra("7I");
		eightIDistances = intent.getStringArrayListExtra("8I");
		nineIDistances = intent.getStringArrayListExtra("9I");

		//set adapters in lists
		drivesAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, driveDistances);
		drivesList.setAdapter(drivesAdapter);
		
		threeWAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, threeWDistances);
		threeWList.setAdapter(threeWAdapter);
		
		fifeWAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, fifeWDistances);
		fifeWList.setAdapter(fifeWAdapter);
		
		hibridAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, hibridDistances);
		hibridList.setAdapter(hibridAdapter);
		
		fifeIAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, fifeIDistances);
		fifeIList.setAdapter(fifeIAdapter);
				
		sixIAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, sixIDistances);
		sixIList.setAdapter(sixIAdapter);
		
		sevenIAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, sevenIDistances);
		sevenIList.setAdapter(sevenIAdapter);
		
		eightIAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, eightIDistances);
		eightIList.setAdapter(eightIAdapter);
		
		nineIAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, nineIDistances);
		nineIList.setAdapter(nineIAdapter);
		
		 registerForContextMenu(drivesList);
		 registerForContextMenu(threeWList);
		 registerForContextMenu(fifeWList);
		 registerForContextMenu(hibridList);
		 registerForContextMenu(fifeIList);
		 registerForContextMenu(sixIList);
		 registerForContextMenu(sevenIList);
		 registerForContextMenu(eightIList);
		 registerForContextMenu(nineIList);

	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.deleteScore:
			if (info.targetView == findViewById(R.id.driversList)) {
				drivesAdapter.remove(driveDistances.get(info.position));
				drivesAdapter.notifyDataSetChanged();
				return true;
			}
		default:
			return super.onContextItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.distances, menu);
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
