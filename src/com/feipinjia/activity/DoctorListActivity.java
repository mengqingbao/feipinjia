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

public class DoctorListActivity extends Activity implements OnClickListener{

	private Button backBtn;
	private String fileName;
	private DoctorListAdapter adpater;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_list_activity);
		backBtn=(Button) this.findViewById(R.id.doctor_list_btn_back);
		backBtn.setOnClickListener(this);
		listView=(ListView) this.findViewById(R.id.doctor_list_view);
		listView.setOnItemClickListener(new DoctorDetailItemOnClickListener(this));
		initData();
	}
	
	public void initData(){
		//adpater=new DoctorListAdapter(this,R.layout.doctor_list_item);
		List<Doctor> drs=DoctorStore.getInstance(this).queryPage("1",0, 10,this);
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
		case R.id.doctor_list_btn_back:
			this.finish();
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
