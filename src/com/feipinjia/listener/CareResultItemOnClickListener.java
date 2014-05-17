package com.feipinjia.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.feipinjia.activity.CareAdviceActivity;
import com.feipinjia.model.CareAdvice;

public class CareResultItemOnClickListener implements
		AdapterView.OnItemClickListener {
	private Context context;

	public CareResultItemOnClickListener(Context context) {
		this.context = context;
	}

	@Override
	public void onItemClick(AdapterView<?> adpater, View view, int postion,
			long id) {
		CareAdvice careAdvice = (CareAdvice) adpater.getItemAtPosition(postion);
		Intent intent = new Intent();
		intent.setClass(context, CareAdviceActivity.class);
		Bundle bundle = new Bundle();
		bundle.putString("name", careAdvice.getSubject());
		bundle.putString("content", careAdvice.getContentDetail());
		//判断不为空后继续
		if (careAdvice.getContentDetail() != null && careAdvice.getContentDetail()!="") {
			intent.putExtras(bundle);
			context.startActivity(intent);
		}
	}

}
