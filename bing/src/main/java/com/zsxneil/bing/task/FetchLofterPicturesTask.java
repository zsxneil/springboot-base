package com.zsxneil.bing.task;

import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

@Component
public class FetchLofterPicturesTask {

    private static final Logger log = LoggerFactory.getLogger(FetchLofterPicturesTask.class);


    @Value("${lofter.image.dir}")
    private String imageDir;

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build();


    @Scheduled(cron="0 02 0 * * ?")
    public void fetchLofterImages() throws IOException {
        String url = "http://fenchenyue.lofter.com/";
        Document doc = Jsoup.connect(url).get();
        Element pageInfo = doc.getElementsByClass("num").first();
        String text = pageInfo.text();
        String[] page = text.split("/");
        int startPage = Integer.valueOf(page[0]);
        int endPage = Integer.valueOf(page[1]);
        for (int i=startPage; i<=endPage ;i++) {
            url = String.format("%s?page=%d&t=%d", url, i, System.currentTimeMillis());
            log.info("pageUrl:" + url);
            fetchLofterPagedImages(url);

        }
    }

    public void fetchLofterPagedImages(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Element list = doc.getElementById("list");
            if (list != null) {
                Elements linkList = list.getElementsByTag("li");
                log.info(linkList.size() + "");
                for (Element link_li : linkList) {
                    Element link_a = link_li.getElementsByTag("a").get(0);
                    String link = link_a.attr("href");
                    fetchBigImages(link);
                }
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(),e);
        }

    }

    public void fetchBigImages(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Element imageDiv = doc.getElementsByClass("image").get(0);
            Elements srcList = imageDiv.getElementsByTag("p");
            log.info(srcList.size() + "");
            for (Element src_p : srcList) {
                if (src_p.hasClass("des"))
                    continue;
                Element src_a = src_p.getElementsByTag("a").first();
                String src = src_a.attr("bigimgsrc");
                downloadImage(src, client);
            }
        } catch (Exception e) {
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
                    String fileName = url.substring(url.lastIndexOf('/') + 1, url.indexOf('?'));
                    String prefix = url.substring(url.indexOf("//") + 2, url.indexOf("."));
                    fileName = prefix + "_" + fileName;
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

    public static void main(String[] args) {
        String url = "http://imglf6.nosdn.127.net/img/RzJPU1E4RWk4SHNyWlV0MlZXZWw1L1B5U2dFYi85aWVRMmRvNTlJVlFXQTZ4WFFPQlVXZTF3PT0.jpg?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg";
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.indexOf('?'));
        String prefix = url.substring(url.indexOf("//") + 2, url.indexOf("."));
        System.out.println(prefix);
    }


}
