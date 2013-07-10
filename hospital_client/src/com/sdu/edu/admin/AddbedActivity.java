package com.sdu.edu.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sdu.edu.adapter.MenuListAdapter;
import com.sdu.edu.callback.SizeCallBackForMenu;
import com.sdu.edu.control.ConnectToServer;
import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.control.ContobedAdmin;
import com.sdu.edu.control.Judge;

import com.sdu.edu.dbdao.Bedmaster;

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

public class AddbedActivity extends Activity {
	// 第一个主界面
	private MenuHorizontalScrollView scrollView;
	private ListView menuList;

	private RadioGroup bedclass;
	private RadioButton bedfirst;
	private RadioButton bedsecond;
	private RadioButton bedthird;
	private EditText price;
	private RadioGroup availability;
	private RadioButton bedyes;
	private RadioButton bedno;

	private Button insert;
	private static Bedmaster bed;
	private View addbedPage;
	private Button menuBtn;
	private TextView title;
	private MenuListAdapter menuListAdapter;
	private static String bedselectedclass = "first";
	private static String bedselectedavailability = "yes";

	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		LayoutInflater inflater = LayoutInflater.from(this);

		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));

		this.scrollView = (MenuHorizontalScrollView) findViewById(R.id.scrollView);
		this.menuListAdapter = new MenuListAdapter(this, 0);
		bed = new Bedmaster();
		this.menuList = (ListView) findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);

		this.addbedPage = inflater.inflate(R.layout.add_bed, null);

		this.menuBtn = (Button) this.addbedPage.findViewById(R.id.menuBtn);
		// /解析主页面的布局元素
		// 解析class
		this.bedclass = (RadioGroup) this.addbedPage
				.findViewById(R.id.bed_class_group);
		this.bedfirst = (RadioButton) this.addbedPage
				.findViewById(R.id.bed_first);
		this.bedsecond = (RadioButton) this.addbedPage
				.findViewById(R.id.bed_second);
		this.bedthird = (RadioButton) this.addbedPage
				.findViewById(R.id.bed_third);
		
		this.bedclass
				.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(RadioGroup group, int checkedId) {
						// TODO Auto-generated method stub
						if (checkedId == bedfirst.getId()) {
							bedselectedclass = "first";

						} else {
							if (checkedId == bedsecond.getId()) {
								bedselectedclass = "second";

							} else {
								if (checkedId == bedthird.getId()) {
									bedselectedclass = "third";

								}

							}

						}
					}
				});

		// 解析price
		price = (EditText) this.addbedPage.findViewById(R.id.bed_add_price);
		// 解析availability
		this.availability = (RadioGroup) this.addbedPage
				.findViewById(R.id.bed_availability_group);
		this.bedyes = (RadioButton) this.addbedPage.findViewById(R.id.bed_yes);
		this.bedno = (RadioButton) this.addbedPage.findViewById(R.id.bed_no);
		this.availability 
		.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (checkedId == bedyes.getId()) {
					bedselectedavailability= "yes";

				} else {
					if (checkedId == bedno.getId()) {
						bedselectedavailability = "no";

					}

				}
			}
		});
		
		
		
		this.title = (TextView) this.addbedPage.findViewById(R.id.topbar_title);
		
		title.setText("ADD New Bed Details");
		title.setGravity(Gravity.RIGHT);
		title.setTextSize(10.0f);

		insert = (Button) this.addbedPage.findViewById(R.id.bed_insert);

		if (insert == null) {
			Log.i("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!", "!!!!!!!!!!!!!!!!!"
					+ insert + "----------------");
		}

		this.menuBtn.setOnClickListener(onClickListener2);

		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		final View[] children = new View[] { leftView, addbedPage };
		this.scrollView.initViews(children, new SizeCallBackForMenu(
				this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
		insert.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                if(Judge.isDouble(price.getText().toString())){
				bed.setBedId(1);
				bed.setBedClass(bedselectedclass);
				bed.setBedPrice(Double.parseDouble(price.getText().toString()));
				bed.setBedAvailability(bedselectedavailability);

				pd_loging = new ProgressDialog(AddbedActivity.this);
				// String meg_log = getString(R.string.login_loging).toString();
				pd_loging.setMessage("Running");
				pd_loging.show();

				Thread thread = new Thread(new Addbed());
				thread.start();
				Log.i("i am d", "ppppp");
                }
                else{
                	Toast.makeText(AddbedActivity.this, "the price must double", 2).show();
                	
                	
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

	private class Addbed implements Runnable {
		public void run() {
			ContobedAdmin ctt = new ContobedAdmin("0", bed);
			// log_back = ctt.GetToTom();
			log_back = ctt.Post();
			Log.i("!!!!!!!!!!!!!", "UUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU");
			System.out.println(log_back);
			abed.sendEmptyMessage(0);
		}
	}

	private Handler abed = new Handler() {
		public void handleMessage(Message msg) {
			pd_loging.dismiss();
			if (log_back.equals("bedhaserror")) {

				Toast toast = Toast.makeText(getApplicationContext(),
						"The bed have resgistered", Toast.LENGTH_LONG);
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
