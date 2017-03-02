package xuyihao.JohnsonTest.arbiter;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRestServiceRepack {

    public static ObjectMapper mapper = new ObjectMapper();

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static void main(String[] args){
        TestRestServiceRepack test = new TestRestServiceRepack();
//		test.testAcceptMessage();
//		test.testCommitEvent();
//		test.testGetAlert();
//		test.testQueryAlert();
//		test.testProgressing();
//		test.testResolve();
        test.testSaveTags();
        test.testGetTags();
        test.testGetAllTags();
    }

    public void testGetAlert(){
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/arbiter/alerts?id=220690");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");
            Map<String, Object> m = new HashMap<String, Object>();

            ObjectMapper mapper = new ObjectMapper();
//			String param = mapper.writeValueAsString(m);
//			System.out.println("request param:" + param);

            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
//			os.write(param.getBytes());
            os.flush();

            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while((result = reader.readLine())!=null){
                System.out.println(result);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testQueryAlert(){
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/arbiter/alerts/query");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            Map<String, Object> m = new HashMap<String, Object>();
            m.put("type", "HostCPUAlert");
            m.put("pageSize", 20);
            m.put("pageNum", 0);
            m.put("severity", 30);

            ObjectMapper mapper = new ObjectMapper();
            String param = mapper.writeValueAsString(m);
            System.out.println("request param:" + param);

            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes());
            os.flush();

            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while((result = reader.readLine())!=null){
                System.out.println(result);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testResolve(){
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/arbiter/alerts/resolve");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            Map<String, Object> m = new HashMap<String, Object>();
            m.put("alertId", 225675);
            m.put("resolveCode", 200);
            m.put("message", "告警已被手动消除");
            m.put("userId", "admin");
            m.put("userName", "顶级管理员");

            ObjectMapper mapper = new ObjectMapper();
            String param = mapper.writeValueAsString(m);
            System.out.println("request param:" + param);

            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes());
            os.flush();

            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while((result = reader.readLine())!=null){
                System.out.println(result);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void testProgressing(){
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/arbiter/alerts/progressing");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            Map<String, Object> m = new HashMap<String, Object>();
            m.put("alertId", 225676);
            m.put("userId", "admin");
            m.put("userName", "顶级管理员");

            ObjectMapper mapper = new ObjectMapper();
            String param = mapper.writeValueAsString(m);
            System.out.println("request param:" + param);

            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes());
            os.flush();

            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while((result = reader.readLine())!=null){
                System.out.println(result);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


/*	public void testAcceptMessage(){
		try {
			URL url = new URL("http://localhost:8890/arbiter/api/v2/arbiter/messages/create");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			JSONArray list = new JSONArray();
			JSONObject m = new JSONObject();
			m.put("senderAddr", "192.168.1.3");
			m.put("senderName", "pc-abc");
			JSONArray attrs = new JSONArray();
			JSONObject a1 = new JSONObject();
			a1.put("name","1.3.6.1.6.3.1.1.4.1.0");
			a1.put("value","1.3.6.1.4.1.22014.1.3.3.1.1.1.19");
			a1.put("type","");
			attrs.put(a1);
			JSONObject a2 = new JSONObject();
			a2.put("name",".1.3.6.1.4.1.22014.1.3.3.1.2.8.0");
			a2.put("value","3");
			a2.put("type","INT");
			attrs.put(a2);
			JSONObject a3 = new JSONObject();
			a3.put("name",".1.3.6.1.4.1.22014.1.3.3.1.2.7.0");
			a3.put("value",sdf.format(new Date()));
			a3.put("type","DATE");
			attrs.put(a3);
			JSONObject a4 = new JSONObject();
			a4.put("name",".1.3.6.1.4.1.22014.1.3.3.1.2.6.0");
			a4.put("value","CPU占用率达到92%");
			a4.put("type","");
			attrs.put(a4);
			JSONObject a5 = new JSONObject();
			a5.put("name",".1.3.6.1.4.1.22014.1.3.3.1.2.3.0");
			a5.put("value","192.168.1.119");
			a5.put("type","");
			attrs.put(a5);
			JSONObject a6 = new JSONObject();
			a6.put("name",".1.3.6.1.4.1.22014.1.3.3.1.2.10.0");
			a6.put("value","BROADA-PC119");
			a6.put("type","");
			attrs.put(a6);
			JSONObject a7 = new JSONObject();
			a7.put("name","SrvId");
			a7.put("value","8550");
			a7.put("type","");
			attrs.put(a7);
			JSONObject a8 = new JSONObject();
			a8.put("name","Department");
			a8.put("value","administration");
			a8.put("type","");
			attrs.put(a8);
			m.put("attribs", attrs);
			list.put(m);

			String param = list.toString();
			System.out.println("request param:" + param);

			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(param.getBytes());
			os.flush();

			System.out.println("response code:" + conn.getResponseCode());

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result;
			System.out.println("content:");
			while((result = reader.readLine())!=null){
				System.out.println(result);
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void testCommitEvent(){
		try {
			URL url = new URL("http://localhost:8890/arbiter/api/v2/arbiter/events/create");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

			JSONObject jo = new JSONObject();
			jo.put("sysAddr", "192.168.1.3");
			JSONArray events = new JSONArray();
			JSONObject a1 = new JSONObject();
			a1.put("sourceId","EVET-122212-1212");
			a1.put("occurTime",sdf.format(new Date()));
			a1.put("entityId","tempHumSensor-2");
			a1.put("entityName","中心机房2号温湿度探测点");
			a1.put("entityAddr","10.2.30.1");
			a1.put("severity",30);
			a1.put("name","温度超标事件");
			a1.put("type","overheatEvent ");
			a1.put("description","中心机房2号温湿度探测点温度为30度，超过阈值22度。");

			events.put(a1);
			a1 = new JSONObject();
			a1.put("sourceId","EVET-122212-1112");
			a1.put("occurTime",sdf.format(new Date()));
			a1.put("entityId","tempHumSensor-3");
			a1.put("entityName","中心机房3号温湿度探测点");
			a1.put("entityAddr","10.2.30.1");
			a1.put("severity",30);
			a1.put("name","温度超标事件");
			a1.put("type","overheatEvent ");
			a1.put("description","中心机房3号温湿度探测点温度为32度，超过阈值24度。");
			events.put(a1);
			jo.put("events", events);

			String param = jo.toString();
			System.out.println("request param:" + param);

			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(param.getBytes());
			os.flush();

			System.out.println("response code:" + conn.getResponseCode());

			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String result;
			System.out.println("content:");
			while((result = reader.readLine())!=null){
				System.out.println(result);
			}

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

    public void testSaveTags(){
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/alert/tags");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");

            ObjectMapper mapper = new ObjectMapper();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("userId", "john");
            List<String> tagIdList = new ArrayList<String>();
            tagIdList.add("1");
            tagIdList.add("2");
            tagIdList.add("3");
            tagIdList.add("4");
            tagIdList.add("5");
            map.put("tagIdList", tagIdList);

            String param = mapper.writeValueAsString(map);

            System.out.println("request param:" + param);

            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(param.getBytes());
            os.flush();

            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while((result = reader.readLine())!=null){
                System.out.println(result);
            }

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testGetTags(){
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/alert/tags/john");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");


            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while ((result = reader.readLine()) != null) {
                System.out.println(result);
            }

            conn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void testGetAllTags() {
        try {
            URL url = new URL("http://localhost:8890/arbiter/api/v2/alert/tags/all");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setRequestProperty("Accept", "application/json;charset=utf-8");


            System.out.println("response code:" + conn.getResponseCode());

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String result;
            System.out.println("content:");
            while ((result = reader.readLine()) != null) {
                System.out.println(result);
            }

            conn.disconnect();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
