package com.ignblanch.shopping;

import java.util.Collections;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainListFragment extends Fragment {
	ListView list;
	Context context;
	ArrayAdapter<String> adapterProducts;
	View rootView;
	private Animation anim1;

	// to display list in listView
	public void setupList() {
		list = (ListView) rootView.findViewById(R.id.listShopping);
		list.setFastScrollEnabled(true);
		adapterProducts = new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, Lists.getItems());
		Collections.sort(Lists.getItems(), String.CASE_INSENSITIVE_ORDER); // Keep items sorted
		adapterProducts.notifyDataSetChanged();
		list.setAdapter(adapterProducts);

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
				 final int position, long id) {
				anim1 = AnimationUtils.loadAnimation(context, R.anim.move_right);
				view.startAnimation(anim1);
				Handler handle = new Handler();
				handle.postDelayed(new Runnable(){
					@Override
					public void run() {
						Lists.removeItem(Lists.getItems().get(position));
						adapterProducts.notifyDataSetChanged();
						list.setAdapter(adapterProducts);						
					}
					
				}, 250);

			}
		});
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.list_main, container, false);
		context = container.getContext();
		setupList();
		return rootView;
	}

	MyReceiver r;

	public void onPause() {
		super.onPause();
		LocalBroadcastManager.getInstance(context).unregisterReceiver(r);
	}

	public void onResume() {
		super.onResume();
		r = new MyReceiver();
		LocalBroadcastManager.getInstance(context).registerReceiver(r,
				new IntentFilter("TAG_REFRESH"));
	}

	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			MainListFragment.this.setupList();
		}
	}
}
