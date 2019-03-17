package top.rish.collect.common.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

public class FreemarkerUtil {

    public static Template getTemplate(String ftlName, String ftlPath)  {
        try {
            Configuration cfg = new Configuration();  												//通过Freemaker的Configuration读取相应的ftl
            cfg.setEncoding(Locale.CHINA, "utf-8");
            cfg.setDirectoryForTemplateLoading( new File(ftlPath));		//设定去哪里读取相应的ftl模板文件
            Template temp = cfg.getTemplate(ftlName);												//在模板文件目录中找到名称为name的文件
            return temp;
        } catch ( Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public void print(String name,String ftlPath,Map<String, Object> root) {
        //通过Template可以将模版文件输出到相应的文件流
        Template template = this.getTemplate(name,ftlPath);
        try {
            template.process(root, new PrintWriter(System.out));//在控制台输出内容
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 输出HTML文件
     *
     * @param name
     * @param root
     * @param outFile
     */
    public void fprint(String name,String ftlPath,Map<String, Object> root, String outFile) {
        FileWriter out = null;
        try {
            // 通过一个文件输出流，就可以写到相应的文件中，此处用的是绝对路径
            out = new FileWriter(new File(outFile));
            Template temp = this.getTemplate(name,ftlPath);
            temp.process(root, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}