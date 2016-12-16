/**
 * Created by Administrator on 2016/07/18.
 */
public class CharacterArrayUtil {
	/**
	 * @author Xuyh created at 20167/18 12:50
	 * @description 字符数组指定位置插入字符
	 * @param rawArray 需要传入插入字符的数组
	 * @param insertChar 需要插入的字符
	 * @param index 需要插入的位置(插入到index字符之前)
	 */
	public static char[] insertCharacter(char[] rawArray, char insertChar, int index) {
		char[] afterArray = new char[rawArray.length + 1];
		for (int i = 0; i < index; i++) {
			afterArray[i] = rawArray[i];
		}
		afterArray[index] = insertChar;
		for (int j = index; j < rawArray.length; j++) {
			afterArray[j + 1] = rawArray[j];
		}
		return afterArray;
	}
}
