/**
 * 
 */
package com.mad.trafficclient.fragment;

import com.mad.trafficclient.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class Fragment_1 extends Fragment
{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater
				.inflate(R.layout.fragment_layout01, container, false);
		TextView textview = (TextView) view.findViewById(R.id.textview);
		textview.setText("this is fragment 1");
		ListView lv1 = view.findViewById(R.id.lv1);
		TextView tv1 = view.findViewById(R.id.lv1);
		Spinner sp1 = view.findViewById(R.id.sp1);
		initData();


		return view;
	}

	public void initData() {





	}


}
