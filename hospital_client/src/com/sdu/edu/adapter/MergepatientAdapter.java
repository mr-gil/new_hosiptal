package com.sdu.edu.adapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import com.sdu.edu.admin.AdminMainActivity;
import com.sdu.edu.admin.MergeinsertActivity;
import com.sdu.edu.admin.MergeinsertpatientActivity;

import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.dbdao.Doctormaster;
import com.sdu.edu.dbdao.Patientmaster;
import com.sdu.edu.main.R;

public class MergepatientAdapter extends BaseAdapter {
	private String password;
	private List<String> listItems;
	private Patientmaster patient;
	private Context context;
	private LayoutInflater layoutInflater;
	private int layoutResource;
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框
	private Doctormaster doctor;
	public MergepatientAdapter(Context context, int resource,List<String> listItems) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutResource = resource;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.listItems=new ArrayList<String>();
		this.listItems = listItems; 
		Log.i("listitem的长度", ""+listItems.size());
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listItems.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = layoutInflater.inflate(layoutResource, null);
		}
		try {
			patient = new Patientmaster();
			JSONObject object = new JSONObject(listItems.get(position));
			Log.i("JJJJJJJJJJJJJJJ", object.toString());
			patient.setPatientId(Integer.parseInt(object.getString("id")));
			patient.setPatientName(object.getString("name"));
			patient.setPatientGender(object.getString("gender"));
			String datestring = object.getString("dateofeg");
			System.out.println(datestring + "日期是");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			sdf.setLenient(false);
			String sdate = datestring;
			Date ddate = null;
			ddate = sdf.parse(sdate);
			patient.setPatientDateofreg(ddate);

			patient.setPatientEmail(object.getString("email"));
			patient.setPatientPhonenumber(Long.parseLong(object
					.getString("phonenumber")));
			password = object.getString("password");
			System.out.println(object.getString("id"));
		} catch (Exception e) {
			e.printStackTrace();

		}
		// 从xml布局获得列表中的元素
		TextView id = (TextView) convertView.findViewById(R.id.patient_list_id);
		id.setText(patient.getPatientId().toString());
		TextView name = (TextView) convertView
				.findViewById(R.id.patient_list_name);
		name.setText(patient.getPatientName());
		TextView gender = (TextView) convertView
				.findViewById(R.id.patient_list_gender);
		gender.setText(patient.getPatientGender());
		TextView dateofeg = (TextView) convertView
				.findViewById(R.id.patient_list_date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String datestring = sdf.format(patient.getPatientDateofreg());
		dateofeg.setText(datestring);

		TextView phone = (TextView) convertView
				.findViewById(R.id.patient_list_phone);
		phone.setText(patient.getPatientPhonenumber().toString());
		TextView email = (TextView) convertView
				.findViewById(R.id.patient_list_email);
		email.setText(patient.getPatientEmail());
		final TextView password2 = (TextView) convertView
				.findViewById(R.id.patient_list_password);
		password2.setText("");
		password2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				password2.setText(password);
			}
		});
		ImageView delete_image = (ImageView) convertView
				.findViewById(R.id.patient_list_delete);

		
	

				
		

		delete_image.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				Intent intent =new Intent();
				intent.putExtra("patient", listItems.get(position));
				intent.setClass(context, MergeinsertpatientActivity.class);
				context.startActivity(intent);
			}
		});

		

			
			

					
					

				
					
					
					
					
				
				
				
				
				
				
				
	
		return convertView;
	}
	
	
	
	
}
