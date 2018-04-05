package com.zsxneil.bing;

import com.github.pagehelper.PageInfo;
import com.zsxneil.bing.model.LofterImage;
import com.zsxneil.bing.model.LofterSeries;
import com.zsxneil.bing.service.LofterImageService;
import com.zsxneil.bing.service.LofterSeriesService;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BingApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(BingApplicationTests.class);

	@Autowired
	LofterSeriesService seriesService;

	@Autowired
	LofterImageService imageService;

	@Value("${lofter.image.dir}")
	private String imageDir;

	/**
	 * 不可再执行，会导致重复
	 * @throws IOException
	 */
	@Test
	public void loadLofterSeriesLinks() throws IOException {
		String url = "http://fenchenyue.lofter.com/";
		Document doc = Jsoup.connect(url)
				.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
				.cookies(cookies())
				.get();
		Element pageInfo = doc.getElementsByClass("num").first();
		String text = pageInfo.text();
		String[] page = text.split("/");
		int startPage = Integer.valueOf(page[0]);
		int endPage = Integer.valueOf(page[1]);
		for (int i=startPage; i<=endPage ;i++) {
			String pageUrl = String.format("%s?page=%d", url, i);
			log.info("pageUrl:" + pageUrl);
			fetchLofterPagedImages(pageUrl, i);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void fetchLofterPagedImages(String url, int pageNo) {
		try {
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.cookies(cookies())
					.get();
			Element list = doc.getElementById("list");
			if (list != null) {
				Elements linkList = list.getElementsByTag("li");
				log.info(linkList.size() + "");
				for (Element link_li : linkList) {
					try {
						Element link_a = link_li.getElementsByTag("a").get(0);
						String link = link_a.attr("href");
						LofterSeries series = new LofterSeries();
						series.setLink(link);
						series.setPageno(pageNo);
						seriesService.insert(series);
					} catch (Exception e) {
						log.error(e.getLocalizedMessage(),e);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(),e);
		}

	}

	private Map<String ,String> cookies() {
		String cookieString = "usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; NETEASE_WDA_UID=498028853#|#1451350604125; firstentry=%2FartIndex.do%3FX-From-ISP%3D2|https%3A%2F%2Fblog.csdn.net%2Fwindroid%2Farticle%2Fdetails%2F44244347; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; reglogin_isLoginFlag=1; reglogin_isLoginFlag=1; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; __utma=61349937.1853319105.1522673977.1522845969.1522849759.4; __utmb=61349937.5.8.1522849759";
		String[] cookieArray = cookieString.split(";");
		Map<String, String> cookieMap = new HashMap<>();
		for (String item : cookieArray) {
			int index = item.indexOf("=");
			String key = item.substring(0, index).trim();
			String value = item.substring(index + 1).trim();
			cookieMap.put(key, value);
		}
		return cookieMap;
	}

	@Test
	public void pageTest() {
		PageInfo<LofterSeries> pageInfo = seriesService.selectOnePage(1);
		System.out.println(pageInfo);
		List<LofterSeries> seriesList = pageInfo.getList();
		for (LofterSeries series : seriesList) {
			log.info(series.toString());
		}
	}

	/**
	 * 不可再执行，会导致重复
	 */
	@Test
	public void fetchImageLinks() {
		PageInfo<LofterSeries> pageInfo = seriesService.selectOnePage(1);
		int totalPage = pageInfo.getPages();
		for (int page =0 ;page <= totalPage; page++){
			try {
				List<LofterSeries> seriesList = seriesService.selectOnePage(page).getList();
				for (LofterSeries series : seriesList) {
					try {
						fetchBigImages(series);
						TimeUnit.SECONDS.sleep(1);
					} catch (Exception e) {
						log.error(e.getLocalizedMessage(),e);
					}
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
			}
		}
	}

	public void fetchBigImages(LofterSeries series ) {
		try {
			String url = series.getLink();
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
					.cookies(cookies())
					.get();
			Element imageDiv = doc.getElementsByClass("image").get(0);
			Elements srcList = imageDiv.getElementsByTag("p");
			log.info(srcList.size() + "");
			for (Element src_p : srcList) {
				try {
					if (src_p.hasClass("des"))
						continue;
					Element src_a = src_p.getElementsByTag("a").first();
					String src = src_a.attr("bigimgsrc");
					LofterImage image = new LofterImage();
					image.setLink(src);
					image.setReferid(series.getId());
					imageService.insert(image);
					//downloadImage(src, client, url);
				} catch (Exception e) {
					log.error(e.getLocalizedMessage(),e);
				}
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage(), e);
		}

	}


	@Test
	public void imagePageTest() {
		PageInfo<HashMap<String ,Object> > pageInfo = imageService.selectPageImagesWithRefer(1);
		System.out.println(pageInfo);
		List<HashMap<String ,Object>> imageList = pageInfo.getList();
		for (HashMap<String, Object> map : imageList) {
			log.info(map.toString());
		}
	}

	/**
	 * 不可再执行
	 */
	//@Test
	public void downLoadImages() {
		OkHttpClient client = new OkHttpClient.Builder()
				.connectTimeout(10, TimeUnit.SECONDS)
				.readTimeout(100, TimeUnit.SECONDS)
				.build();
		PageInfo<HashMap<String ,Object> > pageInfo = imageService.selectPageImagesWithRefer(1);
		int totalPage = pageInfo.getPages();
		for(int page = 1; page <= totalPage ;page++) {
			try {
				List<HashMap<String, Object>> resultList = imageService.selectPageImagesWithRefer(page).getList();
				for (HashMap<String, Object> map : resultList) {
					this.downLoadImage(map, client);
				}
			} catch (Exception e) {
				log.error(e.getLocalizedMessage(),e);
			}
		}

		try {
			TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void downLoadImage(HashMap<String, Object> map, OkHttpClient client) {
		try {
			String refer = (String) map.get("refer");
			int refer_id = (int) map.get("referId");
			String src = (String) map.get("link");

			String prefix = StringUtils.hasText(refer) ? refer : String.valueOf(refer_id);

			Request request = new Request
					.Builder()
					.url(src)
					.header("Cookie","usertrack=ezq0pVrCKTRrpfTuA8+wAg==; JSESSIONID-WLF-XXD=884b08fdbdc7f7885891b6ee343688a938efadb1c06fbdbe75868a7ae15333abd6d2b55dfdecc7a442975853d8473c550e8ab63dfc11ac8df2e0d09fc4bc18c190216b44a2137d066f12e1b30127e22dbd7c85b13d054baa52e8a026060bdf584deb01a77ca082817496c65b38f6cde6e093f8f7f87ac3b78941fbd49294ebd2ce951603; _ntes_nnid=f858011badc59d1134cccf7eaec6332d,1522673977204; _ga=GA1.2.1853319105.1522673977; NETEASE_WDA_UID=498028853#|#1451350604125; __utmz=61349937.1522765445.1.1.utmcsr=blog.csdn.net|utmccn=(referral)|utmcmd=referral|utmcct=/windroid/article/details/44244347; reglogin_isLoginFlag=1; reglogin_isLoginFlag=1; _gid=GA1.2.2109176571.1522765606; __utmc=61349937; firstentry=%2Flogin.do%3FX-From-ISP%3D2|http%3A%2F%2Fwww.lofter.com%2Fcontrol%3FblogId%3D482481412%26postId%3D303965003; regtoken=2000; PRIVILEGE_USER_IDENTIFICATION=0; __utma=61349937.1853319105.1522673977.1522889326.1522893498.6; _gat=1; __utmb=61349937.19.7.1522893508877")
					.get()
					.build();

			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					log.error("下载失败：" + src);
					log.error(e.getLocalizedMessage(),e);
				}

				@Override
				public void onResponse(Call call, Response response) throws IOException {
					if (response.isSuccessful()) {
						//refer = StringUtils.hasText(refer) ? refer : refer_id + "";
						String fileName = prefix.hashCode() + "_" + src.hashCode() + ".jpg";
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

	@Test
	public void existLinkTest() {
		String link = "http://fenchenyue.lofter.com/post/1cc21504_128e9248";
		boolean result = seriesService.existLink(link);
		System.out.println(result);
	}


}
