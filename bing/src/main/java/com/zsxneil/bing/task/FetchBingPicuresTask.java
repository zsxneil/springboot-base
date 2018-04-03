package com.zsxneil.bing.task;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class FetchBingPicuresTask {

    private static final Logger log = LoggerFactory.getLogger(FetchBingPicuresTask.class);

    private static final String URL_PREFIX = "https://www.bing.com";

    @Value("${bing.image.dir}")
    private String imageDir;

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build();

    @Scheduled(cron="0 59 23 * * ?")
    public void fetchBingPictures() {


        this.fetchIndexedImages(0, client);
        this.fetchIndexedImages(10, client);

    }

    private void fetchIndexedImages(int index, OkHttpClient client) {
        String image_url = String.format("%s/HPImageArchive.aspx?format=js&idx=%d&n=20", URL_PREFIX, index);
        Request request = new Request
                .Builder()
                .url(image_url)
                .get()
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String result = response.body().string();
                JSONObject json = JSONObject.parseObject(result);
                if (json.containsKey("images")) {
                    JSONArray imageList = json.getJSONArray("images");
                    for (int i=0; i<imageList.size(); i++) {
                        try {
                            JSONObject imageInfo = imageList.getJSONObject(i);
                            String url = imageInfo.getString("url");
                            if (StringUtils.hasText(url)) {
                                url = URL_PREFIX + url;
                                downloadImage(url ,client);
                            }
                        } catch (Exception e){
                            log.error(e.getLocalizedMessage(), e);
                        }
                    }
                }
            } else {
                log.info(String.format("request image url failed, response code is %d, response body is %s", response.code(), response.body().string()));
            }
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }

    private void downloadImage(String url, OkHttpClient client) throws Exception {
        log.info("imageUrl:" + url);
        Request request = new Request
                .Builder()
                .url(url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                log.error(e.getLocalizedMessage(),e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String fileName = url.substring(url.lastIndexOf('/'));
                    OutputStream out = null;
                    try {
                        out = new FileOutputStream(new File(imageDir, fileName));
                        out.write(response.body().bytes());
                    } catch (FileNotFoundException e) {
                        log.error(e.getLocalizedMessage(),e);
                    } finally {
                        out.close();
                    }

                }
            }
        });
    }

}
