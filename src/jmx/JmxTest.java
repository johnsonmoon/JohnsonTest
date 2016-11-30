package jmx;

import jmx.entity.JmxObject;

import javax.management.*;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.MemoryUsage;
import java.util.*;

/**
 * jmx 连接监控测试类
 *
 * Created by Xuyh at 2016/09/22 上午 10:05.
 */
public class JmxTest {
    private static String ipAndPort = "10.1.50.104:1099";
    private static String userName = "admin";
    private static String userPassword = "admin";
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws Exception{
        //testGettingActiveMQInfo();
        testGettingConnectionSystemInformation();
        //testQueryInformation();
        //testJMXSessionTool();
        //testGettingObjectNames("10.1.50.104", "1099", "admin", "admin");
        //testGettingObjectNamesWithClassNames("10.1.50.104", "1099", "admin", "admin");
        //testGettingObjectNamesByClassName("10.1.50.104", "1099", "admin", "admin", "weblogic.connector.monitoring.ConnectorComponentRuntimeMBeanImpl");
        //testGettingValueByObjectName("10.1.50.104", "1099", "admin", "admin", "com.bea:ServerRuntime=AdminServer,Name=jms-internal-notran-adp,ApplicationRuntime=jms-internal-notran-adp,Type=ConnectorComponentRuntime");
    }

    public static void testGettingValueByObjectName(String ip, String port, String userName, String userPassword, String objectName)throws Exception{
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();

        Object object = connection.getAttribute(ObjectName.getInstance(objectName), "Configuration");

        //testGettingValue(connection, objectName);
    }

    public static void testGettingValue(MBeanServerConnection connection, String objectName) throws Exception{
        output("\r\nobjectName = " + objectName);
        MBeanInfo info = connection.getMBeanInfo(new ObjectName(objectName));
        output("ClassName  = " + info.getClassName());
        for (MBeanAttributeInfo attribute : info.getAttributes()) {
            try {
                String attrValue = connection.getAttribute(new ObjectName(objectName), attribute.getName()).toString();
                output(/*"Attribute：" + */ "         " + attribute.getName() + " = " + attrValue);
            }catch (Exception e){
                output(/*"Attribute：" + */ "         " + attribute.getName() + " = 无");
            }
        }
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
                output("\t\t" + attr.getName());
            }
        }
    }

    public static void testGettingDomains(String ip, String port, String userName, String userPassword)throws Exception{
        ipAndPort = ip + ":" + port;
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        String domains[] = connection.getDomains();
        output("\r\nDomains:-------------------------------------------------------------\r\n");
        for(int i = 0; i < domains.length; i++){
            output("domain[" + i + "] = " + domains[i]);
        }
    }

    public static void testGettingObjectNamesWithClassNames(String ip, String port, String userName, String userPassword) throws Exception{
        ipAndPort = ip + ":" + port;
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        List<String> objectNameList = new ArrayList<String>();
        List<String> classNameList = new ArrayList<String>();
        Set<ObjectInstance> objectInstanceSet = connection.queryMBeans(null, null);
        for(ObjectInstance objectInstance : objectInstanceSet){
            objectNameList.add(objectInstance.getObjectName().toString());
            classNameList.add(objectInstance.getClassName());
        }
        for(int i = 0; i < objectNameList.size(); i++){
            //output(objectNameList.get(i) + "=========" + classNameList.get(i));
            if(classNameList.get(i).equals("weblogic.t3.srvr.ServerRuntime")){
                testGettingValue(connection, objectNameList.get(i));
            }
        }
    }

    public static void testGettingObjectNames(String ip, String port, String userName, String userPassword)throws Exception{
        ipAndPort = ip + ":" + port;
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        List<String> objectNameList = new ArrayList<String>();
            Set<ObjectInstance> objectInstanceSet = connection.queryMBeans(null, null);
        for(ObjectInstance objectInstance : objectInstanceSet){
            objectNameList.add(objectInstance.getObjectName().toString());
        }
        for(String name : objectNameList){
            output(name);
        }
    }

    public static void testGettingObjectNamesByClassName(String ip, String port, String userName, String userPassword, String className)throws Exception{
        ipAndPort = ip + ":" + port;
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        List<String> objectNameList = new ArrayList<String>();
        Set<ObjectInstance> objectInstanceSet = connection.queryMBeans(null, null);
        for(ObjectInstance objectInstance : objectInstanceSet){
            if(objectInstance.getClassName().equals(className)) {
                objectNameList.add(objectInstance.getObjectName().toString());
            }
        }
        for(String name : objectNameList){
            output(name);
        }
    }

    public static void testGettingClassNames(String ip, String port, String userName, String userPassword)throws Exception{
        ipAndPort = ip + ":" + port;
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        List<String> classNameList = new ArrayList<String>();
        Set<ObjectInstance> objectInstanceSet = connection.queryMBeans(null, null);
        for(ObjectInstance objectInstance : objectInstanceSet){
            classNameList.add(objectInstance.getClassName());
        }
        for(String name : classNameList){
            output(name);
        }
    }

    public static void testGettingConnectionSystemInformation() throws Exception{
        output("输入ip及端口: ");
        ipAndPort = "10.1.50.104:1099";/*input();*/
        output("输入用户名-->回车 + 密码-->回车");
        userName = "admin";/*input();*/
        userPassword = "admin";/*input();*/
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

        output("\r\nAll ClassName------------------------------------------------------\r\n");
        List<ObjectName> objectNameList = new ArrayList<ObjectName>();
        Set<ObjectInstance> set = connection.queryMBeans(null, null);
        for (ObjectInstance objectInstance : set) {
            String className = objectInstance.getClassName();
            objectNameList.add(objectInstance.getObjectName());
            output("\t" + className);
        }

        output("\r\n ----------------- Objects informations ---------------\r\n");
        for(ObjectName objectName : objectNameList) {
            if(!objectName.toString().contains("com.bea")){
                continue;
            }
            output("\r\nobjectName = " + objectName.toString());
            MBeanInfo info = connection.getMBeanInfo(objectName);
            output("ClassName  = " + info.getClassName());
            if (info.getAttributes().length > 0) {
                for (MBeanAttributeInfo attribute : info.getAttributes()) {
                    try {
                        String attrValue = connection.getAttribute(objectName, attribute.getName()).toString();
                        output(/*"Attribute：" + */ "         " + attribute.getName() + " ==> " + attrValue);
                    }catch (Exception e){
                        output(/*"Attribute：" + */ "         " + attribute.getName() + " ==> 无");
                    }
                }
            }
            if (info.getOperations().length > 0) {
                for (MBeanOperationInfo m : info.getOperations()) {
                    m.getDescription();
                    m.getName();
                    m.getReturnType();
                    output("Operation：" + m.getName());
                }
            }
        }
        connector.close();
    }

    public static void testJMXSessionTool(){
        JmxSession session = new JmxSession();
        session.connect("10.1.50.104", "1099", "admin", "admin");
        Object[] objects = session.query("com.bea:ServerRuntime=AdminServer,Name=DataSourceLog,Type=WLDFDataAccessRuntime,WLDFAccessRuntime=Accessor,WLDFRuntime=WLDFRuntime");
        JmxObject object = (JmxObject)objects[0];
        Object value = object.get("DataArchiveParameters");
        Map map = (Map)value;
        output(map.get("storeDir").toString());
    }

    public static void testQueryInformation() throws Exception{
        ipAndPort = "10.1.50.104:1099";
        userName = "admin";
        userPassword = "admin";
        String rawUrl = "service:jmx:rmi:///jndi/rmi://" + ipAndPort + "/jmxrmi";
        JMXServiceURL url = new JMXServiceURL(rawUrl);
        JMXConnector connector;
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put(JMXConnector.CREDENTIALS, new String[]{userName, userPassword});
        connector = JMXConnectorFactory.connect(url, environment);
        MBeanServerConnection connection = connector.getMBeanServerConnection();
        ObjectName name = ObjectName.getInstance("java.lang:type=Memory");
        MBeanInfo info = connection.getMBeanInfo(name);
        MBeanAttributeInfo[] attributeInfoArray = info.getAttributes();
        for(MBeanAttributeInfo attributeInfo : attributeInfoArray){
                output(connection.getAttribute(name, attributeInfo.getName()).toString());
        }
        connector.close();
    }

    public static Map<String,Object> getHeapProperty(MBeanServerConnection connection) {

        Map<String,Object> heapProperties = new HashMap<String, Object>();

        try {
            ObjectName heapObjName = new ObjectName("java.lang:type=Memory");
            MemoryUsage heapMemoryUsage = MemoryUsage.from((CompositeDataSupport) connection.getAttribute(heapObjName, "HeapMemoryUsage"));
            long maxMemory = heapMemoryUsage.getMax();// 堆最大
            long commitMemory = heapMemoryUsage.getCommitted();// 堆当前分配
            long usedMemory = heapMemoryUsage.getUsed();

            heapProperties.put("maxMemory",maxMemory);
            heapProperties.put("commitMemory",commitMemory);
            heapProperties.put("usedMemory",usedMemory);
            heapProperties.put("usedPct", (double) usedMemory * 100 / commitMemory + "%");// 堆使用率

        } catch (Throwable e) {
            if (e instanceof InstanceNotFoundException)
                return null;
        }
        return heapProperties;
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
