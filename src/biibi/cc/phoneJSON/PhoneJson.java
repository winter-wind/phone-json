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
		
		//�ֻ���֤��ַ
		String phonejsonurl = "https://apis.juhe.cn/mobile/get?phone="+phonecode+"&key=xxx";
		try {
			URL url = new URL(phonejsonurl);//������ַ����
			conn = (HttpsURLConnection)url.openConnection();//�������󣬴�����
					
			//��ȡ������
			inputStream = conn.getInputStream();
					
			//�ֽ������ַ���
			inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
					
			//��ȡ��Ӧ����
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
		
		//�ر��ͷ���Դ
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
		
		
		//��ȡjson
		JSONObject jsonObject = JSONObject.fromObject(buffer.toString());
		//��ȡjson�ڶ���
		jsonObject = jsonObject.getJSONObject("result");
		//��ȡjson�ڶ����Ĳ�������
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
