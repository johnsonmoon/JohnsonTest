
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试json格式文本的用法(基于JSON-lib)
 *
 * Created by Xuyh at 2016/09/23 上午 11:06.
 */
public class JsonTest {
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]){
        //testJsonArray();
        //testJsonList();
        testObjectInObject();
    }

    public static void testObjectInObject(){
        JSONObject object1 = new JSONObject();
        object1.put("name", "John");
        object1.put("number", "15700083767");
        JSONObject object2 = new JSONObject();
        object2.put("name", "Johnson");
        object2.put("number", "15700085649");
        JSONArray array = new JSONArray();
        array.add(object1);
        array.add(object2);
        JSONObject largeObject = new JSONObject();
        largeObject.put("list", array);
        output(array.toString());
        output(largeObject.toString());
    }

    public static void testJsonList(){
        JSONObject json = new JSONObject();
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0 ; i < 5; i++){
            studentList.add(new Student(String.valueOf(i), String.valueOf(i+"John"), String.valueOf("1570008376" + i)));
        }
        json.put("students", studentList);
        output(json.toString());
    }

    public static void testJsonArray(){
        JSONObject json = new JSONObject();
        Student[] students = new Student[5];
        for (int i = 0 ; i < 5; i++){
            students[i] = new Student(String.valueOf(i), String.valueOf(i+"John"), String.valueOf("1570008376" + i));
        }
        json.put("students", students);
        output(json.toString());
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

    public static class Student{
        private String number;
        private String name;
        private String telephone;

        public Student() {
        }

        public Student(String number, String name, String telephone) {
            this.number = number;
            this.name = name;
            this.telephone = telephone;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }
    }
}