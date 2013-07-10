package com.sdu.edu.admin;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;


import com.sdu.edu.adapter.MenuListAdapter;
import com.sdu.edu.adapter.UpdatebedAdapter;

import com.sdu.edu.callback.SizeCallBackForMenu;
import com.sdu.edu.control.Connectgetallbed;
import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.main.R;
import com.sdu.edu.ui.MenuHorizontalScrollView;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DeletebedActivity extends Activity {
	// delete patient
	//@author sdusjy
	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private ListView patientList;

	private UpdatebedAdapter upadater;
	private List<String> listItems;
	private View up_patientPage;
	private Button menuBtn;
	private MenuListAdapter menuListAdapter;
	private TextView title;
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框

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
		this.menuList = (ListView) findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);

		this.init();
		Log.i("执行了吗", listItems.toString());
		this.up_patientPage = inflater.inflate(R.layout.activity_update_doctor,
				null);
		this.title = (TextView) this.up_patientPage
				.findViewById(R.id.topbar_title);
		title.setText("Delete Bed");
		title.setGravity(Gravity.RIGHT);
		title.setTextSize(10.0f);
		this.menuBtn = (Button) this.up_patientPage.findViewById(R.id.menuBtn);

		this.menuBtn.setOnClickListener(onClickListener);

		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		final View[] children = new View[] { leftView, up_patientPage };
		this.scrollView.initViews(children, new SizeCallBackForMenu(
				this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
		}
	};

	private void init() {
		listItems = new ArrayList<String>();


		pd_loging = new ProgressDialog(DeletebedActivity.this);
		String meg_log = getString(R.string.login_loging).toString();
		pd_loging.setMessage("Running");
		pd_loging.show();

		Thread thread = new Thread(new Adddoctor());
		thread.start();
		Log.i("i am d", "ppppp");

	}

	private class Adddoctor implements Runnable {
		public void run() {
			// 获得所有列表
			Connectgetallbed ctt = new Connectgetallbed("1");
			// log_back = ctt.GetToTom();
			log_back = ctt.Post();
			Log.i("!!!!!!!!!!!!!", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			System.out.println(log_back);
			Log.i("!!!!!!!!!!!!!", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");

			adoctor.sendEmptyMessage(0);
			

		}

		private Handler adoctor = new Handler() {
			public void handleMessage(Message msg) {
				pd_loging.dismiss();
				System.out.println("我是ｓｊｙ");
				Log.i("HHHHHHHHHHHHHHHHHHHHHHHHHHHHH", "woshi 好人");

				if (log_back.equals("bedhaserror")) {

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

					Toast toast = Toast.makeText(getApplicationContext(),
							"error", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					return;
				} else {

					Toast toast = Toast.makeText(getApplicationContext(),
							"Success", Toast.LENGTH_LONG);
					toast.setGravity(Gravity.CENTER, 0, 0);
					toast.show();
					try {
						JSONArray array = new JSONArray(log_back);
						System.out.println(array.toString());
						for (int i = 0; i < array.length(); i++) {
							listItems.add(array.getJSONObject(i).toString());
						}
						System.out.println(listItems.get(0) + "hahhha");
						Log.i("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1",
								array.toString());
						Log.i("YYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYYY",
								listItems.get(0));

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					Log.i("!!!!!!!!!!!!!", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
				}
				if (msg.what == 0) {

					Log.i("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
							"" + listItems.toString());
					upadater = new UpdatebedAdapter(DeletebedActivity.this,
							R.layout.all_bed_list, listItems);
					patientList = (ListView)up_patientPage
							.findViewById(R.id.update_doctor_list);
					patientList.setAdapter(upadater);
					
				}

			}

		};

	}
}
