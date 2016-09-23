import com.xuyihao.url.connectors.DownUtil;
import com.xuyihao.url.connectors.HttpUtil;
import com.xuyihao.url.enums.MIME_FileType;
import com.xuyihao.url.enums.Platform;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 *
 * Created by Xuyh at 2016/09/19 下午 03:07.
 */
public class HTTPconnectionTest {
    public static void main(String args[]){
        //testGetCacheCourses();
        //testGetCachePosts();
        //testGetCourses(216, 1);
        //testGetPosts(0, 1);
    }

    public static String BASE_URL_ACCOUNTS = "http://localhost:8089/rongyi/accounts";
    public static String BASE_URL_COURSES = "http://localhost:8089/rongyi/courses";
    public static String BASE_URL_POSTS = "http://localhost:8089/rongyi/posts";
    private static HttpUtil httpUtil = new HttpUtil(Platform.WINDOWS);
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void testGetCacheCourses(){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "getCachedCoursesList");
        output(httpUtil.executeGet(BASE_URL_COURSES, params));
    }

    public static void testGetCachePosts(){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "getCachedPostsList");
        output(httpUtil.executeGet(BASE_URL_POSTS, params));
    }

    public static void testGetCourses(int page, int size){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "getLatestCoursesList");
        params.put("page", String.valueOf(page));
        params.put("size", String.valueOf(size));
        output(httpUtil.executeGet(BASE_URL_COURSES, params));
    }

    public static void testGetPosts(int page, int size){
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "getLatestPostsList");
        params.put("page", String.valueOf(page));
        params.put("size", String.valueOf(size));
        output(httpUtil.executeGet(BASE_URL_POSTS, params));
    }

    public static void testDeleteMyCourse(){
        //获取session
        httpUtil.getSessionIDFromCookie(BASE_URL_ACCOUNTS);

        //注册账号
        System.out.println("Input your register name:  ");
        String Acc_name = "";
        try{
            Acc_name = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "register");
        params.put("Acc_name", Acc_name);
        params.put("Acc_pwd", "123456");
        params.put("Acc_sex", "男");
        params.put("Acc_loc", "ZJUT");
        System.out.println(httpUtil.executePostByUsual(BASE_URL_ACCOUNTS, params));

        //发表视频课程
        String uploadFile = "";
        System.out.println("Input the vedio directory name: ");
        try{
            uploadFile = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params2 = new HashMap<String, String>();
        params2.put("action", "addCrs");
        params2.put("Crs_name", "KOL");
        System.out.println(httpUtil.singleFileUploadWithParameters(BASE_URL_COURSES, uploadFile, MIME_FileType.Video_mp4, params2));

        //删除视频课程
        System.out.println("Input the Crs_ID that you want to delete: ");
        String Crs_ID = "";
        try{
            Crs_ID = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params3 = new HashMap<String, String>();
        params3.put("action", "deleteCrs");
        params3.put("Crs_ID", Crs_ID);
        System.out.println(httpUtil.executePostByUsual(BASE_URL_COURSES, params3));
    }

    public static void testCourseVedioUploading(){
        //获取session
        httpUtil.getSessionIDFromCookie(BASE_URL_ACCOUNTS);

        //注册账号
        System.out.println("Input your register name:  ");
        String Acc_name = "";
        try{
            Acc_name = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("action", "register");
        params.put("Acc_name", Acc_name);
        params.put("Acc_pwd", "123456");
        params.put("Acc_sex", "男");
        params.put("Acc_loc", "ZJUT");
        System.out.println(httpUtil.executePostByUsual(BASE_URL_ACCOUNTS, params));

        //发表视频课程
        String uploadFile = "";
        System.out.println("Input the vedio directory name: ");
        try{
            uploadFile = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params2 = new HashMap<String, String>();
        params2.put("action", "addCrs");
        params2.put("Crs_name", "KOL");
        System.out.println(httpUtil.singleFileUploadWithParameters(BASE_URL_COURSES, uploadFile, MIME_FileType.Video_mp4, params2));

        //修改视频
        String uploadFile2 = "";
        String Crs_ID2 = "";
        try{
            System.out.println("Input the changing vedio directory name: ");
            uploadFile2 = reader.readLine();
            System.out.println("Input Crs_ID: ");
            Crs_ID2 = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params4 = new HashMap<String, String>();
        params4.put("action", "changeCrsInfo");
        params4.put("Crs_ID", Crs_ID2);
        params4.put("Crs_name", "HHN");
        System.out.println(httpUtil.singleFileUploadWithParameters(BASE_URL_COURSES, uploadFile2, MIME_FileType.Video_mp4, params4));

        //获取course的信息
        System.out.println(httpUtil.executeGet(BASE_URL_COURSES + "?action=getCrsInfo&Crs_ID=" + Crs_ID2));

        //登出
        System.out.println(httpUtil.executeGet(BASE_URL_COURSES + "?action=logout"));

        //注册另外一个账号
        System.out.println("Input your register name:  ");
        String Acc_name2 = "";
        try{
            Acc_name2 = reader.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params7 = new HashMap<String, String>();
        params7.put("action", "register");
        params7.put("Acc_name", Acc_name2);
        params7.put("Acc_pwd", "123456");
        params7.put("Acc_sex", "男");
        params7.put("Acc_loc", "ZJUT");
        System.out.println(httpUtil.executePostByUsual(BASE_URL_ACCOUNTS, params7));

        //用另外一个账号分享视频教程
        String Crs_ID3 = "";
        try{
            System.out.println("Input Crs_ID to share:  ");
            Crs_ID3 = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params6 = new HashMap<String, String>();
        params6.put("action", "shareCrs");
        params6.put("Crs_ID", Crs_ID3);
        System.out.println(httpUtil.executePostByUsual(BASE_URL_COURSES, params6));

        //获取视频图片文件信息
        String Crs_ID4 = "";
        try{
            System.out.println("Input Crs_ID to get information:  ");
            Crs_ID4 = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(httpUtil.executeGet(BASE_URL_COURSES + "?action=getCrsFileInfo&Crs_ID=" + Crs_ID4));
        System.out.println(httpUtil.executeGet(BASE_URL_COURSES + "?action=getCrsVedioId&Crs_ID=" + Crs_ID4));
        String Vedio_ID = "";
        try{
            System.out.println("Input Vedio_ID to get information:  ");
            Vedio_ID = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        System.out.println(httpUtil.executeGet(BASE_URL_COURSES + "?action=getCrsPhotoId&Vedio_ID=" + Vedio_ID));


        //下载文件
        String downloadPath = "";
        try{
            System.out.println("Input download path to save file:  ");
            downloadPath = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        DownUtil downUtil = new DownUtil(httpUtil);
        String Vedio_ID2 = "";
        try{
            System.out.println("Input vedio id to save vedio:  ");
            Vedio_ID2 = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        downUtil.downloadByGet(downloadPath + "1.mp4", BASE_URL_COURSES+"?action=getVedioById&Vedio_ID="+Vedio_ID2);

        String Photo_ID = "";
        try{
            System.out.println("Input photo id to save photo:  ");
            Photo_ID = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        downUtil.downloadByGet(downloadPath + "2photo.jpg", BASE_URL_COURSES+"?action=getPhotoById&Photo_ID="+Photo_ID);
        downUtil.downloadByGet(downloadPath + "2photoThumbnail.jpg", BASE_URL_COURSES+"?action=getThumbnailPhotoById&Photo_ID="+Photo_ID);


        //测试删除视频课程
        System.out.println("Input the Crs_ID that you want to delete: ");
        String Crs_ID = "";
        try{
            Crs_ID = reader.readLine();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, String> params3 = new HashMap<String, String>();
        params3.put("action", "deleteCrs");
        params3.put("Crs_ID", Crs_ID);
        System.out.println(httpUtil.executePostByUsual(BASE_URL_COURSES, params3));
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
