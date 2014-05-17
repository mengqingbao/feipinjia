package com.feipinjia.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.feipinjia.model.Doctor;
import com.feipinjia.persistence.DoctorStore;
import com.feipinjia.util.CountUtil;
import com.feipinjia.activity.R;

public class DoctorDetailActivity extends Activity implements OnClickListener{

	private Button backBtn,button1,button2,button3,button4; //button1~4:map 转发，免费咨询，出诊，收藏
	private Doctor doctor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_detail);
		String userId = getIntent().getStringExtra("docId");
		doctor = DoctorStore.getInstance(this).getById(userId,this);
		backBtn=(Button) this.findViewById(R.id.doctor_detail_btn_back);
		button2=(Button) this.findViewById(R.id.button2);
		backBtn.setOnClickListener(this);
		button2.setOnClickListener(this);
		initData();
	}
	
	public void initData(){
		if(doctor==null){
			return;
		}
		((TextView)findViewById(R.id.doctor_detail_tv1)).setText(doctor.getNick());
		((TextView)findViewById(R.id.doctor_detail_tv1_ext)).setText(doctor.getProcince()+" "+doctor.getCity());
		((TextView)findViewById(R.id.doctor_detail_tv2)).setText(doctor.getDesc());
		((TextView)findViewById(R.id.doctor_detail_tv3)).setText(doctor.getLevelDes());
		((TextView)findViewById(R.id.textView2)).setText(doctor.getInfo());
		((TextView)findViewById(R.id.textView4)).setText(doctor.getTecFrom());
		((TextView)findViewById(R.id.textView7)).setText("有效率"+CountUtil.doctorWCommentRate(doctor)+"  评价总数: "+CountUtil.getWTotal(doctor));
		((TextView)findViewById(R.id.textView10)).setText("有效率"+CountUtil.doctorZCommentRate(doctor)+"  评价总数: "+CountUtil.getZTotal(doctor));
		
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
		case R.id.button2:
			startChat();
			break;
		case R.id.doctor_detail_btn_back:
			this.finish();
			break;
		default:
			break;
		}
	}
	
	public void startChat(){
		Intent intent = new Intent();
		intent.setClass(this, InChatActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("docId", doctor.getUserId());
		bundle.putString("docNick", doctor.getNick());
		intent.putExtras(bundle);
		this.startActivity(intent);
	}

	@Override
	public void finish() {
		super.finish();
	}  
	}  
