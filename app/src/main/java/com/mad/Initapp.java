package com.mad;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mad.bean.BaseBean;
import com.mad.bean.Car;
import com.mad.bean.User;
import com.mad.db.DB;
import com.mad.trafficclient.httppost.HttpPostRequest;
import com.mad.trafficclient.util.LoadingDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Initapp extends Application {

    public static LoadingDialog loadingDialog;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;
    public static Context context;
    private static Toast tos;
    public static User.ROWSDETAILBean user;
    public static String url;
    public static Gson gson;
    public static DB db;
    public static BaseBean baseBean;
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        sp = getSharedPreferences("settings", MODE_PRIVATE);
        edit = sp.edit();
        gson = new Gson();
        url = "http://192.168.1.120:8080/api/v2/";
        db = new DB(context, "qb.db", null, 1);
        queue = Volley.newRequestQueue(context);
        baseBean = new BaseBean();
//        setData();
    }

    public static void setData() {
//            HttpPostRequest postRequest = new HttpPostRequest();
////            postRequest.requestHttp(url + "get_all_user_info", user);
//            JSONArray array = new JSONObject(postRequest.getWebContext()).getJSONArray("ROWS_DETAIL");
//            Log.i("==", "setData: " + postRequest.getWebContext());
        doPost("get_all_user_info", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray array = jsonObject.getJSONArray("ROWS_DETAIL");
                    for (int i = 0, l = array.length(); i < l; i++) {
                        JSONObject object = array.getJSONObject(i);
                        db.insert("user", baseBean.fromJson(object));
                    }
                    Cursor query = db.query("user", "username = ?", "user1");
                    while (query.moveToNext()) {
                        Log.i("==", "onResponse: "+query.getString(0));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public static void getUser(String type, String name) {
        try {
            JSONArray array = new JSONObject(getData("User")).getJSONArray("ROWS_DETAIL");
            for (int i = 0, l = array.length(); i < l; i++) {
                JSONObject object = array.getJSONObject(i);
                if (name.equals(object.getString(type))) {
                    user = gson.fromJson(String.valueOf(object), User.ROWSDETAILBean.class);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<Car.ROWSDETAILBean> getCar(String type, String name) {
        try {
            JSONArray array = new JSONObject(getData("Car")).getJSONArray("ROWS_DETAIL");
            ArrayList<Car.ROWSDETAILBean> carlist = new ArrayList<>();
            for (int i = 0, l = array.length(); i < l; i++) {
                JSONObject object = array.getJSONObject(i);
                if (name.equals(object.getString(type))) {
                    carlist.add(gson.fromJson(String.valueOf(object), Car.ROWSDETAILBean.class));
                }
            }
            return carlist;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void doPost(String path, Map map, Response.Listener<JSONObject> listener) {
        HashMap hashMap = new HashMap();
        hashMap.put("UserName", "user1");
        JSONObject object = new JSONObject(hashMap);
        if (map != null) {
            object = new JSONObject(map);
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url + path, object, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (volleyError.networkResponse == null) {
                    toast("网络连接故障");
                }
            }
        });
        queue.add(request);
    }

    public static void toast(String s) {
        if (null != tos) {
            tos.cancel();
        }
        tos = Toast.makeText(context, s, Toast.LENGTH_LONG);
        tos.show();
    }

    public static int random(int a, int b) {
        return (int) Math.round(Math.random() * (a - b) + b);
    }

    public static String getData(String path) {
        StringBuffer data = new StringBuffer();
        try {
            InputStream inputStream = context.getAssets().open(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String len;
            while ((len = bufferedReader.readLine()) != null) {
                data.append(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(data);
    }

}
