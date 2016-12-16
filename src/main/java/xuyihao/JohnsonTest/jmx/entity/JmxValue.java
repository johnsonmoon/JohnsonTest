package xuyihao.JohnsonTest.jmx.entity;

import javax.management.openmbean.CompositeDataSupport;
import javax.management.openmbean.TabularDataSupport;

public class JmxValue {
	private TabularDataSupport value;

	public JmxValue(TabularDataSupport value) {
		this.value = value;
	}

	public Object get(String key) {
		Object result = value.get(new String[] { key });
		if (result instanceof CompositeDataSupport)
			return ((CompositeDataSupport) result).get("value");
		return result;
	}
}
