package xuyihao.JohnsonTest.jmx;

import xuyihao.JohnsonTest.jmx.entity.JmxObject;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.CompositeDataSupport;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.lang.management.MemoryUsage;
import java.net.MalformedURLException;
import java.util.*;

public class JmxSession {
	private JMXConnector connector;
	private MBeanServerConnection connection;
	private String serviceUrlString;

	public Object invoke(String mbeanName, String method, Object[] params, String[] signs) throws IOException {
		try {
			MBeanServerConnection connection = connector.getMBeanServerConnection();
			return connection.invoke(new ObjectName(mbeanName), method, params, signs);
		} catch (Throwable e) {
			return null;
		}
	}

	public JmxObject check(String objectName) {
		JmxObject obj = get(objectName);
		if (obj == null)
			throw new UnsupportedOperationException("JMX对象不存在：" + objectName);
		return obj;
	}

	public Object[] query(String objectName) {
		try {
			ObjectName name = new ObjectName(objectName);
			Set<?> names = connection.queryNames(name, null);
			List<Object> result = new ArrayList<Object>(names.size());
			for (Object item : names) {
				JmxObject ret = get(item.toString());
				if (ret != null)
					result.add(ret);
			}
			return result.toArray();
		} catch (Throwable e) {
			return null;
		}
	}

	public JmxObject get(String objectName) {
		try {
			ObjectName name = new ObjectName(objectName);
			connection.getObjectInstance(name);
			return new JmxObject(connection, name);
		} catch (Throwable e) {
			if (e instanceof InstanceNotFoundException)
				return null;
			return null;
		}
	}

	public Map<String, Object> getHeapProperty() {
		Map<String, Object> heapProperties = new HashMap<String, Object>();
		try {
			ObjectName heapObjName = new ObjectName("java.lang:type=Memory");
			MemoryUsage heapMemoryUsage = MemoryUsage
					.from((CompositeDataSupport) connection.getAttribute(heapObjName, "HeapMemoryUsage"));
			long maxMemory = heapMemoryUsage.getMax();// 堆最大
			long commitMemory = heapMemoryUsage.getCommitted();// 堆当前分配
			long usedMemory = heapMemoryUsage.getUsed();

			heapProperties.put("maxMemory", maxMemory);
			heapProperties.put("commitMemory", commitMemory);
			heapProperties.put("usedMemory", usedMemory);
			heapProperties.put("usedPct", (double) usedMemory * 100 / commitMemory + "%");// 堆使用率

		} catch (Throwable e) {
			if (e instanceof InstanceNotFoundException)
				return null;
			return null;
		}
		return heapProperties;
	}

	protected void connect(String ip, String port, String userName, String userPassword) {
		JMXServiceURL serviceURL;
		try {
			if (serviceUrlString == null)
				serviceURL = new JMXServiceURL(String.format("service:jmx:rmi:///jndi/rmi://%s:%s/jmxrmi", ip, port));
			else
				serviceURL = new JMXServiceURL(serviceUrlString);
		} catch (MalformedURLException e) {
			serviceURL = null;
			e.printStackTrace();
		}
		try {
			if (userName == null || userPassword.isEmpty()) {
				connector = JMXConnectorFactory.connect(serviceURL);
			} else {
				Map<String, Object> environment = new HashMap<String, Object>();
				environment.put(JMXConnector.CREDENTIALS, new String[] { userName, userPassword });
				connector = JMXConnectorFactory.connect(serviceURL, environment);
			}
			connection = connector.getMBeanServerConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void disconnect() {
		try {
			connector.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
