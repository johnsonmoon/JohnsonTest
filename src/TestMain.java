import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2016/07/18.
 */
public class TestMain {
    public static void main(String args[]){
        //testFunc1();
        //testFunc2();
        //testFunc3();
        //testFunc4();
        //testFunc5();
        //testFunc6();
        //testFunc7("jdaiudh,afugh,faiuh,aoo,aewf,ax,vef,e,ewq");
        //testFunc8();
        //testFunc10(3, "12.556");
        //testSQLServerConnection();
        //testOracleConnection();
        //testToJsonString();
        //HTTPconnectionTest.testCourseVedioUploading();
        //HTTPconnectionTest.testDeleteMyCourse();
        //System.out.println(VedioUtils.cutoutVedio("D:\\ffmpeg\\bin\\ffmpeg", "C:\\Users\\Administrator\\Desktop\\test2.mp4", "C:\\Users\\Administrator\\Desktop\\testCut.jpg", 6));
        //System.out.println(VedioUtils.convertVedio("D:\\ffmpeg\\bin\\ffmpeg", "C:\\Users\\Administrator\\Desktop\\test3.mp4", "C:\\Users\\Administrator\\Desktop\\test3.flv"));
    }

    /**
     * 测试方法
     *
     * */
    public static void testToJsonString(){
        _id = 456l;
        Crs_ID = "fafioauhf";
        Vedio_ID = "afaf";
        CrsVedio_addTime = "2016-9-18";
        System.out.println(toJSONString());
    }

    public static void testSQLServerConnection(){
        String JDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";// SQL数据库引擎
        String connectDB = "jdbc:sqlserver://10.1.50.104:1433;DatabaseName=XuyhTest";// 数据源
        try {
            Class.forName(JDriver);// 加载数据库引擎，返回给定字符串名的类
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFound: " + e.getMessage());
            System.exit(0);
        }
        System.out.println("数据库驱动成功");
        try {
            String user = "sa";
            String password = "PassW0rd";
            Connection con = DriverManager.getConnection(connectDB, user, password);// 连接数据库对象
            System.out.println("连接数据库成功");
            Statement stmt = con.createStatement();// 创建SQL命令对象
            // 创建表
            System.out.println("开始创建表");
            String query = "create table TABLE1(ID NCHAR(2),NAME NCHAR(10))";// 创建表SQL语句
            stmt.executeUpdate(query);// 执行SQL命令对象
            System.out.println("表创建成功");
            // 输入数据
            System.out.println("开始插入数据");
            String a1 = "INSERT INTO TABLE1 VALUES('1','旭哥')";// 插入数据SQL语句
            String a2 = "INSERT INTO TABLE1 VALUES('2','伟哥')";
            String a3 = "INSERT INTO TABLE1 VALUES('3','张哥')";
            stmt.executeUpdate(a1);// 执行SQL命令对象
            stmt.executeUpdate(a2);
            stmt.executeUpdate(a3);
            System.out.println("插入数据成功");
            // 读取数据
            System.out.println("开始读取数据");
            ResultSet rs = stmt.executeQuery("SELECT * FROM TABLE1");// 返回SQL语句查询结果集(集合)
            // 循环输出每一条记录
            while (rs.next()) {
                // 输出每个字段
                System.out.println(rs.getString("ID") + "\t" + rs.getString("NAME"));
            }
            System.out.println("读取完毕");
            // 关闭连接
            stmt.close();// 关闭命令对象连接
            con.close();// 关闭数据库连接
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库连接错误");
            System.exit(0);
        }
    }

    public static void testOracleConnection(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.50.104:1521:orcl", "SYSTEM", "PassW0rd");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from dept");
            while(rs.next()) {
                System.out.println(rs.getString("deptno"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) {
                    rs.close();
                }
                if(stmt != null) {
                    stmt.close();
                }
                if(conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testFunc8(){
        String returnMessage;
        returnMessage = "{\"" + "progress" + "\":\"" + 100 + "\",\"" + "detailMessage" + "\":\""
                + "adhgayufga" + "\",";
        returnMessage += "\"" + "warnMessageSize" + "\":\"" + 15 + "\",";
        returnMessage += "\"" + "fileName" + "\":\"" + "dauag" + "\"";
        returnMessage += "}";
        System.out.println(returnMessage);
    }

    public static void testFunc7(String classCodes){
        if(classCodes == null || classCodes.equals("")){
            System.out.println("null");
        }
        List<String> classCodeList = new ArrayList<String>();
        String[] raw = classCodes.split(",");
        for (int i = 0; i < raw.length; i++) {
            classCodeList.add(raw[i]);
        }
        for (int j = 0; j < classCodeList.size(); j++){
            System.out.println(classCodeList.get(j));
        }
    }

    public static void testFunc6(){
        String returnMessage = "";
        returnMessage = "{\"" + "progress" + "\":\"" + 0 + "\",\"" + "detailMessage" + "\":\"" + "查找不到该 messageId 对应的message!" + "\",";
        returnMessage += "\"warnMessage\":[";
        returnMessage += ("\"" + "查找不到该 messageId 对应的message!" + "\"");
        returnMessage += "]}";
        System.out.println(returnMessage);
    }

    public static void testFunc5(){
        String Id = "daiugfti";
        String returnMessage = "";
        returnMessage += "{\"messageId\":\"";
        returnMessage += Id;
        returnMessage += "\"}";
        System.out.println(returnMessage);
    }

    public static void testFunc4(){
        int progress = 100;
        String progressMessage = "ok!";
        List<String> warnMessage = new ArrayList<String>();
        //warnMessage.add("aiodhyoi8fuh");
        //warnMessage.add("audfohgoiufgh");
        //warnMessage.add("aufgtyif");
        //warnMessage.add("adhulufh");
        //warnMessage.add("faughfviuyah");
        String returnMessage = "";
        returnMessage = "{\"" + "progress" + "\":\"" + progress + "\",\"" + "detailMessage" + "\":\"" + progressMessage + "\",";
        returnMessage += "\"warnMessage\":[";
        if(warnMessage.size() > 0){
            returnMessage += ("\"" + warnMessage.get(0) + "\"");
            for(int i = 1; i < warnMessage.size(); i++){
                returnMessage += ("," + "\"" + warnMessage.get(0) + "\"");
            }
        }
        returnMessage += "]}";
        System.out.println(returnMessage);
    }

    public static void testFunc3(){
        System.out.println(RandomStringFactory.getRandomString(20));
    }

    public static void testFunc2(){
        String value = "classCode14684674878&&id415684854856";
        String[] values = value.split("&&");
        System.out.println(values[0]);
        System.out.println(values[1]);
    }

    public static void testFunc1(){
        Map<String, String> testMap = new LinkedHashMap<String, String>();
        testMap.put("123", "312");
        testMap.put("hao", "a");
        testMap.put("shiya", "duide");
        Iterator it = testMap.entrySet().iterator();
        System.out.println(testMap.size());
        while(it.hasNext()){
            Map.Entry entity = (Map.Entry)it.next();
            System.out.println("Key: "+ entity.getKey().toString() + "  Value: " + entity.getValue().toString());
        }
    }

    public static void testFunc10(int precision, String attrValue){
        if (attrValue.contains(".")) {
            String value = attrValue.substring(attrValue.indexOf(".") + 1);
            System.out.println(attrValue);
            System.out.println(value);
            if (value.length() != precision) {
                System.out.println("小数位数不符合！");
            }
        } else {
            System.out.println("不是小数类型！");
        }

        System.out.println(attrValue);
    }

    public static final String BASE_TABLE_NAME = "CoursesVedio";
    public static final String BASE_COURSESVEDIO_PHYSICAL_ID = "_id";
    public static final String BASE_COURSESVEDIO_COURSES_ID = "Crs_ID";
    public static final String BASE_COURSESVEDIO_VEDIO_ID = "Vedio_ID";
    public static final String BASE_COURSESVEDIO_ADD_TIME = "CrsVedio_addTime";
    private static long _id;
    private static String Crs_ID;
    private static String Vedio_ID;
    private static String CrsVedio_addTime;

    public static String toJSONString() {
        String value = "{\"" + BASE_COURSESVEDIO_PHYSICAL_ID + "\":\"" + _id + "\", \"" + BASE_COURSESVEDIO_COURSES_ID
                + "\":\"" + Crs_ID + "\", \"" + BASE_COURSESVEDIO_VEDIO_ID + "\":\"" + Vedio_ID + "\", \""
                + BASE_COURSESVEDIO_ADD_TIME + "\":\"" + CrsVedio_addTime + "\"}";
        return value;
    }
}
