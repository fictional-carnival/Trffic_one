package com.mad;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.mad.db.DB;
import com.mad.trafficclient.httppost.HttpPostRequest;
import com.mad.trafficclient.util.LoadingDialog;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Initapp extends Application {

    public static LoadingDialog loadingDialog;
    public static SharedPreferences sp;
    public static SharedPreferences.Editor edit;
    public static Context context;
    private static Toast tos;
    public static String user;
    public static HttpPostRequest postRequest;
    public static String url;
    public static Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
        sp = getSharedPreferences("settings", MODE_PRIVATE);
        edit = sp.edit();
        gson = new Gson();
        url = "http://192.168.1.120:8080/api/v2/";
        postRequest = new HttpPostRequest();
//        new DB(context,)
//        HashMap hashMap = new HashMap();
//        hashMap.put("UserName", "user1");
        user = "{\"UserName\":\"user1\"}";
    }

    public static void setData() {
        postRequest.requestHttp(url + "get_all_user_info", user);

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
