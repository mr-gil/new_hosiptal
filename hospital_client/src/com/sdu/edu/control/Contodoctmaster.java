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

import com.sdu.edu.dbdao.Doctormaster;
import com.sdu.edu.dbdao.Loginmaster;

public class Contodoctmaster {
	public String url;
	public String choose;
	Doctormaster doctor;
	public int type;
	Loginmaster loginmaster;
	public String password;
	public Contodoctmaster(String choose,Doctormaster doctor){
	 
	    this.choose=choose;
	    this.doctor=doctor;	
	    
	    
}
	public Contodoctmaster(String choose,Doctormaster doctor,String password){
		 
	    this.choose=choose;
	    this.doctor=doctor;	
	    this.password=password;
	    
}
	
	public String Post() {
		
		/* 建立HTTP Post联机 */
		HttpPost httpRequest = new HttpPost("http://10.0.2.2:8080/hospital/"+"update");
		/*
		 * Post运作传送变量必须用NameValuePair[]数组储存
		 */
		System.out.println("update");
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("choose", choose));
//		if(choose.equals(0)){
//			params.add(new BasicNameValuePair("name", doctor.getDoctorName().toString()));
//			params.add(new BasicNameValuePair("gender", doctor.getDoctorGender().toString()));
//			params.add(new BasicNameValuePair("fees", doctor.getDoctorFees().toString()));
//			params.add(new BasicNameValuePair("speciality", doctor.getDoctorSpeciality().toString()));
//			params.add(new BasicNameValuePair("phonenumber", doctor.getDoctorPhonenumber().toString()));
//			params.add(new BasicNameValuePair("email", doctor.getDoctorEmail().toString()));
//			params.add(new BasicNameValuePair("password",password));
//			System.out.println("password"+password);
//		}else{
		params.add(new BasicNameValuePair("id",doctor.getDoctorId().toString() ));
		params.add(new BasicNameValuePair("name", doctor.getDoctorName().toString()));
		params.add(new BasicNameValuePair("gender", doctor.getDoctorGender().toString()));
		params.add(new BasicNameValuePair("fees", doctor.getDoctorFees().toString()));
		params.add(new BasicNameValuePair("speciality", doctor.getDoctorSpeciality().toString()));
		params.add(new BasicNameValuePair("phonenumber", doctor.getDoctorPhonenumber().toString()));
		params.add(new BasicNameValuePair("email", doctor.getDoctorEmail().toString()));
		params.add(new BasicNameValuePair("password",password));
		//}
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
