
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * jmx 连接监控测试类
 *
 * Created by Xuyh at 2016/09/22 上午 10:05.
 */
public class JmxTest {
    private static String ipAndPort = "127.0.0.1:8099";
    private static String userName = "admin";
    private static String userPassword = "activemq";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws Exception{
        testGettingActiveMQInfo();
        //testGettingConnectionSystemInformation();
    }

    public static void testGettingActiveMQInfo() throws Exception{
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        JMXConnector connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        List<ObjectName> mBeanNameList = new ArrayList<ObjectName>();
        Set<ObjectInstance> set = connection.queryMBeans(null, null);
        for (Iterator<ObjectInstance> it = set.iterator(); it.hasNext();) {
            ObjectInstance objectInstance = (ObjectInstance) it.next();
            String name = objectInstance.getObjectName().toString();
            if(name.contains("org.apache.activemq")){
                mBeanNameList.add(objectInstance.getObjectName());
            }
        }

        //获取所有相关mBean的属性名:值
        for(ObjectName name : mBeanNameList) {
            MBeanInfo mBeanInfo = connection.getMBeanInfo(name);
            output("\r\n\r\nMBean名字 = " + mBeanInfo.getClassName());
            output("MBeanName = " + name);
            output("MBean属性:");
            for(MBeanAttributeInfo attr : mBeanInfo.getAttributes()) {
                output("\t\t" + attr.getName() + " : " + connection.getAttribute(name, attr.getName()));
            }
        }
    }

    public static void testGettingConnectionSystemInformation() throws Exception{
        output("输入ip及端口: ");
        ipAndPort = input();
        output("输入用户名-->回车 + 密码-->回车");
        userName = input();
        userPassword = input();
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        if(userName.equals("")) {
            connector = JMXConnectorFactory.connect(url, null);
        }else{
            Map<String, Object> environment = new HashMap<String, Object>();
            environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
            connector = JMXConnectorFactory.connect(url, environment);
        }
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        String domains[] = connection.getDomains();
        output("\r\nDomains:-------------------------------------------------------------\r\n");
        for(int i = 0; i < domains.length; i++){
            output("domain[" + i + "] = " + domains[i]);
        }
        output("\r\nAll ObjectNames------------------------------------------------------\r\n");
        Set<ObjectInstance> set = connection.queryMBeans(null, null);
        for (Iterator<ObjectInstance> it = set.iterator(); it.hasNext();) {
            ObjectInstance oi = (ObjectInstance) it.next();
            output("\t" + oi.getObjectName());
        }
        output("\r\norg.apache.activemq:BrokerName=localhost,Type=Broker：---------------\r\n");
        ObjectName mbeanName = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost");
        MBeanInfo info = connection.getMBeanInfo(mbeanName);
        output("Class: " + info.getClassName());
        if (info.getAttributes().length > 0) {
            for (MBeanAttributeInfo m : info.getAttributes()){
                output("Attriber：" + m.getName());
            }
        }
        if (info.getOperations().length > 0){
            for(MBeanOperationInfo m : info.getOperations()) {
                output("Operation：" + m.getName());
            }
        }
        connector.close();
    }

    public static void output(String message){
        System.out.println(message);
    }

    public static String input(){
        String value = "";
        try{
            value = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        return value;
    }
}
