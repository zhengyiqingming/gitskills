package com.baizhi.freemarker;

import com.baizhi.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FreemarkerTest {
    public static void main(String[] args) throws IOException, TemplateException {
        //1. 准备动态数据
        Map<String,Object> models = new HashMap<String,Object>();
        models.put("name","zs");
        models.put("user",new User(1,"xiaohuang"));
        models.put("age",18);
        models.put("birthday",new Date());
        models.put("bool",false);
        models.put("list", Arrays.asList("iphone","xiaomi","huawei"));

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("iphone","iphonexs max");
        map.put("xiaomi","红米6");
        map.put("huawei","mate20x");
        models.put("map",map);

        models.put("exists","aa"); //HashTable
        //2. 通过Freemarker的类库处理 产生最终的文本输出
        Configuration configuration=new Configuration(Configuration.VERSION_2_3_28);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setDirectoryForTemplateLoading(new File("F:\\大数据训练营\\20181106\\freemarker_demo\\src\\main\\resources\\templates")); // 模板文件的存放目录

        Template template = configuration.getTemplate("index.ftl");
        // 处理 加工方法
        template.process(models,new FileWriter("F:\\index.html"));
    }
}
