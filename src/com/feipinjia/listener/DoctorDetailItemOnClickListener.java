package com.feipinjia.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.feipinjia.activity.DoctorDetailActivity;
import com.feipinjia.model.Doctor;

public class DoctorDetailItemOnClickListener implements  AdapterView.OnItemClickListener{
	private Context context;
	public DoctorDetailItemOnClickListener(Context context){
		this.context=context;
	}
	@Override
	public void onItemClick(AdapterView<?> adpater, View view, int postion, long id) {
		Intent intent = new Intent();
		intent.setClass(context, DoctorDetailActivity.class);
		Doctor doctor=(Doctor) adpater.getItemAtPosition(postion);
		Bundle bundle = new Bundle();
		bundle.putString("docId", doctor.getUserId());
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
