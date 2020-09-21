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
import android.widget.TextView;


public class Fragment_2 extends Fragment {

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater
                .inflate(R.layout.fragment_layout02, container, false);
        listView = view.findViewById(R.id.lv_f2);
        setData();
        return view;
    }

    private void setData() {

    }

}
