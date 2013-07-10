package com.sdu.edu.adapter;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
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

import com.sdu.edu.control.Contodoctmaster;
import com.sdu.edu.dbdao.Doctormaster;
import com.sdu.edu.main.R;

public class UpdateAdapter extends BaseAdapter {

	private List<String> listItems;
	private static String password;
	private Context context;
	private LayoutInflater layoutInflater;
	private int layoutResource;
	private String log_back; // 用户点击登录按钮时返回的响应信号
	private ProgressDialog pd_loging;// 用户登录验证响应的缓冲对话框
	private Doctormaster doctor;

	public UpdateAdapter(Context context, int resource, List<String> listItems) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.layoutResource = resource;
		this.layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.listItems = new ArrayList<String>();
		this.listItems = listItems;
		Log.i("listitem的长度", "" + listItems.size());

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
			doctor = new Doctormaster();
			JSONObject object = new JSONObject(listItems.get(position));
			Log.i("JJJJJJJJJJJJJJJ", object.toString());
			doctor.setDoctorId(Integer.parseInt(object.getString("id")));
			doctor.setDoctorName(object.getString("name"));
			doctor.setDoctorGender(object.getString("gender"));
			doctor.setDoctorEmail(object.getString("email"));
			doctor.setDoctorFees(Double.parseDouble(object.getString("fees")));
			doctor.setDoctorSpeciality(object.getString("specility"));
			doctor.setDoctorPhonenumber(Long.parseLong(object
					.getString("phonenumber")));
			password = object.getString("password");
			System.out.println(object.getString("id"));
		} catch (JSONException e) {
			e.printStackTrace();

		}
		// 从xml布局获得列表中的元素
		TextView id = (TextView) convertView.findViewById(R.id.doctor_list_id);
		id.setText(doctor.getDoctorId().toString());
		TextView name = (TextView) convertView
				.findViewById(R.id.doctor_list_name);
		name.setText(doctor.getDoctorName());
		TextView gender = (TextView) convertView
				.findViewById(R.id.doctor_list_gender);
		gender.setText(doctor.getDoctorGender());
		TextView fees = (TextView) convertView
				.findViewById(R.id.doctor_list_fees);
		fees.setText(doctor.getDoctorFees().toString());
		TextView speciality = (TextView) convertView
				.findViewById(R.id.doctor_list_speciatity);
		speciality.setText(doctor.getDoctorSpeciality());
		
		TextView phone = (TextView) convertView
				.findViewById(R.id.doctor_list_phone);
		phone.setText(doctor.getDoctorPhonenumber().toString());
		TextView email = (TextView) convertView
				.findViewById(R.id.doctor_list_email);
		       email.setText(doctor.getDoctorEmail());
		final TextView password2 = (TextView) convertView
				.findViewById(R.id.doctor_list_password);
		password2.setText("");
		password2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				password2.setText(password);
			}
		});
		ImageView delete_image = (ImageView) convertView
				.findViewById(R.id.doctor_list_delete);
		
		final Dialog dialog = new AlertDialog.Builder(context)
				.setTitle("Delete doctor")
				.setMessage("Do you want to delete it ?")
				.setPositiveButton("yes",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								//点击yes删除
								pd_loging = new ProgressDialog(context);
								// String meg_log = getString(R.string.login_loging).toString();
								pd_loging.setMessage("Running");
								pd_loging.show();
								System.out.println("启动线程");
								Thread thread = new Thread(new Adddoctor());
								thread.start();

								listItems.remove(position);
								notifyDataSetChanged();

							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub

					}
				}).create();

		delete_image.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				// TODO Auto-generated method stub
				dialog.show();
			}
		});

		return convertView;
	}

	/**
	 * 初始化数据
	 */
	private class Adddoctor implements Runnable {
		public void run() {
			Contodoctmaster ctt = new Contodoctmaster("2", doctor, password);
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

				Toast toast = Toast.makeText(context,
						"The doctor have resgistered", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

				return;
			} else if (log_back.equals("linkerror")) {

				Toast toast = Toast.makeText(context, "link error",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

				return;
			} else if (log_back.equals("notdelete")) {

				Toast toast = Toast.makeText(context,
						"sorry ,You can't insert,please again",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			} else if (log_back.equals("error")) {

				Toast toast = Toast.makeText(context, "error",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			} else if (log_back.equals("success")) {

				Toast toast = Toast.makeText(context, "success",
						Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();

			}
		}
	};

}
