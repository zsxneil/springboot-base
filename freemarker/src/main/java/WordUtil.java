import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordUtil {

    public static void createWord() throws IOException, TemplateException {
        String fileName = "test.xml";
        String basePath = "F:/";
        String templateName = "document.ftl";
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("username", "Neil");

        //新建配置实例
        Configuration configuration = new Configuration();
        //设置编码
        configuration.setDefaultEncoding("UTF-8");
        //ftl模板文件
        configuration.setClassForTemplateLoading(WordUtil.class, "/");
        //获取模板
        Template template = configuration.getTemplate(templateName);

        //输出文件
        File out = new File(basePath + fileName);

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(out), "UTF-8"));

        //生成文件
        template.process(dataMap, writer);

        writer.flush();
        writer.close();
    }

    public static void main(String[] args) throws IOException, TemplateException {
        WordUtil.createWord();
    }

}
