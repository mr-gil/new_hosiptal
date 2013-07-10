package com.sdu.edu.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.andreabaccega.widget.FormEditText;
import com.sdu.edu.adapter.MenuListAdapter;
import com.sdu.edu.callback.SizeCallBackForMenu;
import com.sdu.edu.control.ConnectToServer;
import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.control.ContopatientAdmin;

import com.sdu.edu.dbdao.Patientmaster;
import com.sdu.edu.main.Loginctivity;
import com.sdu.edu.main.R;

import com.sdu.edu.ui.MenuHorizontalScrollView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddpatientActivity extends Activity {
	// 添加patient
	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	// 声明一个独一无二的标识，来作为要显示DatePicker的Dialog的ID：
	static final int DATE_DIALOG_ID = 0;
	private FormEditText name;
	private TextView dateofreg;

	private FormEditText phonenumber;
	private FormEditText email;
	private EditText password;
	private RadioGroup gender;
	private RadioButton male;
	private RadioButton female;
	private Button insert;
	private static Patientmaster patient;
	private View addpatientPage;
	private Button menuBtn;
	private TextView title;
	private MenuListAdapter menuListAdapter;
	private String sex = "male";
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框
	// 用来保存年月日：
	private int mYear;
	private int mMonth;
	private int mDay;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// WindowManager.LayoutParams.FLAG_FULLSCREEN);
		LayoutInflater inflater = LayoutInflater.from(this);

		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));

		this.scrollView = (MenuHorizontalScrollView) findViewById(R.id.scrollView);
		this.menuListAdapter = new MenuListAdapter(this, 0);
		patient = new Patientmaster();
		this.menuList = (ListView) findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);

		this.addpatientPage = inflater.inflate(R.layout.add_patient, null);

		this.menuBtn = (Button) this.addpatientPage.findViewById(R.id.menuBtn);
		// /解析patient页面的布局元素
		//获得当前的日期：
        final Calendar currentDate = Calendar.getInstance();
        mYear = currentDate.get(Calendar.YEAR);
        mMonth = currentDate.get(Calendar.MONTH);
        mDay = currentDate.get(Calendar.DAY_OF_MONTH);
		this.name = (FormEditText) this.addpatientPage
				.findViewById(R.id.patient_add_name);
		this.gender = (RadioGroup) this.addpatientPage
				.findViewById(R.id.patient_gender_group);
		this.male = (RadioButton) this.addpatientPage
				.findViewById(R.id.patient_male);
		female = (RadioButton) this.addpatientPage
				.findViewById(R.id.patient_female);
		this.title = (TextView) this.addpatientPage
				.findViewById(R.id.topbar_title);
		title.setText("ADD New patient Details");
		title.setGravity(Gravity.RIGHT);
		title.setTextSize(10.0f);
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

		dateofreg = (TextView) this.addpatientPage
				.findViewById(R.id.patient_add_date);
		dateofreg.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);
			}
		});
		email = (FormEditText) this.addpatientPage
				.findViewById(R.id.patient_add_email);
		phonenumber = (FormEditText) this.addpatientPage
				.findViewById(R.id.patient_add_phonenumber);

		password = (EditText) this.addpatientPage
				.findViewById(R.id.patient_add_password);
		insert = (Button) this.addpatientPage.findViewById(R.id.patient_insert);

		if (insert == null) {
			Log.i("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", "!!!!!!!!!!!!!!!!!"
					+ insert + "----------------");
		}

		this.menuBtn.setOnClickListener(onClickListener2);

		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		final View[] children = new View[] { leftView, addpatientPage };
		this.scrollView.initViews(children, new SizeCallBackForMenu(
				this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
		insert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (name.testValidity() && phonenumber.testValidity()
						&& email.testValidity()) {

					patient.setPatientEmail(email.getText().toString());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

					String sdate = dateofreg.getText().toString();
					Log.i("日期是", sdate);
					Date ddate = null;
					try {
						ddate = sdf.parse(sdate);
						Log.i("日期是", ddate.toString());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					patient.setPatientDateofreg(ddate);
					patient.setPatientGender(sex);
					patient.setPatientId(2);
					patient.setPatientName(name.getText().toString());
					patient.setPatientPhonenumber(Long.parseLong(phonenumber
							.getText().toString()));

					pd_loging = new ProgressDialog(AddpatientActivity.this);
					// String meg_log =
					// getString(R.string.login_loging).toString();
					pd_loging.setMessage("Running");
					pd_loging.show();

					Thread thread = new Thread(new Addpatient());
					thread.start();
					Log.i("i am d", "ppppp");

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

	private class Addpatient implements Runnable {
		public void run() {
			ContopatientAdmin ctt = new ContopatientAdmin("0", patient,
					password.getText().toString());
			// log_back = ctt.GetToTom();
			log_back = ctt.Post();
			Log.i("!!!!!!!!!!!!!", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			System.out.println(log_back);
			apatient.sendEmptyMessage(0);
		}
	}

	private Handler apatient = new Handler() {
		public void handleMessage(Message msg) {
			pd_loging.dismiss();
			if (log_back.equals("patienthaserror")) {

				Toast toast = Toast.makeText(getApplicationContext(),
						"The patient have resgistered", Toast.LENGTH_LONG);
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
	// 需要定义弹出的DatePicker对话框的事件监听器：
	private DatePickerDialog.OnDateSetListener mDateSetListener = new OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			// 设置文本的内容：
			dateofreg.setText(new StringBuilder().append(mYear).append("-")
					.append(mMonth + 1).append("-")// 得到的月份+1，因为从0开始
					.append(mDay));
		}
		
	};

	/**
	 * 当Activity调用showDialog函数时会触发该函数的调用：
	 */
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}
}
