package com.manu.lumos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.manu.lumos.Model.Explore.Explore;
import com.manu.lumos.Model.Explore.Item_;
import com.manu.lumos.Service.FourSquareService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

	EditText etGeolocation, etQuery;
	Button btnSearch, btnReload;
	ListView listView;
	String Client_ID = "3W4H2CLZFQF5BGFUC1ZNTRJ4FTL54UZUCVGMXNMTRNU2APZG";
	String Client_Secret = "NLHCLRXGMNR0I1RO22TTFQ03331OIBJUQH4PJQTLB13AGOJ4";
	String apiVersion = "20180421";
	String geoLocation = "";
	String query = "cafe";
	String Loc;

	List<Item_> item_list = new ArrayList<Item_>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		btnReload = (Button) findViewById(R.id.btn_reload);
		btnSearch = (Button) findViewById(R.id.btn_search);

//		btnReload.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				Intent i=getIntent();
//				finish();
//				startActivity(i);
//			}
//		});

		Loc = getIntent().getStringExtra("CurrentLocation");
		etGeolocation = (EditText) findViewById(R.id.et_geolocation);
		etGeolocation.setText(Loc);
		Log.d("OKOKOKOKOK", Loc);

		btnSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
//				ExploreAsyncTask exploreAsyncTask = new ExploreAsyncTask();
//				exploreAsyncTask.execute();
				findViewByIds();
				geoLocation=Loc;

				FourSquareService fourSquareService = FourSquareService.retrofit.create(FourSquareService.class);
				final Call<Explore> call = fourSquareService.requestExplore(Client_ID, Client_Secret, apiVersion, geoLocation, query);
				call.enqueue(new Callback<Explore>() {
					@Override
					public void onResponse(Call<Explore> call, Response<Explore> response) {
						item_list = response.body().getResponse().getGroups().get(0).getItems();
						ExploreListAdapter exploreListAdapter = new ExploreListAdapter(getApplicationContext(), R.layout.item_list, item_list);
						listView.setAdapter(exploreListAdapter);
					}

					@Override
					public void onFailure(Call<Explore> call, Throwable t) {

					}
				});
			}
		});
	}
	void findViewByIds(){
		etGeolocation = (EditText) findViewById(R.id.et_geolocation);
		etQuery = (EditText) findViewById(R.id.et_query);
		listView = (ListView)findViewById(R.id.listivew);
//		geoLocation=etGeolocation.getText().toString();
		query=etQuery.getText().toString();
	}

	public class ExploreAsyncTask extends AsyncTask<Void,Void,List<Item_>> {

		public ExploreAsyncTask() {
			super();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected List<Item_> doInBackground(Void... voids) {
			FourSquareService fourSquareService = FourSquareService.retrofit.create(FourSquareService.class);
			final Call<Explore> call = fourSquareService.requestExplore(Client_ID, Client_Secret, apiVersion, geoLocation, query);

			try {
				Explore explore =  call.execute().body();
				item_list = explore.getResponse().getGroups().get(0).getItems();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return item_list;
		}

		@Override
		protected void onPostExecute(List<Item_> item_s) {
			super.onPostExecute(item_s);
			ExploreListAdapter exploreListAdapter = new ExploreListAdapter(getApplicationContext(), R.layout.item_list, item_s);
			listView.setAdapter(exploreListAdapter);
		}
	}
}
