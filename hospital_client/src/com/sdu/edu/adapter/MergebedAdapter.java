package com.sdu.edu.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.sdu.edu.admin.MergeinsertBedActivity;
import com.sdu.edu.admin.MergeinsertpatientActivity;

import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.dbdao.Bedmaster;
import com.sdu.edu.dbdao.Doctormaster;
import com.sdu.edu.main.R;

public class MergebedAdapter extends BaseAdapter {

	private List<String> listItems;
	
	private Context context;
	private LayoutInflater layoutInflater;
	private int layoutResource;
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框
	private static Bedmaster bed;
	public MergebedAdapter(Context context, int resource,List<String> listItems) {
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
			bed=new Bedmaster();
			JSONObject object = new JSONObject(listItems.get(position));
			bed.setBedId(Integer.parseInt(object.getString("id")));
			bed.setBedClass(object.getString("bed_class"));
			bed.setBedPrice(Double.parseDouble(object.getString("price")));
			bed.setBedAvailability(object.getString("availability"));	
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		// 从xml布局获得列表中的元素
				TextView id = (TextView) convertView.findViewById(R.id.bed_list_id);
				id.setText(bed.getBedId().toString());
				TextView classgroup = (TextView) convertView
						.findViewById(R.id.bed_list_class);
				classgroup.setText(bed.getBedClass());
				TextView price = (TextView) convertView
						.findViewById(R.id.bed_list_price);
				price.setText(bed.getBedPrice().toString());
				TextView avaa= (TextView) convertView
						.findViewById(R.id.bed_list_ava);
				avaa.setText(bed.getBedAvailability());
				ImageView delete_image = (ImageView) convertView
						.findViewById(R.id.bed_list_delete);

				delete_image.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
						
						Intent intent =new Intent();
						intent.putExtra("bed", listItems.get(position));
						intent.setClass(context, MergeinsertBedActivity.class);
						context.startActivity(intent);
				
						
					}
				});
		
				
					
	
					
					
					
					
				
				
				
				
				
				
				
	
		
		return convertView;
	}
	
	
	
	
}
