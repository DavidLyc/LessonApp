package com.david.aclass.util;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkUtils {

    private static final OkHttpClient mOkHttpClient = new OkHttpClient()
            .newBuilder()
            .readTimeout(3, TimeUnit.SECONDS)
            .build();
    private static final String TIMEOUT = "timeout";
    private static final String IP = "http://192.168.31.250:8788";
    private static final String LOGIN_IP = "/user/login";
    private static final String REGISTER_IP = "/user/register";
    private static final String LESSON_IP = "/lesson/";

    public static String login(String name, String password) {
        RequestBody body = new FormBody.Builder()
                .add("name", name)
                .add("password", password)
                .build();
        Request request = new Request.Builder()
                .url(IP + LOGIN_IP)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TIMEOUT;
    }

    public static String register(String name, String password, String Class) {
        RequestBody body = new FormBody.Builder()
                .add("name", name)
                .add("password", password)
                .add("class", Class)
                .build();
        Request request = new Request.Builder()
                .url(IP + REGISTER_IP)
                .post(body)
                .build();
        Call call = mOkHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TIMEOUT;
    }

    public static String getLessons(String major) {
        Request request = new Request.Builder().url(IP + LESSON_IP + major)
                .get().build();
        Call call = mOkHttpClient.newCall(request);
        try {
            Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return TIMEOUT;
    }

}
