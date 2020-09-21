/**
 * 
 */
package com.mad.trafficclient.fragment;

import com.google.gson.reflect.TypeToken;
import com.mad.Initapp;
import com.mad.bean.TrafficLightBean;
import com.mad.trafficclient.R;
import com.mad.trafficclient.util.Sorts;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Fragment_1 extends Fragment {

	private ArrayList<TrafficLightBean> traffics;
	private ListView lv1;
	private ListView lv2;
	private TextView tv1;
	private Spinner sp1;
	private RedAdapter redAdapter;
	private Spinner sp2;
	private TextView tv2;
	private RedAdapter2 redAdapter2;
	private LinearLayout ll1;
	private LinearLayout ll2;
	private LinearLayout ll3;
	private TextView tv3;
	private TextView tv4;
	private EditText et1;
	private EditText et2;
	private EditText et3;
	private ArrayList<Integer> integers;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_layout01, container, false);
		lv1 = view.findViewById(R.id.lv1);
		lv2 = view.findViewById(R.id.lv2);
		tv1 = view.findViewById(R.id.tv1);
		tv2 = view.findViewById(R.id.tv2);
		sp1 = view.findViewById(R.id.sp1);
		sp2 = view.findViewById(R.id.sp2);
		ll1 = view.findViewById(R.id.ll1);
		ll2 = view.findViewById(R.id.ll2);
		ll3 = view.findViewById(R.id.ll3);
		tv3 = view.findViewById(R.id.tv3);
		tv4 = view.findViewById(R.id.tv4);
		et1 = view.findViewById(R.id.et1);
		et2 = view.findViewById(R.id.et2);
		et3 = view.findViewById(R.id.et3);
		String pname = Initapp.user.getUsername();
		Log.i("asfsafa", "onCreateView: "+pname.substring(4, pname.length()));
		String substring = pname.substring(4, pname.length());
		if (Integer.parseInt(substring) < 3) {
			ll2.setVisibility(View.VISIBLE);
			ll3.setVisibility(View.GONE);
		} else {
			ll2.setVisibility(View.GONE);
			ll3.setVisibility(View.VISIBLE);
		}
		initData2();
		initData();
		return view;
	}

	private void initData2() {
		integers = new ArrayList<>();
		String reds = Initapp.sp.getString("reds", "");
		if (reds.equals("")) {
			traffics = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				TrafficLightBean trafficLightBean = new TrafficLightBean();
				trafficLightBean.setRoadId(i + 1);
				trafficLightBean.setRedTime(Initapp.random(1, 99));
				trafficLightBean.setGreenTime(Initapp.random(1, 99));
				trafficLightBean.setYellowTime(Initapp.random(3, 5));
				traffics.add(trafficLightBean);
			}
			Initapp.edit.putString("reds", Initapp.gson.toJson(traffics)).commit();
		} else {
			traffics = Initapp.gson.fromJson(reds, new TypeToken<List<TrafficLightBean>>() {
			}.getType());
		}
		redAdapter2 = new RedAdapter2();
		lv2.setAdapter(redAdapter2);
		String strings[] = new String[]{"路口升序", "路口降序", "红灯升序", "红灯降序", "绿灯升序", "绿灯降序", "黄灯升序", "黄灯降序"};
		sp2.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, strings));
		sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@RequiresApi(api = Build.VERSION_CODES.N)
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				traffics = Sorts.trafficLight(traffics, i + 2);
				redAdapter2.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});

		tv2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (integers.size() > 0) {
					ll1.setVisibility(View.VISIBLE);
				} else {
					Initapp.toast("没有选择！");
				}
			}
		});

		tv3.setOnClickListener(new View.OnClickListener() {
			@RequiresApi(api = Build.VERSION_CODES.N)
			@Override
			public void onClick(View view) {
				String et11 = et1.getText().toString();
				String et12 = et2.getText().toString();
				String et13 = et3.getText().toString();
				if (!et11.equals("") && !et12.equals("") && !et13.equals("")) {
					int iw1 = Integer.parseInt(et11);
					int iw2 = Integer.parseInt(et12);
					int iw3 = Integer.parseInt(et13);
					if (iw1 > 0 && iw2 > 0 && iw3 >= 3 && iw3 <= 5) {
						for (int i = 0; i < integers.size(); i++) {
							traffics.get(integers.get(i)).setRedTime(iw1);
							traffics.get(integers.get(i)).setGreenTime(iw2);
							traffics.get(integers.get(i)).setYellowTime(iw3);
						}
						Initapp.toast("设置成功！");
						Initapp.edit.putString("reds", Initapp.gson.toJson(traffics)).commit();
						traffics = Sorts.trafficLight(traffics, sp2.getSelectedItemPosition() + 2);
						redAdapter.notifyDataSetChanged();
						redAdapter2.notifyDataSetChanged();
						et1.setText("");
						et2.setText("");
						et3.setText("");

						integers.clear();
						ll1.setVisibility(View.GONE);
					} else {
						Initapp.toast("设置失败");
					}
				} else {
					Initapp.toast("设置失败");
				}
			}
		});
		tv4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ll1.setVisibility(View.GONE);
				et1.setText("");
				et2.setText("");
				et3.setText("");
				integers.clear();
				redAdapter2.notifyDataSetChanged();
			}
		});
	}

	public void initData() {


		String reds = Initapp.sp.getString("reds", "");
		if (reds.equals("")) {
			traffics = new ArrayList<>();
			for (int i = 0; i < 5; i++) {
				TrafficLightBean trafficLightBean = new TrafficLightBean();
				trafficLightBean.setRoadId(i + 1);
				trafficLightBean.setRedTime(Initapp.random(1, 99));
				trafficLightBean.setGreenTime(Initapp.random(1, 99));
				trafficLightBean.setYellowTime(Initapp.random(3, 5));
				traffics.add(trafficLightBean);
			}
			Initapp.edit.putString("reds", Initapp.gson.toJson(traffics)).commit();
		} else {
			traffics = Initapp.gson.fromJson(reds, new TypeToken<List<TrafficLightBean>>() {
			}.getType());
		}
		redAdapter = new RedAdapter();
		lv1.setAdapter(redAdapter);
		String strings[] = new String[]{"路口升序", "路口降序", "红灯升序", "红灯降序", "绿灯升序", "绿灯降序", "黄灯升序", "黄灯降序"};
		sp1.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, strings));


		sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@RequiresApi(api = Build.VERSION_CODES.N)
			@Override
			public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
				traffics = Sorts.trafficLight(traffics, i + 2);
				redAdapter.notifyDataSetChanged();
			}

			@Override
			public void onNothingSelected(AdapterView<?> adapterView) {
			}
		});


	}


	private class RedAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return traffics.size();
		}

		@Override
		public TrafficLightBean getItem(int i) {
			return traffics.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(int i, View view, ViewGroup viewGroup) {
			view = View.inflate(getContext(), R.layout.red_list, null);
			TextView tv_1 = view.findViewById(R.id.tv_1);
			TextView tv_2 = view.findViewById(R.id.tv_2);
			TextView tv_3 = view.findViewById(R.id.tv_3);
			TextView tv_4 = view.findViewById(R.id.tv_4);
			TrafficLightBean item = getItem(i);
			tv_1.setText(item.getRoadId() + "");
			tv_2.setText(item.getRedTime() + "");
			tv_3.setText(item.getYellowTime() + "");
			tv_4.setText(item.getGreenTime() + "");
			return view;
		}
	}

	private class RedAdapter2 extends BaseAdapter {
		@Override
		public int getCount() {
			return traffics.size();
		}

		@Override
		public TrafficLightBean getItem(int i) {
			return traffics.get(i);
		}

		@Override
		public long getItemId(int i) {
			return i;
		}

		@Override
		public View getView(final int i, View view, ViewGroup viewGroup) {
			view = View.inflate(getContext(), R.layout.red_list2, null);
			TextView tv_1 = view.findViewById(R.id.tv_1);
			TextView tv_2 = view.findViewById(R.id.tv_2);
			TextView tv_3 = view.findViewById(R.id.tv_3);
			TextView tv_4 = view.findViewById(R.id.tv_4);
			TextView tv_5 = view.findViewById(R.id.tv_5);
			CheckBox ch1 = view.findViewById(R.id.check1);
			TrafficLightBean item = getItem(i);
			tv_1.setText(item.getRoadId() + "");
			tv_2.setText(item.getRedTime() + "");
			tv_3.setText(item.getYellowTime() + "");
			tv_4.setText(item.getGreenTime() + "");
			if (integers.indexOf(i) != -1) {
				ch1.setChecked(true);
			} else {
				ch1.setChecked(false);
			}

			ch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					if (b) {
						integers.add(i);
					} else {
						integers.add(i);
					}
					notifyDataSetChanged();
				}
			});
			tv_5.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					ll1.setVisibility(View.VISIBLE);
					integers.clear();
					integers.add(i);
				}
			});
			return view;
		}
	}
}
