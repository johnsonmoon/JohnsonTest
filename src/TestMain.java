import javax.xml.transform.Result;
import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * Created by Administrator on 2016/07/18.
 */
public class TestMain {
	public static void main(String args[]) {
		// testFunc1();
		// testFunc2();
		// testFunc3();
		// testFunc4();
		// testFunc5();
		// testFunc6();
		// testFunc7("jdaiudh,afugh,faiuh,aoo,aewf,ax,vef,e,ewq");
		// testFunc8();
		// testFunc10(3, "12.556");
		// testSQLServerConnection();
		// testOracleConnection();
		// testToJsonString();
		// HTTPconnectionTest.testCourseVedioUploading();
		// HTTPconnectionTest.testDeleteMyCourse();
		// System.out.println(VedioUtils.cutoutVedio("D:\\ffmpeg\\bin\\ffmpeg",
		// "C:\\Users\\Administrator\\Desktop\\test2.mp4",
		// "C:\\Users\\Administrator\\Desktop\\testCut.jpg", 6));
		// System.out.println(VedioUtils.convertVedio("D:\\ffmpeg\\bin\\ffmpeg",
		// "C:\\Users\\Administrator\\Desktop\\test3.mp4",
		// "C:\\Users\\Administrator\\Desktop\\test3.flv"));
		// testSplitString();
		// testSplitToWord();
		// testOct_13_10_51();
		// testStringFormatter();
		testConvertCookieMap();
	}

	/**
	 * 测试方法
	 *
	 */
	public static void testStringFormatter() {
		String formatter = "你好吗，我想说：【%s】，今天天气是：【%s】。";
		String message = String.format(formatter, "新年好", "雨");
		output(message);
	}

	public static void testConvertCookieMap() {
		HashMap<String, String> map = new HashMap<>();
		map.put("JSESSIONID", "aduahgfuioafhguaiohf");
		map.put("ALM", "daawiugdh");
		map.put("DAI", "FAguhfa");
		output(convertCookieMapToString(map));
	}

	private static String convertCookieMapToString(HashMap<String, String> map) {
		String cookie = "";
		for (String key : map.keySet()) {
			cookie += (key + "=" + map.get(key) + ";");
		}
		cookie = cookie.substring(0, cookie.length() - 1);
		return cookie;
	}

	public static void testOct_13_10_51() {
		String test = "<connector xmlns=\"http://www.bea.com/connector/monitoring1dot0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n"
				+ "  <module-name>jms-internal-xa-adp</module-name>\n" + "  <native-libdir xsi:nil=\"true\"/>\n"
				+ "  <version>1.5</version>\n" + "  <license>\n" + "    <license-required>false</license-required>\n"
				+ "  </license>\n" + "  <vendor-name>Oracle Corporation</vendor-name>\n"
				+ "  <eis-type>JMS Service</eis-type>\n" + "  <metadata-complete>false</metadata-complete>\n"
				+ "  <resourceadapter-version>1.0.2</resourceadapter-version>\n"
				+ "  <enable-global-access-to-classes>false</enable-global-access-to-classes>\n"
				+ "  <enable-access-outside-app>true</enable-access-outside-app>\n" + "  <properties/>\n"
				+ "  <connector-work-manager>\n"
				+ "    <max-concurrent-long-running-requests>10</max-concurrent-long-running-requests>\n"
				+ "  </connector-work-manager>\n" + "  <outbound>\n" + "    <outbound-group>\n"
				+ "      <managedconnectionfactory-class>weblogic.jms.adapter.JMSManagedConnectionFactory</managedconnectionfactory-class>\n"
				+ "      <connection-factory-interface>weblogic.jms.bridge.AdapterConnectionFactory</connection-factory-interface>\n"
				+ "      <connectionfactory-impl-class>weblogic.jms.adapter.JMSBaseConnectionFactory</connectionfactory-impl-class>\n"
				+ "      <connection-interface>weblogic.jms.bridge.AdapterConnection</connection-interface>\n"
				+ "      <connection-impl-class>weblogic.jms.adapter.JMSBaseConnection</connection-impl-class>\n"
				+ "      <connection-instance>\n"
				+ "        <jndi-name>eis/jms/internal/WLSConnectionFactoryJNDIXA</jndi-name>\n"
				+ "        <transaction-support>XATransaction</transaction-support>\n" + "        <pool-params>\n"
				+ "          <initial-capacity>0</initial-capacity>\n" + "          <max-capacity>100</max-capacity>\n"
				+ "          <capacity-increment>1</capacity-increment>\n"
				+ "          <shrinking-enabled>true</shrinking-enabled>\n"
				+ "          <shrink-frequency-seconds>900</shrink-frequency-seconds>\n"
				+ "          <highest-num-waiters>0</highest-num-waiters>\n"
				+ "          <highest-num-unavailable>0</highest-num-unavailable>\n"
				+ "          <connection-creation-retry-frequency-seconds>0</connection-creation-retry-frequency-seconds>\n"
				+ "          <connection-reserve-timeout-seconds>-1</connection-reserve-timeout-seconds>\n"
				+ "          <test-frequency-seconds>0</test-frequency-seconds>\n"
				+ "          <test-connections-on-create>false</test-connections-on-create>\n"
				+ "          <test-connections-on-release>false</test-connections-on-release>\n"
				+ "          <test-connections-on-reserve>false</test-connections-on-reserve>\n"
				+ "          <profile-harvest-frequency-seconds>300</profile-harvest-frequency-seconds>\n"
				+ "          <ignore-in-use-connections-enabled>true</ignore-in-use-connections-enabled>\n"
				+ "          <match-connections-supported>true</match-connections-supported>\n"
				+ "          <use-first-available>false</use-first-available>\n" + "        </pool-params>\n"
				+ "        <logging>\n" + "          <log-filename xsi:nil=\"true\"/>\n"
				+ "          <logging-enabled>true</logging-enabled>\n" + "          <rotation-type>bySize</rotation-type>\n"
				+ "          <number-of-files-limited>false</number-of-files-limited>\n"
				+ "          <file-count>7</file-count>\n" + "          <file-size-limit>500</file-size-limit>\n"
				+ "          <rotate-log-on-startup>true</rotate-log-on-startup>\n"
				+ "          <log-file-rotation-dir xsi:nil=\"true\"/>\n" + "          <rotation-time>00:00</rotation-time>\n"
				+ "          <file-time-span>24</file-time-span>\n"
				+ "          <date-format-pattern>yyyy-M-d, h:mm:ss,S a z</date-format-pattern>\n" + "        </logging>\n"
				+ "        <authentication-mechanism>\n"
				+ "          <authentication-mechanism-type>BasicPassword</authentication-mechanism-type>\n"
				+ "          <credential-interface>javax.resource.spi.security.PasswordCredential</credential-interface>\n"
				+ "        </authentication-mechanism>\n"
				+ "        <reauthentication-support>false</reauthentication-support>\n" + "        <properties>\n"
				+ "          <property>\n" + "            <name>ConnectionURL</name>\n"
				+ "            <type>java.lang.String</type>\n" + "            <value/>\n"
				+ "            <ignore>false</ignore>\n"
				+ "            <supports-dynamic-updates>false</supports-dynamic-updates>\n"
				+ "            <confidential>false</confidential>\n" + "          </property>\n" + "          <property>\n"
				+ "            <name>DestinationType</name>\n" + "            <type>java.lang.String</type>\n"
				+ "            <value>Queue</value>\n" + "            <ignore>false</ignore>\n"
				+ "            <supports-dynamic-updates>false</supports-dynamic-updates>\n"
				+ "            <confidential>false</confidential>\n" + "          </property>\n" + "          <property>\n"
				+ "            <name>AdapterType</name>\n" + "            <type>java.lang.String</type>\n"
				+ "            <value>XATransaction</value>\n" + "            <ignore>false</ignore>\n"
				+ "            <supports-dynamic-updates>false</supports-dynamic-updates>\n"
				+ "            <confidential>false</confidential>\n" + "          </property>\n" + "          <property>\n"
				+ "            <name>InitialContextFactory</name>\n" + "            <type>java.lang.String</type>\n"
				+ "            <value>weblogic.jndi.WLInitialContextFactory</value>\n" + "            <ignore>false</ignore>\n"
				+ "            <supports-dynamic-updates>false</supports-dynamic-updates>\n"
				+ "            <confidential>false</confidential>\n" + "          </property>\n" + "          <property>\n"
				+ "            <name>DestinationJNDI</name>\n" + "            <type>java.lang.String</type>\n"
				+ "            <value/>\n" + "            <ignore>false</ignore>\n"
				+ "            <supports-dynamic-updates>false</supports-dynamic-updates>\n"
				+ "            <confidential>false</confidential>\n" + "          </property>\n" + "          <property>\n"
				+ "            <name>ConnectionFactoryJNDI</name>\n" + "            <type>java.lang.String</type>\n"
				+ "            <value/>\n" + "            <ignore>false</ignore>\n"
				+ "            <supports-dynamic-updates>false</supports-dynamic-updates>\n"
				+ "            <confidential>false</confidential>\n" + "          </property>\n" + "        </properties>\n"
				+ "        <res-auth xsi:nil=\"true\"/>\n" + "      </connection-instance>\n" + "    </outbound-group>\n"
				+ "  </outbound>\n" + "  <admin-objects/>\n" + "</connector>";
		output(test.subSequence(test.indexOf("<initial-capacity>") + "<initial-capacity>".length(),
				test.indexOf("</initial-capacity>")).toString());
		output(test.subSequence(test.indexOf("<max-capacity>") + "<max-capacity>".length(), test.indexOf("</max-capacity>"))
				.toString());
		output(test.subSequence(test.indexOf("<capacity-increment>") + "<capacity-increment>".length(),
				test.indexOf("</capacity-increment>")).toString());
	}

	public static void testSplitToWord() {
		String[] a = splitWord("WebLogic Server 12.2.1.1.0 Thu Jun 2 16:21:58 PDT 2016 1784838");
		for (String i : a) {
			output(i);
		}
	}

	public static void testSplitString() {
		String test = "C:\\Oracle\\Middleware\\Oracle_Home\\user_projects\\domains\\base_domain\\servers\\AdminServer\\data\\store\\diagnostics";
		String newOne = test.substring(0, test.indexOf("user_projects") - 1);
		output(newOne);
	}

	public static void testToJsonString() {
		_id = 456l;
		Crs_ID = "fafioauhf";
		Vedio_ID = "afaf";
		CrsVedio_addTime = "2016-9-18";
		System.out.println(toJSONString());
	}

	public static void testSQLServerConnection() {
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

	public static void testOracleConnection() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@10.1.50.104:1521:orcl", "SYSTEM", "PassW0rd");
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from dept");
			while (rs.next()) {
				System.out.println(rs.getString("deptno"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void testFunc8() {
		String returnMessage;
		returnMessage = "{\"" + "progress" + "\":\"" + 100 + "\",\"" + "detailMessage" + "\":\"" + "adhgayufga" + "\",";
		returnMessage += "\"" + "warnMessageSize" + "\":\"" + 15 + "\",";
		returnMessage += "\"" + "fileName" + "\":\"" + "dauag" + "\"";
		returnMessage += "}";
		System.out.println(returnMessage);
	}

	public static void testFunc7(String classCodes) {
		if (classCodes == null || classCodes.equals("")) {
			System.out.println("null");
		}
		List<String> classCodeList = new ArrayList<String>();
		String[] raw = classCodes.split(",");
		for (int i = 0; i < raw.length; i++) {
			classCodeList.add(raw[i]);
		}
		for (int j = 0; j < classCodeList.size(); j++) {
			System.out.println(classCodeList.get(j));
		}
	}

	public static void testFunc6() {
		String returnMessage = "";
		returnMessage = "{\"" + "progress" + "\":\"" + 0 + "\",\"" + "detailMessage" + "\":\""
				+ "查找不到该 messageId 对应的message!" + "\",";
		returnMessage += "\"warnMessage\":[";
		returnMessage += ("\"" + "查找不到该 messageId 对应的message!" + "\"");
		returnMessage += "]}";
		System.out.println(returnMessage);
	}

	public static void testFunc5() {
		String Id = "daiugfti";
		String returnMessage = "";
		returnMessage += "{\"messageId\":\"";
		returnMessage += Id;
		returnMessage += "\"}";
		System.out.println(returnMessage);
	}

	public static void testFunc4() {
		int progress = 100;
		String progressMessage = "ok!";
		List<String> warnMessage = new ArrayList<String>();
		// warnMessage.add("aiodhyoi8fuh");
		// warnMessage.add("audfohgoiufgh");
		// warnMessage.add("aufgtyif");
		// warnMessage.add("adhulufh");
		// warnMessage.add("faughfviuyah");
		String returnMessage = "";
		returnMessage = "{\"" + "progress" + "\":\"" + progress + "\",\"" + "detailMessage" + "\":\"" + progressMessage
				+ "\",";
		returnMessage += "\"warnMessage\":[";
		if (warnMessage.size() > 0) {
			returnMessage += ("\"" + warnMessage.get(0) + "\"");
			for (int i = 1; i < warnMessage.size(); i++) {
				returnMessage += ("," + "\"" + warnMessage.get(0) + "\"");
			}
		}
		returnMessage += "]}";
		System.out.println(returnMessage);
	}

	public static void testFunc3() {
		System.out.println(RandomStringFactory.getRandomString(20));
	}

	public static void testFunc2() {
		String value = "classCode14684674878&&id415684854856";
		String[] values = value.split("&&");
		System.out.println(values[0]);
		System.out.println(values[1]);
	}

	public static void testFunc1() {
		Map<String, String> testMap = new LinkedHashMap<String, String>();
		testMap.put("123", "312");
		testMap.put("hao", "a");
		testMap.put("shiya", "duide");
		Iterator it = testMap.entrySet().iterator();
		System.out.println(testMap.size());
		while (it.hasNext()) {
			Map.Entry entity = (Map.Entry) it.next();
			System.out.println("Key: " + entity.getKey().toString() + "  Value: " + entity.getValue().toString());
		}
	}

	public static void testFunc10(int precision, String attrValue) {
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

	public static void output(String message) {
		System.out.println(message);
	}

	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	public static String input() {
		String value = "";
		try {
			value = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 根据空白字符，将文本切割为单词输出
	 * 
	 * @param text
	 * @return
	 */
	public static String[] splitWord(String text) {
		return text.split("\\s+");
	}
}
