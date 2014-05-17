package com.feipinjia.listener;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.feipinjia.activity.CareResultActivity;
import com.feipinjia.activity.InChatActivity;
import com.feipinjia.model.InMessage;

public class Fr1ItemOnClickListener implements  AdapterView.OnItemClickListener{
	private Context context;
	public Fr1ItemOnClickListener(Context context){
		this.context=context;
	}
	@Override
	public void onItemClick(AdapterView<?> adpater, View view, int postion, long id) {
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		InMessage message = (InMessage) adpater.getItemAtPosition(postion);
		System.out.println(message.getAction());
		System.out.println(message.getFriendNick());
		switch (message.getAction()) {
		case 1:
			intent.setClass(context, InChatActivity.class);
			bundle.putString("docId", message.getFriendId());
			bundle.putString("docNick", message.getFriendNick());
			break;
		case 2:
			intent.setClass(context, CareResultActivity.class);
			break;
		case 3:
			intent.setClass(context, InChatActivity.class);
			bundle.putString("docId", message.getFriendId());
			bundle.putString("docNick", message.getFriendNick());
			break;
		default:
			intent.setClass(context, CareResultActivity.class);
			break;
		}
		intent.putExtras(bundle);
		context.startActivity(intent);
	}

}
