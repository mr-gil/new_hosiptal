package com.sdu.edu.main;


import com.sdu.edu.adapter.MenuListAdapter;
import com.sdu.edu.callback.SizeCallBackForMenu;
import com.sdu.edu.ui.MenuHorizontalScrollView;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class CAROLPageActivity extends Activity {
	
	private MenuHorizontalScrollView scrollView;
	private ListView menuList;
	private View carolPage;
	private Button menuBtn;
	private MenuListAdapter menuListAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = LayoutInflater.from(this);
		
		setContentView(inflater.inflate(R.layout.menu_scroll_view, null));
		this.scrollView = (MenuHorizontalScrollView)findViewById(R.id.scrollView);
		this.menuListAdapter = new MenuListAdapter(this, 1);
		this.menuList = (ListView)findViewById(R.id.menuList);
		this.menuList.setAdapter(menuListAdapter);
		
		this.carolPage = inflater.inflate(R.layout.carol_page, null);
		this.menuBtn = (Button)this.carolPage.findViewById(R.id.menuBtn);
		this.menuBtn.setOnClickListener(onClickListener);
		
		View leftView = new View(this);
		leftView.setBackgroundColor(Color.TRANSPARENT);
		final View[] children = new View[]{leftView, carolPage};
		this.scrollView.initViews(children, new SizeCallBackForMenu(this.menuBtn), this.menuList);
		this.scrollView.setMenuBtn(this.menuBtn);
	}
	

	
	private OnClickListener onClickListener = new OnClickListener(){

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			scrollView.clickMenuBtn();
		}
	};
}
