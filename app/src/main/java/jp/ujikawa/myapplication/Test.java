package jp.ujikawa.myapplication;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class Test {

    private String baseURL;

    public Test(String baseURL) {
        this.baseURL = baseURL;
    }

    public String run() throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(baseURL).build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }
}
