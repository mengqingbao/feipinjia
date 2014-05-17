package com.feipinjia.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.feipinjia.adapter.DoctorListAdapter;
import com.feipinjia.listener.DoctorDetailItemOnClickListener;
import com.feipinjia.model.Doctor;
import com.feipinjia.persistence.DoctorStore;
import com.feipinjia.activity.R;

public class DoctorSearchActivity extends Activity implements OnClickListener {

	private Button backBtn, btn1, btn2, btn3;
	private String fileName;
	private DoctorListAdapter adpater;
	private ListView listView;
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_search_activity);
		backBtn = (Button) this.findViewById(R.id.doctor_search_btn_back);
		btn1 = (Button) this.findViewById(R.id.button1);
		btn2 = (Button) this.findViewById(R.id.button2);
		btn3 = (Button) this.findViewById(R.id.button3);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);
		btn3.setOnClickListener(this);
		backBtn.setOnClickListener(this);
		listView = (ListView) this.findViewById(R.id.doctor_search_list_view);
		listView.setOnItemClickListener(new DoctorDetailItemOnClickListener(
				this));
		initData();
	}

	public void initData() {
		List<Doctor> drs = DoctorStore.getInstance(this).queryPage("1", 0, 10,this);
		adpater = new DoctorListAdapter(this, R.layout.doctor_list_item, drs);
		listView.setAdapter(adpater);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.doctor_search_btn_back:
			this.finish();
			break;
		case R.id.button1:
			type=1;
			updateBf();
			break;
		case R.id.button2:
			type=2;
			updateBf();
			break;
		case R.id.button3:
			type=3;
			updateBf();
			break;		
		default:
			break;
		}
	}

	private void updateBf() {
		switch (type) {
		case 1:
			btn1.setBackgroundResource(R.drawable.u37_normal);
			btn2.setBackgroundResource(R.drawable.doctor_search_btn);
			btn3.setBackgroundResource(R.drawable.doctor_search_btn);
			break;
		case 2:
			btn1.setBackgroundResource(R.drawable.doctor_search_btn);
			btn2.setBackgroundResource(R.drawable.u37_normal);
			btn3.setBackgroundResource(R.drawable.doctor_search_btn);
			break;
		case 3:
			btn1.setBackgroundResource(R.drawable.doctor_search_btn);
			btn2.setBackgroundResource(R.drawable.doctor_search_btn);
			btn3.setBackgroundResource(R.drawable.u37_normal);
			break;
		default:
			break;
		}
	}

	@Override
	public void finish() {
		super.finish();
	}
}
