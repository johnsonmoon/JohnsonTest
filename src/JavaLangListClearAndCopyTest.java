import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xuyh at 2016/09/23 下午 04:23.
 */
public class JavaLangListClearAndCopyTest {
    public static void main(String aargs[]){
        testClearList();
    }

    /**
     * 测试java.lang.list clear之后,是否是list中的引用地址清空,引用对象继续存在在堆中
     */
    public static void testClearList(){
        // 设计一个列表集合
        List<Student> studentList = new ArrayList<Student>();
        for(int i = 0; i < 10; i++){
            studentList.add(new Student("John" + i, "1570008376" + i));
        }

        // 拷贝到另一个列表
        List<Student> studentsListNew = new ArrayList<Student>(studentList);

        // 这时候清空studentList,如果studentsListNew的size大于零,
        // 并且能够引用其中Student的对象,则说明java.lang.list集合clear操作只是
        // 把list中的引用地址给删除了,但是原先引用的对象还是存在的，
        // 在拷贝后的studentsListNew中依然可以引用到
        studentList.clear();
        System.out.println(studentList.size());
        System.out.println("\r\n\r\n" + studentsListNew.size());
        for(Student student : studentsListNew){
            System.out.println(student.getName() + " || " + student.getNumber());
        }
    }

    public static class Student{
        private String name;
        private String number;

        public Student() {
            name = "";
            number = "";
        }

        public Student(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
