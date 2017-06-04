package biibi.cc.phoneJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PhoneJson {

	private HttpsURLConnection conn = null;
	private InputStream inputStream = null;
	private InputStreamReader inputStreamReader = null;
	private BufferedReader bufferedReader = null;
	private StringBuffer buffer = null;
	
	public String JsonAddress(String phonecode){
		
		//手机验证网址
		String phonejsonurl = "https://apis.juhe.cn/mobile/get?phone="+phonecode+"&key=xxx";
		try {
			URL url = new URL(phonejsonurl);//创建网址对象
			conn = (HttpsURLConnection)url.openConnection();//生成请求，打开链接
					
			//获取输入流
			inputStream = conn.getInputStream();
					
			//字节流向字符流
			inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
					
			//读取响应内容
			buffer = new StringBuffer();
			String str = null;
					
			while((str = bufferedReader.readLine()) != null){
				buffer.append(str);
			}
				
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		}
		
		//关闭释放资源
		finally {
			try {
				if(inputStream != null)
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(inputStreamReader != null)
				inputStreamReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			conn.disconnect();
			
			
		}
		
		
		//获取json
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		//获取json第二级
		jsonObject = jsonObject.getJSONObject("result");
		//获取json第二级的部分数据
		String province = jsonObject.getString("province");
		String city = jsonObject.getString("city");
	
		System.out.println(province+city);
		return province+city;
	}
	public static void main(String[] args){
			PhoneJson pj = new PhoneJson();
			pj.JsonAddress("18857160481");
	}
}
