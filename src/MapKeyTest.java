import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Xuyh at 2016/08/15 上午 10:32.
 * 
 * <pre>
 * 测试map的key（组合key）情况下应该如何实现
 * </pre>
 * 
 */
public class MapKeyTest {
	private static Map<Key, String> map = new HashMap<Key, String>();

	public void initMap() {
		Date today = DateUtils.parseDate(DateUtils.currentDate());
		map.put(new Key("xuyihao", DateUtils.formatDate(today)), "Yes!");
		map.put(new Key("dongjianming", DateUtils.formatDate(today)), "Yeah!");
		map.put(new Key("chenjiali", DateUtils.formatDate(today)), "OK!");
		map.put(new Key("fanxiaoxuan", DateUtils.formatDate(today)), "Yes!");
		map.put(new Key("xuyihao", DateUtils.formatDate(DateUtils.getDateDayByDiff(today, -1))), "kikiki!");
		map.put(new Key("xuyihao", DateUtils.formatDate(DateUtils.getDateDayByDiff(today, -2))), "wiki!");
		map.put(new Key("xuyihao", DateUtils.formatDate(DateUtils.getDateDayByDiff(today, 1))), "lokoj");
		map.put(new Key("xuyihao", DateUtils.formatDate(DateUtils.getDateDayByDiff(today, 2))), "lpaef!");
	}

	public void testIfKeyWorks() {
		/*
		 * for (Key key : map.keySet()){ System.out.println(key.get_name() + "||" +
		 * DateUtils.formatDate(key.get_date()) + "||" + map.get(key));
		 * System.out.println(); }
		 */
		System.out.println(map.get(new Key("xuyihao", DateUtils.currentDate())));

		Set<Key> Keys = map.keySet();
		Key key = new Key("xuyihao", DateUtils.currentDate());
		for (Key key1 : Keys) {
			if (key.equals(key1)) {
				System.out.println("yes");
			} else {
				System.out.println("no");
			}
		}
	}

	public class Key {
		private String _name;
		private String _date;

		public Key(String name, String date) {
			this._name = name;
			this._date = date;
		}

		public String get_name() {
			return _name;
		}

		public void set_name(String _name) {
			this._name = _name;
		}

		public String get_date() {
			return _date;
		}

		public void set_date(String _date) {
			this._date = _date;
		}

		@Override
		public int hashCode() {
			int ret = _name.hashCode() ^ _date.hashCode();
			return ret;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (!(obj instanceof Key)) {
				return false;
			}
			Key key = (Key) obj;
			return _date.equals(key.get_date()) && _name.equals(key.get_name());
		}
	}
}
