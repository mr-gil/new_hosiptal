package com.sdu.edu.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdu.edu.admin.AddbedActivity;
import com.sdu.edu.admin.AddpatientActivity;
import com.sdu.edu.admin.AdminMainActivity;
import com.sdu.edu.admin.DeletebedActivity;
import com.sdu.edu.admin.MergeDoctorActivity;
import com.sdu.edu.admin.MergePatientActivity;
import com.sdu.edu.admin.MergebedActivity;
import com.sdu.edu.admin.UpdateDoctorActivity;
import com.sdu.edu.admin.UpdatepatientActivity;

import com.sdu.edu.main.CAROLPageActivity;
import com.sdu.edu.main.R;
import com.sdu.edu.main.XMUPageActivity;



import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * MenuList的适配器
 * @author acbuwa
 *
 */
//menulist左侧栏适配器得到
public class MenuListAdapter extends BaseAdapter {
	private static String []adminitems=new String[19];
	private Context context;
	private List<Map<String, Object>> listItems;
	private int itemCount;
	private LayoutInflater listInflater;
	private boolean isPressed[];
	private int imageId = R.drawable.dotaicon;
	private String textContent = "i love xmu";
	//定义菜单栏的数目
	private final int COUNT = 19; 
	private int pressedId;
	
	/*一个menu item中包含一个imageView和一个TextView*/
	public final class ListItemsView{
		public ImageView menuIcon;
		public TextView menuText;
	}
	
	
	public MenuListAdapter(Context context, int pressedId) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.pressedId = pressedId;
		this.init();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.itemCount;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final int po = position;
		ListItemsView listItemsView;
		if(convertView == null){
			listItemsView = new ListItemsView();
			convertView = this.listInflater.inflate(R.layout.menu_list_item, null);
			listItemsView.menuIcon = (ImageView)convertView.findViewById(R.id.menuIcon);
			listItemsView.menuText = (TextView)convertView.findViewById(R.id.menuText);
			convertView.setTag(listItemsView);
		}
		else{
			listItemsView = (ListItemsView)convertView.getTag();
		}
		
		listItemsView.menuIcon.setBackgroundResource((Integer)listItems.get(position).get("menuIcon"));
		listItemsView.menuText.setText((String)listItems.get(position).get("menuText"));
		
		if(this.isPressed[position] == true)
			convertView.setBackgroundResource(R.drawable.menu_item_bg_sel);
		else
			convertView.setBackgroundColor(Color.TRANSPARENT);
		
		convertView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				changeState(po);
				gotoActivity(po);
				notifyDataSetInvalidated();
				new Handler().post(new Runnable(){

					@Override
					public void run() {
						// TODO Auto-generated method stub
					}
					
				});
			}
		});
		
		return convertView;
	} 
	
	private void gotoActivity(int position){
		Intent intent = new Intent();
		switch(position){
		case 0:
			intent.setClass(context, AdminMainActivity.class);
			context.startActivity(intent);
			break;
		/*----------------------------------------------------*/	
		case 1:
			intent.setClass(context, MergeDoctorActivity.class);
			context.startActivity(intent);
			break;
		/*----------------------------------------------------*/
		case 2:
			intent.setClass(context, UpdateDoctorActivity.class);
			context.startActivity(intent);
			break;
		case 3:
			intent.setClass(context, AddpatientActivity.class);
			context.startActivity(intent);
			break;
		case 4:
			intent.setClass(context, MergePatientActivity.class);
			context.startActivity(intent);
			break;
		case 5:
			intent.setClass(context, UpdatepatientActivity.class);
			context.startActivity(intent);
			break;
		case 6:
			intent.setClass(context, AddbedActivity.class);
			context.startActivity(intent);
			break;
		case 7:
			intent.setClass(context, MergebedActivity.class);
			context.startActivity(intent);
			break;
		case 8:
			intent.setClass(context, DeletebedActivity.class);
			context.startActivity(intent);
			break;
		default:
			intent.setClass(context, XMUPageActivity.class);
			context.startActivity(intent);
		}
	}
	
	private void changeState(int position){
		
		for(int i = 0; i < this.itemCount; i++){
			isPressed[i] = false;
		}
		isPressed[position] = true;
	}
	
	private void init(){
		 adminitems=context.getResources().getStringArray(R.array.adminmodules);
		this.itemCount = this.COUNT;
		this.listItems =  new ArrayList<Map<String, Object>>();
		this.isPressed = new boolean[this.itemCount];
		for(int i = 0; i < this.itemCount; i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("menuIcon", imageId);
			
				map.put("menuText", adminitems[i]);
			
			this.listItems.add(map);
			this.isPressed[i] = false;
		}
		this.isPressed[this.pressedId] = true;
		this.listInflater = LayoutInflater.from(context); 
	}
}
