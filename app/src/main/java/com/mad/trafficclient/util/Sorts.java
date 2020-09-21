package com.mad.trafficclient.util;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

import com.mad.bean.RechargeBean;
import com.mad.bean.RechargeLogBean;
import com.mad.bean.TrafficLightBean;

public class Sorts {
	
	public static final int MODE_UP = 0;
	public static final int MODE_DOWN = 1;
	
	public static final int MODE_TRAFFIC_ID_UP = 2;
	public static final int MODE_TRAFFIC_ID_DOWN = 3;
	public static final int MODE_TRAFFIC_GREEN_UP = 6;
	public static final int MODE_TRAFFIC_GREEN_DOWN = 7;
	public static final int MODE_TRAFFIC_RED_UP = 4;
	public static final int MODE_TRAFFIC_RED_DOWN = 5;
	public static final int MODE_TRAFFIC_YELLOW_UP = 8;
	public static final int MODE_TRAFFIC_YELLOW_DOWN = 9;
	
	public static LinkedList<RechargeLogBean> recharge(RechargeLogBean[] beans, final int order) {
		Comparator<RechargeLogBean> com = new Comparator<RechargeLogBean>() {

			@Override
			public int compare(RechargeLogBean arg0, RechargeLogBean arg1) {
				return (int) (order == MODE_UP ? arg0.getTime() - arg1.getTime() : arg1.getTime() - arg0.getTime());
			}
			
		};
		LinkedList<RechargeLogBean> list = new LinkedList<RechargeLogBean>();
		Arrays.sort(beans, com);
		for (RechargeLogBean bean : beans) {
			list.add(bean);
		}
		return list;
	}
	
	@RequiresApi(api = Build.VERSION_CODES.N)
	public static ArrayList<TrafficLightBean> trafficLight(ArrayList<TrafficLightBean> beans, final int mode) {
		Comparator<TrafficLightBean> com = new Comparator<TrafficLightBean>() {
			
			@Override
			public int compare(TrafficLightBean arg0, TrafficLightBean arg1) {
				int r = 0;
				switch (mode) {
				case MODE_TRAFFIC_ID_UP:
					r = arg0.getRoadId() - arg1.getRoadId();
					break;
				case MODE_TRAFFIC_ID_DOWN:
					r = arg1.getRoadId() - arg0.getRoadId();
					break;
				case MODE_TRAFFIC_GREEN_UP:
					r = arg0.getGreenTime() - arg1.getGreenTime();
					break;
				case MODE_TRAFFIC_GREEN_DOWN:
					r = arg1.getGreenTime() - arg0.getGreenTime();
					break;
				case MODE_TRAFFIC_RED_UP:
					r = arg0.getRedTime() - arg1.getRedTime();
					break;
				case MODE_TRAFFIC_RED_DOWN:
					r = arg1.getRedTime() - arg0.getRedTime();
					break;
				case MODE_TRAFFIC_YELLOW_UP:
					r = arg0.getYellowTime() - arg1.getYellowTime();
					break;
				case MODE_TRAFFIC_YELLOW_DOWN:
					r = arg1.getYellowTime() - arg0.getYellowTime();
					break;
				default:
					break;
				}
				return r;
			}
		};
		beans.sort(com);
		return beans;
	}
}
