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
import android.widget.TextView;

public class Distances extends Activity {
	
	private ArrayList<String> mDistances;
	private ArrayAdapter<String> mAdapter;
	private ListView mDistancesList;
	private TextView mHeaderTextView;
	private String mHeader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_distances);
		
		//find views
		mDistancesList = (ListView) findViewById(R.id.distancesList);
		mHeaderTextView = (TextView) findViewById(R.id.header);
		
		//get parameters passed in the intent
		Intent intent = getIntent();
		mDistances = intent.getStringArrayListExtra("distances");
		mHeader = intent.getStringExtra("Header");
		
		//set adapters in lists
		mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				android.R.id.text1, mDistances);
		mDistancesList.setAdapter(mAdapter);
		
		mHeaderTextView.setText(mHeader);
		
		registerForContextMenu(mDistancesList);
	}
	
	@Override
	public void onBackPressed() {
		Intent intent = new Intent();
		intent.putStringArrayListExtra("distances", mDistances);
		intent.putExtra("Header", mHeader);
		setResult(Activity.RESULT_OK, intent);
		super.onBackPressed();

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
			mAdapter.remove(mDistances.get(info.position));
			mAdapter.notifyDataSetChanged();
			return true;
			
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
		//no action bar clicks that need too be handled
		return super.onOptionsItemSelected(item);
	}
}
