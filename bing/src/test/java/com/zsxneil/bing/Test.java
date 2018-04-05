package com.zsxneil.bing;

import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test {

    @org.junit.Test
    public  void  cookieTest() {
        String cookieString = "usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; NETEASE_WDA_UID=498028853#|#1451350604125; firstentry=%2FartIndex.do%3FX-From-ISP%3D2|https%3A%2F%2Fblog.csdn.net%2Fwindroid%2Farticle%2Fdetails%2F44244347; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; reglogin_isLoginFlag=1; reglogin_isLoginFlag=1; _gid=GA1.2.2109176571.1522765606; __utma=61349937.1853319105.1522673977.1522770480.1522845969.3; __utmc=61349937; __utmb=61349937.5.8.1522845970356";
        String[] cookieArray = cookieString.split(";");
        Map<String, String> cookieMap = new HashMap<>();
        for (String item : cookieArray) {
            int index = item.indexOf("=");
            String key = item.substring(0, index).trim();
            String value = item.substring(index + 1).trim();
            cookieMap.put(key, value);
        }
    }


    @org.junit.Test
    public void rssTest() throws Exception {
        String url = "http://fenchenyue.lofter.com/rss";

        try (XmlReader reader = new XmlReader(new URL(url))) {
            SyndFeed feed = new SyndFeedInput().build(reader);
            System.out.println(feed.getTitle());
            System.out.println("***********************************");
            for (SyndEntry entry : feed.getEntries()) {
                System.out.println(entry);
                SyndContent syndContent = entry.getDescription();
                syndContent.getValue();
                entry.getPublishedDate();
                System.out.println("***********************************");
            }
            System.out.println("Done");
        }
    }


    @org.junit.Test
    public void jsoupTest() {
        Document doc = Jsoup.parse("<p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf4.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0TXMwOFVSSS9aejhZRmw2dWFYeUx6d0JVV0F4Zm5HeE9BPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf5.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0Rzl6cjhIa0JiMmlnWVNaWUpaOU11YVRDaHdFRzY2OFFRPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf6.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0TytRYkdHWXFBb09jVEs3OHpQZTFaUndLZmFRay8xeWZ3PT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf3.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0S0diZFBweGdiODFlcE1vYlpmKzdlZ2hmT2RLV1F0NW9BPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf6.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0Sy8zbVB2Y0t1UjlGenJYNUk2NURCWWw0R2hQR25KbVlnPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf5.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0RVVUNVA4NmFncmdhTzVqZk9lQWFjNVdscStGWi9USHlBPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf5.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0UENKNjdCYjE1SGhZcWJmTi9ETHdwYVI1SUZiQ0ZJVmRBPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf4.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0SVZYR3NsaW9PYzE4OGVrOUdJdXc0TDlLS2V1ZU5pYWJnPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf5.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0SExKaW9rc2hIQVpFR2tYMFZWS0txMzBDdzFwS0lPbjhRPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p><p><a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'><img src=\"http://imglf5.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0Q0JiNU11SGhCWnVIblp6UXFUaGhaL3JhOENLWjhFSVBnPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=\" /></a></p></p><p class='text'></p><br/><br/><a target='_blank' href='http://fenchenyue.lofter.com'>LOFTER：MeiLu</a>&#160;&#160;&#160;<a target='_blank' href='http://fenchenyue.lofter.com/post/1cc21504_127b60a8'>http://fenchenyue.lofter.com/post/1cc21504_127b60a8</a>");
        Elements imgs = doc.getElementsByTag("img");
        for (Element img : imgs) {
            String src = img.attr("src");
            System.out.println(src);
        }
    }

    @org.junit.Test
    public void urlParamReplaceTest() {
        String url = "http://imglf4.nosdn.127.net/img/RzJPU1E4RWk4SHRIZHZGNTFjYTM0TXMwOFVSSS9aejhZRmw2dWFYeUx6d0JVV0F4Zm5HeE9BPT0.jpg?imageView&thumbnail=500x10000&quality=96&stripmeta=0&type=jpg%7Cwatermark&type=1&dx=0&gravity=southwest&dissolve=100&image=aW1nL3N5cy9zZWxlY3Rpb24tcnNzLXdhdGVybWFyay5wbmc=";
        url=url.replaceAll("(thumbnail=[^&]*)","thumbnail=1680x0");
        System.out.println(url);
    }



    @org.junit.Test
    public void okHttpCookieTest() throws IOException {
        final HashMap<String, List<Cookie>> cookieStore = new HashMap<>();
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        String domain = null;
                        if (cookies != null && cookies.size() > 0) {
                            domain = cookies.get(0).domain().replace("www.", "");
                            System.out.println("domain:" + domain);
                        }
                        List<Cookie> storedCookies = null;
                        if (StringUtils.hasText(domain)) {
                            storedCookies = cookieStore.get("domain");
                            if (storedCookies == null) {
                                storedCookies = new ArrayList<>();
                            }
                        }
                        storedCookies.addAll(cookies);
                        cookieStore.put(domain, storedCookies);
                        System.out.println("response:" + storedCookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = null;
                        Set<String> keySet = cookieStore.keySet();
                        for(String domain : keySet) {
                            if (url.host().contains(domain)) {
                                cookies = cookieStore.get(domain);
                                break;
                            }
                        }
                        System.out.println(url.host() + ":" + cookies);
                        return cookies != null ? cookies : new ArrayList<Cookie>();
                    }
                })
                .build();

        Request request0 = new Request.Builder()
                .url("http://fenchenyue.lofter.com/")
                .get()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                //.header("Cookie","usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; firstentry=%2Flogin.do%3FX-From-ISP%3D2|http%3A%2F%2Fwww.lofter.com%2Fcontrol%3FblogId%3D482481412%26postId%3D303965003; __utma=61349937.1853319105.1522673977.1522893498.1522898379.7; regtoken=2000; reglogin_isLoginFlag=; reglogin_isLoginFlag=; reglogin_hasopened=1; reglogin_hasopened=1; _gat=1; __utmb=61349937.26.7.1522899337718")
                .build();

        client.newCall(request0).execute();
        System.out.println(cookieStore);

        Request request = new Request.Builder()
                .url("http://www.lofter.com/control?blogId=482481412")
                .get()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                //.header("Cookie","usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; firstentry=%2Flogin.do%3FX-From-ISP%3D2|http%3A%2F%2Fwww.lofter.com%2Fcontrol%3FblogId%3D482481412%26postId%3D303965003; __utma=61349937.1853319105.1522673977.1522893498.1522898379.7; regtoken=2000; reglogin_isLoginFlag=; reglogin_isLoginFlag=; reglogin_hasopened=1; reglogin_hasopened=1; _gat=1; __utmb=61349937.26.7.1522899337718")
                .build();

        client.newCall(request).execute();
        System.out.println(cookieStore);



        Request request1 = new Request.Builder()
                .url("http://music.ph.126.net/ph.js?001")
                .get()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                //.header("Cookie","usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; firstentry=%2Flogin.do%3FX-From-ISP%3D2|http%3A%2F%2Fwww.lofter.com%2Fcontrol%3FblogId%3D482481412%26postId%3D303965003; __utma=61349937.1853319105.1522673977.1522893498.1522898379.7; regtoken=2000; reglogin_isLoginFlag=; reglogin_isLoginFlag=; reglogin_hasopened=1; reglogin_hasopened=1; _gat=1; __utmb=61349937.26.7.1522899337718")
                .build();

        client.newCall(request1).execute();

        System.out.println(cookieStore);



        Request request2 = new Request.Builder()
                .url("http://da.lofter.com/datacollect/v2/recdata/batchaction.do")
                .get()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                //.header("Cookie","usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; firstentry=%2Flogin.do%3FX-From-ISP%3D2|http%3A%2F%2Fwww.lofter.com%2Fcontrol%3FblogId%3D482481412%26postId%3D303965003; __utma=61349937.1853319105.1522673977.1522893498.1522898379.7; regtoken=2000; reglogin_isLoginFlag=; reglogin_isLoginFlag=; reglogin_hasopened=1; reglogin_hasopened=1; _gat=1; __utmb=61349937.26.7.1522899337718")
                .build();

        client.newCall(request2).execute();

        System.out.println(cookieStore);

        /*Request request3 = new Request.Builder()
                .url("http://analytics.163.com/ntes?_nacc=lofter&_nvid=041aba5f7c28fd0997c492c36a576c2d&_nvtm=0&_nvsf=1&_nvfi=1&_nlag=zh-cn&_nlmf=1522900622&_nres=1920x1080&_nscd=24-bit&_nstm=0&_nurl=http%3A//fenchenyue.lofter.com/&_ntit=MeiLu&_nref=&_nfla=&_nssn=&_nxkey=06221640.73091&_end1")
                .get()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                //.header("Cookie","usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; firstentry=%2Flogin.do%3FX-From-ISP%3D2|http%3A%2F%2Fwww.lofter.com%2Fcontrol%3FblogId%3D482481412%26postId%3D303965003; __utma=61349937.1853319105.1522673977.1522893498.1522898379.7; regtoken=2000; reglogin_isLoginFlag=; reglogin_isLoginFlag=; reglogin_hasopened=1; reglogin_hasopened=1; _gat=1; __utmb=61349937.26.7.1522899337718")
                .build();

        client.newCall(request3).execute();

        System.out.println(cookieStore);*/

    }
}
