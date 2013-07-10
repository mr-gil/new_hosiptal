package com.sdu.edu.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.sdu.edu.dbdao.Loginmaster;

public class ConnectToServer {
	public String url;
	public int choose;
	Object object;
	Loginmaster loginmaster;
	public ConnectToServer(int choose,Object object){
	    this.url=url;
	    this.choose=choose;
	    this.object=object;	
	    switch(choose){
	    case 1:
	    	if(object instanceof Loginmaster){
	    		this.loginmaster=(Loginmaster)object;
	    		this.url=Utils.getIP()+"login"+"?name="+loginmaster.getUsername()+"&pass="+loginmaster.getPassword()+"&type="+loginmaster.getType();
	    		
	    		
	    	}
	    	break;
	    }
	    
}
	public String Get(){
		/* 建立HTTP Get联机 */
		HttpGet httpRequest = new HttpGet(url);
		try {
			/* 发出HTTP request */
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 取出响应字符串 */
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				strResult = eregi_replace("(\r\n|\r|\n|\n\r)", "", strResult);
				return strResult;
			} else {
				return "link error";
			}
		} catch (ClientProtocolException e) {
			return "error";
		} catch (IOException e) {
			return "error";
		} catch (Exception e) {
			return "error";
		}
	}
	public String Post() {
		
		/* 建立HTTP Post联机 */
		HttpPost httpRequest = new HttpPost("http://10.0.2.2:8080/hospital/"+"login");
		/*
		 * Post运作传送变量必须用NameValuePair[]数组储存
		 */
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		
		params.add(new BasicNameValuePair("uname", loginmaster.getUsername()));
		params.add(new BasicNameValuePair("upass", loginmaster.getPassword()));
		params.add(new BasicNameValuePair("utype", loginmaster.getType()));
		try {
			/* 发出HTTP request */
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			/* 取得HTTP response */
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(httpRequest);
			System.out.println("httprespo");
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 取出响应字符串 */
				String strResult = EntityUtils.toString(httpResponse
						.getEntity());
				strResult = eregi_replace("(\r\n|\r|\n|\n\r)", "", strResult);
				return strResult;
			} else {
				System.out.println("ui");
				return "error";
				
			}
		} catch (ClientProtocolException e) {
			return "ierror";
		} catch (IOException e) {
			return "oerror";
		} catch (Exception e) {
			return "perror";
		}
	}

	/* 自定义字符串取代函数 */
	
	/* 自定义字符串取代函数 */
	public String eregi_replace(String strFrom, String strTo, String strTarget) {
		String strPattern = "(?i)" + strFrom;
		Pattern p = Pattern.compile(strPattern);
		Matcher m = p.matcher(strTarget);
		if (m.find()) {
			return strTarget.replaceAll(strFrom, strTo);
		} else {
			return strTarget;
		}
	}


}
