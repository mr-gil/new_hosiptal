package com.sdu.edu.control;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;

public class Judge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public static boolean isDouble(String string){
		
		try{
			Double a=Double.parseDouble(string);
			
			return true;
		}
		catch(Exception e){
			
			
			return false;
		}
		
		
	}
public static boolean isDate(String string){
	try {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		Date ddate=sdf.parse(string);
			Log.i("日期是", ddate.toString());
		
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		return false;
	}
	
	
	
}
}
