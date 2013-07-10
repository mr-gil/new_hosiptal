package com.sdu.edu.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
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

import com.sdu.edu.dbdao.Patientmaster;
import com.sdu.edu.dbdao.Loginmaster;

public class ContopatientAdmin {
	public String url;
	public String choose;
	Patientmaster patient;
	public int type;
	Loginmaster loginmaster;
	public String password;
	public ContopatientAdmin(String choose,Patientmaster patient){
	 
	    this.choose=choose;
	    this.patient=patient;	
	    
	    
}
	public ContopatientAdmin(String choose,Patientmaster patient,String password){
		 
	    this.choose=choose;
	    this.patient=patient;	
	    this.password=password;
	    
}
	
	public String Post() {
		
		/* 建立HTTP Post联机 */
		HttpPost httpRequest = new HttpPost("http://10.0.2.2:8080/hospital/"+"apatient");
		/*
		 * Post运作传送变量必须用NameValuePair[]数组储存
		 */
		System.out.println("update");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String datestring =sdf.format(patient.getPatientDateofreg());
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("choose", choose));
		params.add(new BasicNameValuePair("id",patient.getPatientId().toString() ));
		params.add(new BasicNameValuePair("name", patient.getPatientName().toString()));
		params.add(new BasicNameValuePair("gender", patient.getPatientGender().toString()));
		params.add(new BasicNameValuePair("email", patient.getPatientEmail().toString()));
		params.add(new BasicNameValuePair("dateofeg",datestring ));
		params.add(new BasicNameValuePair("phonenumber", patient.getPatientPhonenumber().toString()));
		
		params.add(new BasicNameValuePair("password",password));
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
				return "linkerror";
				
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
