package com.sdu.edu.admin;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.andreabaccega.widget.FormEditText;
import com.sdu.edu.adapter.MenuListAdapter;

import com.sdu.edu.callback.SizeCallBackForMenu;
import com.sdu.edu.control.ConnectToServer;
import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.control.Judge;
import com.sdu.edu.dbdao.Doctormaster;
import com.sdu.edu.main.Loginctivity;
import com.sdu.edu.main.R;

import com.sdu.edu.ui.MenuHorizontalScrollView;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MergeinsertActivity extends Activity {
	// 第一个主界面
	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private FormEditText doctor_name;
	private EditText fees;
	private FormEditText seciality;
	private FormEditText phonenumber;
	private FormEditText email;
	private EditText password2;
	private RadioGroup gender;
	private RadioButton male;
	private RadioButton female;
	private Button insert;
	private static Doctormaster doctor;
	private View adddoctorPage;
	private Button menuBtn;
	private  TextView title;
	private MenuListAdapter menuListAdapter;
	private String sex = "male";
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框
	private static JSONObject object;
	private static String password;
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		LayoutInflater inflater = LayoutInflater.from(this);

		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));

		this.scrollView = (MenuHorizontalScrollView) findViewById(R.id.scrollView);
		this.menuListAdapter = new MenuListAdapter(this, 0);
		doctor = new Doctormaster();
		Intent intent = getIntent();
		String s = intent.getStringExtra("doctor");
		// 获取医生数据
		
		try {
			object = new JSONObject(s);
			doctor.setDoctorId(Integer.parseInt(object.getString("id")));
			doctor.setDoctorName(object.getString("name"));
			doctor.setDoctorGender(object.getString("gender"));
			doctor.setDoctorEmail(object.getString("email"));
			doctor.setDoctorFees(Double.parseDouble(object.getString("fees")));
			doctor.setDoctorSpeciality(object.getString("specility"));
			doctor.setDoctorPhonenumber(Long.parseLong(object
					.getString("phonenumber")));
			password=object.getString("password");
			System.out.println(object.getString("id"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.menuList = (ListView) findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);

		this.adddoctorPage = inflater.inflate(R.layout.add_doctor,
				null);

		this.menuBtn = (Button) this.adddoctorPage.findViewById(R.id.menuBtn);
		// /解析主页面的布局元素
		

		this.doctor_name = (FormEditText)  this.adddoctorPage.findViewById(R.id.doctor_add_name);
		doctor_name.setText(doctor.getDoctorName());
		this.gender = (RadioGroup)  this.adddoctorPage.findViewById(R.id.doctor_gender_group);
		
		
		this.male = (RadioButton)  this.adddoctorPage.findViewById(R.id.doctor_male);
		female = (RadioButton)  this.adddoctorPage.findViewById(R.id.doctor_female);
		this.title = (TextView) this.adddoctorPage
				.findViewById(R.id.topbar_title);
		title.setText("Update Doctor Details");
		title.setGravity(Gravity.RIGHT);
		title.setTextSize(10.0f);
		if (doctor.getDoctorGender().equals("male")) {
			male.setChecked(true);
		} else {
			female.setChecked(true);
		}
		this.gender
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == male.getId()) {
							sex = "male";

						} else {
							if (checkedId == female.getId()) {
								sex = "female";

							}

						}
					}
				});

		
		fees = (EditText)  this.adddoctorPage.findViewById(R.id.doctor_add_fees);
		fees.setText(doctor.getDoctorFees().toString());
		email = (FormEditText)  this.adddoctorPage.findViewById(R.id.doctor_add_email);
		phonenumber = (FormEditText) this.adddoctorPage.findViewById(R.id.doctor_add_phonenumber);
		seciality = (FormEditText) this.adddoctorPage.findViewById(R.id.doctor_add_speciality);
		password2=(EditText) this.adddoctorPage.findViewById(R.id.doctor_add_password);
		password2.setText(password);
		insert = (Button) this.adddoctorPage.findViewById(R.id.doctor_insert);
		
		email.setText(doctor.getDoctorEmail());
		
		
		phonenumber.setText(doctor.getDoctorPhonenumber().toString());
		
		seciality.setText(doctor.getDoctorSpeciality());
		
		insert.setText("Update");
		if (insert == null) {
			Log.i("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", "!!!!!!!!!!!!!!!!!"
					+ insert + "----------------");
		}

		this.menuBtn.setOnClickListener(onClickListener2);

		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		final View[] children = new View[] { leftView, adddoctorPage };
		this.scrollView.initViews(children, new SizeCallBackForMenu(
				this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
		insert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if((!fees.getText().equals(null))&&Judge.isDouble(fees.getText().toString())){
					   if(email.testValidity()&&doctor_name.testValidity()&&seciality.testValidity()&&phonenumber.testValidity()){
						   
							doctor.setDoctorEmail(email.getText().toString());
							doctor.setDoctorFees(Double.parseDouble(fees.getText().toString()));
							doctor.setDoctorGender(sex);
							
							doctor.setDoctorName(doctor_name.getText().toString());
							doctor.setDoctorPhonenumber(Long.parseLong(phonenumber
									.getText().toString()));
							doctor.setDoctorSpeciality(seciality.getText().toString());
							
							pd_loging = new ProgressDialog(MergeinsertActivity.this);
							//String meg_log = getString(R.string.login_loging).toString();
							pd_loging.setMessage("Running");
							pd_loging.show();
							
							Thread thread = new Thread(new Adddoctor());
							thread.start();
							Log.i("i am d", "ppppp");
					   }	
					
					}else{
						
					Toast.makeText(MergeinsertActivity.this, "the fees must double", 2).show();	
						
					}
					

			}
		});

	}

	private OnClickListener onClickListener2 = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
		}
	};

	private class Adddoctor implements Runnable {
		public void run() {
			Contodoctmaster ctt = new Contodoctmaster("3", doctor,password2.getText().toString());
			// log_back = ctt.GetToTom();
			log_back = ctt.Post();
			Log.i("!!!!!!!!!!!!!", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			System.out.println(log_back);
			adoctor.sendEmptyMessage(0);
		}
	}

	private Handler adoctor = new Handler() {
		public void handleMessage(Message msg) {
			pd_loging.dismiss();
			if (log_back.equals("doctorhaserror")) {

				Toast toast = Toast.makeText(getApplicationContext(),
						"The doctor have resgistered", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

				return;
			} else if (log_back.equals("linkerror")) {

				Toast toast = Toast.makeText(getApplicationContext(),
						"link error", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

				return;
			} else if (log_back.equals("notinsert")) {

				Toast toast = Toast.makeText(getApplicationContext(),
						"sorry ,You can't insert,please again",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			} else if (log_back.equals("error")) {

				Toast toast = Toast.makeText(getApplicationContext(), "error",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			} else if (log_back.equals("insertcorrect")) {

				Toast toast = Toast.makeText(getApplicationContext(),
						"Success", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

			}
		}
	};
}
