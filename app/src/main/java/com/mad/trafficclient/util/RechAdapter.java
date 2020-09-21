package com.mad.trafficclient.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.mad.bean.RechargeLogBean;
import com.mad.trafficclient.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.content.Context;
import android.util.Base64;

public class RechAdapter extends BaseAdapter{
	private LinkedList<RechargeLogBean> beans;
	private LayoutInflater inflater;
	public RechAdapter(Context c ,LinkedList<RechargeLogBean> beans){
		inflater = LayoutInflater.from(c);
		this.beans = beans;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return beans.size()+1;
	}

	@Override
	public Object getItem(int arg0) {
		return beans.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		if(null == arg1)
			arg1 = inflater.inflate(R.layout.frag2_xml_item, null);
		TextView tv1 = (TextView) arg1.findViewById(R.id.item_tv1);
		TextView tv2 = (TextView) arg1.findViewById(R.id.item_tv2);
		TextView tv3 = (TextView) arg1.findViewById(R.id.item_tv3);
		TextView tv4 = (TextView) arg1.findViewById(R.id.item_tv4);
		TextView tv5 = (TextView) arg1.findViewById(R.id.item_tv5);
		if(arg0 == 0){
			tv1.setText("序号");
			tv2.setText("车号");
			tv3.setText("充值金额（元）");
			tv4.setText("操作人");
			tv5.setText("充值时间");
		}else{
			RechargeLogBean bean = beans.get(arg0-1);
			tv1.setText(arg0+"");
			tv2.setText(bean.getCarId()+"");
			tv3.setText(bean.getMoney()+"");
			tv4.setText(bean.getUser()+"");
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
			tv5.setText(format.format(new Date(bean.getTime()))+"");
		}
		return arg1;
	}

}
