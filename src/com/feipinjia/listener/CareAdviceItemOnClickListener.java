package com.feipinjia.listener;

import com.feipinjia.activity.CareAdviceActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

public class CareAdviceItemOnClickListener implements  AdapterView.OnItemClickListener{
	private Context context;
	public CareAdviceItemOnClickListener(Context context){
		this.context=context;
	}
	@Override
	public void onItemClick(AdapterView<?> adpater, View view, int postion, long id) {
		Intent intent = new Intent();
		intent.setClass(context, CareAdviceActivity.class);
		Bundle bundle = new Bundle();
		/*bundle.putString("friendId", user.getUserId());
		bundle.putString("friendNick", user.getNick());*/
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
