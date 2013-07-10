package com.sdu.edu.main;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class XMUPageActivity extends Activity {
    //菜单栏进入页面
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmu_page);
		this.webView = (WebView)this.findViewById(R.id.webView);
		String url = "http://www.xmu.edu.cn";
		this.webView.loadUrl(url);
	}
	
	
}
