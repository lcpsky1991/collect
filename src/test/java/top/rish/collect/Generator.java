package top.rish.collect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.rish.collect.common.utils.FreemarkerUtil;
import top.rish.collect.common.utils.StringKit;
import top.rish.collect.entitys.ColumnInfo;
import top.rish.collect.entitys.TableInfo;
import top.rish.collect.mappers.mysql.DataBase;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Generator {


    //生成的数据源
    private static String DB ="mysql";
    //生成的表
    private static String[] TABLES = {"t_jk_data"};
    //ftl风格
    private static String DIR = "layui";
    private static String PROJECT_PATH;
    private static String PACHAGE_NAME = "top.rish.collect";
    private static String ENTITYS_PACKAGE ="entitys.mysql";
    private static String SERVICE_PACKAGE = "serivce.mysql";
    private static String CONTROL_PACKAGE = "controller";
    private static String AUTHOR = "xzm0705@gmail.com";
    private static String DATE ;
    @Autowired
    DataBase dataBase;

    static {
        switch (DB){
            case  "mysql":
                ENTITYS_PACKAGE = "entitys.mysql";
                SERVICE_PACKAGE = "service.mysql";
                break;
            case "sqlserver":
                ENTITYS_PACKAGE = "entitys.sqlserver";
                SERVICE_PACKAGE = "service.sqlserver";
                break;
            case "oracle":
                ENTITYS_PACKAGE = "entitys.oracle";
                SERVICE_PACKAGE = "service.oracle";
                break;
            default:
                break;
        }
        DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        PROJECT_PATH = System.getProperty("user.dir");
            /*String os = System.getProperty("os.name");
            if(os.toLowerCase().startsWith("win")){
                PROJECT_PATH = PROJECT_PATH.substring(1,PROJECT_PATH.length()-1);
            }*/

    }

    @Test
    public void gen(){
        System.out.println( DB);
    }
    @Test
    public void databaseTest(){
        TableInfo tableInfo = dataBase.getTableInfo(TABLES[0]);
        System.out.println(tableInfo);
        List<ColumnInfo> columnInfo = dataBase.getColumnInfo(TABLES[0]);
        System.out.println(columnInfo);

    }
    @Test
    public void genControllers(){
        TableInfo tableInfo = dataBase.getTableInfo(TABLES[0]);
        if(tableInfo!=null) {
            String tableName = tableInfo.getTableName();
            genController(tableName,
                    StringKit.toUpperCaseFirstOne(StringKit.underline2Camel(tableName)),
                    tableInfo.getTableComment());
        }else{
            System.out.println("数据表不存在,Controller生成失败！");
        }
        }

    public  void genController(String tableName,String entityName,String tableComment){
            FreemarkerUtil freemarkerUtil = new FreemarkerUtil();
            String path = PROJECT_PATH + "\\src\\main\\resources\\ftl\\" + DIR + "\\";
            System.out.println(path);
            HashMap<String, Object> data = new HashMap<>();
            data.put("package_name", PACHAGE_NAME);
            data.put("entity_package", ENTITYS_PACKAGE);
            data.put("service_package", SERVICE_PACKAGE);
            data.put("table_comment", tableName + "\r\n" + tableComment);
            data.put("entity_name", entityName);
            data.put("author", AUTHOR);
            data.put("date", DATE);
            String out = "\\src\\main\\java\\" +
                    PACHAGE_NAME.replace(".", File.separator) +
                    "\\controller\\" + entityName + "Controller.java";
            freemarkerUtil.fprint("controller.ftl", path, data,
                    PROJECT_PATH + out);
            System.out.println( "生成 "+out.replace(File.separator,".").substring(1,out.length()) +" 成功！" );

    }
}