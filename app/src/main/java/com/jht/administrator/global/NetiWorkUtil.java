package com.jht.administrator.global;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NetiWorkUtil {

    private static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.setTimeout(12000);
    }

    public static void getWithAbstrctAddress(String url, RequestParams params, AsyncHttpResponseHandler handle) {
        client.get(url, params, handle);
    }

    public static void postCS_URL(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

    public static void getWithAbstrctAddress(String url, AsyncHttpResponseHandler handle) {
        client.get(url, handle);
    }

    public static void getFileWithAbstrctAddress(String url, BinaryHttpResponseHandler handle) {
        client.get(url, handle);
    }


}
