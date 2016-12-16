package xuyihao.JohnsonTest.jmx.entity;

import javax.management.AttributeNotFoundException;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.openmbean.TabularDataSupport;

public class JmxObject {
	private MBeanServerConnection connection;
	private ObjectName objectName;

	public JmxObject(MBeanServerConnection connection, ObjectName objectName) {
		super();
		this.connection = connection;
		this.objectName = objectName;		
	}
	
	public Object get(String attr) {
		if (attr.equalsIgnoreCase("ObjectName"))
			return objectName;
		
		Object value;
		try {
			value = connection.getAttribute(objectName, attr);			
		} catch (Throwable e) {			
			if (e instanceof AttributeNotFoundException)
				return null;
			return "获取属性值失败!";
		}
		
		if (value instanceof ObjectName)
			value = value.toString();
		else if (value instanceof TabularDataSupport) 
			return new JmxValue((TabularDataSupport) value);
		return value;
	}

	public Object check(String attr) {
		Object value = get(attr);
		if (value == null)
			throw new UnsupportedOperationException("不存在JMX属性：" + attr);
		return value;
	}

	@Override
	public String toString() {
		return String.format("%s[%s]", getClass().getSimpleName(), objectName);
	}	
}
