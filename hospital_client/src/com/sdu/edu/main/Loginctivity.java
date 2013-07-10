package com.sdu.edu.main;


import com.sdu.edu.admin.AdminMainActivity;
import com.sdu.edu.control.ConnectToServer;
import com.sdu.edu.dbdao.Loginmaster;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Loginctivity extends Activity {
	EditText username, password;
	Button loginbutton;
	
	private static Loginmaster loginmaster;
	RadioButton admin, doctor, patient;
	private static String typed="admin";
	RadioGroup rgroup;
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 去掉标题栏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_loginctivity);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_loginctivity, menu);
		return true;
	}

	public void init() {
		username = (EditText) findViewById(R.id.login_edit_username);
		password = (EditText) findViewById(R.id.login_edit_password);
		admin = (RadioButton) findViewById(R.id.admin);
		doctor = (RadioButton) findViewById(R.id.doctor);
		patient = (RadioButton) findViewById(R.id.patient);
		rgroup=(RadioGroup)findViewById(R.id.radiogroup);
		loginbutton=(Button)findViewById(R.id.login);
		rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			//判断是哪个账户
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==admin.getId()){
					typed="admin";
					Toast.makeText(Loginctivity.this, "你选择了ａｄｍｉｎ", 20).show();
				}else{
					
					if(checkedId==doctor.getId()){
						typed="doctor";
						
					}else{
						if(checkedId==patient.getId()){
							typed="patient";
							
							
						}
						
						
					}
				}
			}
		});
		
		loginbutton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(Loginctivity.this, "hello", 2).show();
			   loginmaster=new Loginmaster();
				loginmaster.setUsername(username.getText().toString());
				loginmaster.setPassword(password.getText().toString());
				loginmaster.setType(typed);
				
				pd_loging = new ProgressDialog(Loginctivity.this);
				
				pd_loging.setMessage("Running");
				pd_loging.show();
				Thread thread = new Thread(new Loging());
				thread.start();
			}
		});
		
		
		
		
	}
	// 用于响应登录按钮的子进程
		private class Loging implements Runnable {
			public void run() {
				ConnectToServer ctt = new ConnectToServer(1, loginmaster);
				//log_back = ctt.GetToTom();
				log_back = ctt.Post();
				
				System.out.println(log_back);
				hl_loging.sendEmptyMessage(0);
			}
		}
		private Handler hl_loging = new Handler() {
			public void handleMessage(Message msg) {
				pd_loging.dismiss();
				if (log_back.equals("Youarefail")) {
					String show3 = getString(R.string.login_show4).toString();
					Toast toast = Toast.makeText(getApplicationContext(), show3,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					username.setText("");
					password.setText("");
					username.requestFocus();
					return;
				} /*else if (log_back.equals("youarefail")) {
					String show4 = getString(R.string.login_show4).toString();
					Toast toast = Toast.makeText(getApplicationContext(), show4,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					password.setText("");
					password.requestFocus();
					return;
				} else if (log_back.equals("error")) {
					String show5 = getString(R.string.login_show5).toString();
					Toast toast = Toast.makeText(getApplicationContext(), show5,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					return;
				}*/ else if (log_back.equals("error")
						) {
					String show6 = getString(R.string.login_show6).toString();
					Toast toast = Toast.makeText(getApplicationContext(), show6,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					return;
				} else if (log_back.equals("Youaresuccess")) {

					
					// 显示登录成功
					String show7 = getString(R.string.login_show7).toString();
					Toast toast = Toast.makeText(getApplicationContext(), show7,
							Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					//获取服务器传来的name，以及更改配置文件的登录信息
					Intent intent=new Intent();
					intent.setClass(Loginctivity.this,AdminMainActivity.class);
					startActivity(intent);
					
				}
			}
		};
}
