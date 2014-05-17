package com.feipinjia.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.feipinjia.activity.CareCommentActivity;
import com.feipinjia.activity.CareDetailActivity;

public class Fr2ItemOnClickListener implements  AdapterView.OnItemClickListener{
	private Context context;
	public Fr2ItemOnClickListener(Context context){
		this.context=context;
	}
	@Override
	public void onItemClick(AdapterView<?> adpater, View view, int postion, long id) {
		Intent intent = new Intent();
		intent.setClass(context, CareDetailActivity.class);
		Bundle bundle = new Bundle();
		/*bundle.putString("friendId", user.getUserId());
		bundle.putString("friendNick", user.getNick());*/
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
