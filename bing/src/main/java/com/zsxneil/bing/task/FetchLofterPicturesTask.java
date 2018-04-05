package com.zsxneil.bing.task;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.zsxneil.bing.model.LofterImage;
import com.zsxneil.bing.model.LofterSeries;
import com.zsxneil.bing.service.LofterImageService;
import com.zsxneil.bing.service.LofterSeriesService;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.net.URL;
import java.util.concurrent.TimeUnit;

@Component
public class FetchLofterPicturesTask {

    private static final Logger log = LoggerFactory.getLogger(FetchLofterPicturesTask.class);


    @Value("${lofter.rss.image.dir}")
    private String imageDir;

    @Value("${lofter.rss.url}")
    private String rssUrl;

    @Autowired
    LofterSeriesService seriesService;
    @Autowired
    LofterImageService imageService;

    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build();

    /**
     * 从rss中获取更新内容并解析出图片地址，下载图片
     * @throws IOException
     */
    @Scheduled(cron="0 10 17 * * ?")
    public void fetchLofterImages() {
        try (XmlReader reader = new XmlReader(new URL(rssUrl))) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            System.out.println("同步rss开始");
            for (SyndEntry entry : feed.getEntries()) {
                try {
                    String seriesLink = entry.getUri();
                    boolean existLink = seriesService.existLink(seriesLink);
                    if (existLink)
                        continue;
                    LofterSeries series = new LofterSeries();
                    series.setLink(seriesLink);
                    series.setPageno(-1);
                    seriesService.insert(series);
                    this.parseOutImageLinks(entry, series.getId());

                } catch (Exception e) {
                    log.error(e.getLocalizedMessage(),e);
                }
            }
            System.out.println("同步rss结束");
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(),e);
        }
    }

    private void parseOutImageLinks(SyndEntry entry, int referId) {
        SyndContent syndContent = entry.getDescription();
        syndContent.getValue();
        Document doc = Jsoup.parse(syndContent.getValue());
        Elements imgs = doc.getElementsByTag("img");
        for (Element img : imgs) {
            String src = img.attr("src");
            if (!StringUtils.hasText(src))
                continue;
            int position = src.indexOf("?");
            src = src.substring(0, position);
            src += "?imageView&thumbnail=1680x0&quality=96&stripmeta=0&type=jpg";
            //src = src.replaceAll("(thumbnail=[^&]*)","thumbnail=1680x0");
            LofterImage image = new LofterImage();
            image.setLink(src);
            image.setReferid(referId);
            imageService.insert(image);
            this.downloadImage(entry, src);
        }

    }

    private void downloadImage(SyndEntry entry, String src) {
        try {
            String seriesLink = entry.getUri();
            Request request = new Request
                    .Builder()
                    .url(src)
                    .get()
                    .build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    log.warn("下载失败：" +  call.request().url());
                    log.error(e.getLocalizedMessage(),e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String fileName = seriesLink.hashCode() + "_" + src.hashCode() + ".jpg";
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
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(),e);
        }
    }

    public static void main(String[] args) {

    }

}
