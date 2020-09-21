package com.mad.trafficclient;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mad.Initapp;
import com.mad.trafficclient.fragment.Fragment_4;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

public class Fragment_8 extends Fragment {

    private Spinner sp1;
    private EditText et1;
    private EditText et2;
    private TextView tv1;
    private LinearLayout ll1;
    private LinearLayout ll2;
    private TextView tv2;
    private ImageView im1;
    private int iw2;
    private int iw1;
    private int selectedItemPosition;
    private String[] ins;
    private Timer timer;
    private int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater
                .inflate(R.layout.fragment_layout08, container, false);

        sp1 = view.findViewById(R.id.sp1);
        et1 = view.findViewById(R.id.et1);
        tv1 = view.findViewById(R.id.tv1);
        et2 = view.findViewById(R.id.et2);
        tv2 = view.findViewById(R.id.tv2);
        im1 = view.findViewById(R.id.im1);
        ll1 = view.findViewById(R.id.ll1);
        ll2 = view.findViewById(R.id.ll2);

        InitData();
        return view;
    }

    private void InitData() {
        ins = new String[]{"1", "2", "3", "4"};
        sp1.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, ins));
        sp1.setSelection(0);


        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = et1.getText().toString();
                String string2 = et2.getText().toString();
                if (!string.equals("") && !string2.equals("")) {
                    iw1 = Integer.parseInt(string);
                    iw2 = Integer.parseInt(string2);
                    if (iw1 > 1 && iw1 >= 3 && iw2 <= 9) {
                        selectedItemPosition = sp1.getSelectedItemPosition();
                        ll1.setVisibility(View.GONE);
                        ll2.setVisibility(View.VISIBLE);
                        stTimer();
                    } else {
                        Initapp.toast("生成失败");
                    }
                } else {
                    Initapp.toast("生成失败");
                }

            }
        });

        im1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                tv2.setVisibility(View.VISIBLE);
                tv2.setText("车号：" + ins[selectedItemPosition]+"付款金额："+iw1+"");
                return true;
            }
        });
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (index == 0) {
                    index = 1;
                    ViewGroup.LayoutParams layoutParams = im1.getLayoutParams();
                    layoutParams.width = 1280;
                    layoutParams.height = 800;
                    im1.setTranslationY(-150);
                    im1.setLayoutParams(layoutParams);
                } else {
                    index = 0;
                    ViewGroup.LayoutParams layoutParams = im1.getLayoutParams();
                    layoutParams.width = 300;
                    layoutParams.height = 300;
                    im1.setTranslationY(0);
                    im1.setLayoutParams(layoutParams);
                }


            }
        });
    }

    public void stTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                final Bitmap imag = getImag(Math.random()+"");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        im1.setImageBitmap(imag);
                    }
                });
            }
        },iw2*1000);

    }

    @Override
    public void onDestroyView() {
        timer.cancel();
        super.onDestroyView();
    }

    public Bitmap getImag(String text) {
        try {
            int size = 300;
            Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            hints.put(EncodeHintType.MARGIN, 2);
            BitMatrix encode = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, size,size ,hints);
            int[] ints = new int[size * size];
            for (int y = 0; y < size; y++) {
                for (int x = 0; x < size; x++) {
                    if (encode.get(x, y)) {
                        ints[y * size + x] = Color.BLACK;
                    } else {
                        ints[y * size + x] = Color.WHITE;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.RGB_565);
            bitmap.setPixels(ints, 0, size, 0, 0, size, size);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

}
