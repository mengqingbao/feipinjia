package com.feipinjia.activity;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Presence;

import com.feipinjia.util.XmppTool;

import com.feipinjia.activity.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class InChatLogin extends Activity implements OnClickListener {


	private EditText useridText, pwdText;
	private LinearLayout layout1, layout2;
	private LinearLayout voice_rcd_hint_loading, voice_rcd_hint_rcding,
	voice_rcd_hint_tooshort;

	@Override

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.in_chat_login);
		SharedPreferences sharedPref = InChatLogin.this
				.getSharedPreferences(
						getString(R.string.in_chat_store),
						Context.MODE_PRIVATE);
		String username=sharedPref.getString(getString(R.string.username_store_key), null);
		String password=sharedPref.getString(getString(R.string.password_store_key), null);
		//获得用户名密码
		this.useridText = (EditText) findViewById(R.id.formlogin_userid);
		this.pwdText = (EditText) findViewById(R.id.formlogin_pwd);
		if(username!=null)
		useridText.setText(username);
		if(password!=null){
			pwdText.setText(password);
		}
		// 登陆等待界面
		this.layout1 = (LinearLayout) findViewById(R.id.formlogin_layout1);
		//登陆界面

		this.layout2 = (LinearLayout) findViewById(R.id.formlogin_layout2);

		Button btsave = (Button) findViewById(R.id.formlogin_btsubmit);
		btsave.setOnClickListener(this);
		Button btcancel = (Button) findViewById(R.id.formlogin_btcancel);
		btcancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.formlogin_btsubmit:
			final String USERID = this.useridText.getText().toString();
			final String PWD = this.pwdText.getText().toString();

			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					handler.sendEmptyMessage(1);
					/*	try {
						XmppTool.getConnection().login(USERID, PWD);
						Presence presence = new Presence(
								Presence.Type.available);
						XmppTool.getConnection().sendPacket(presence);*/

						Intent intent = new Intent();
						intent.setClass(InChatLogin.this, MainTabActivity.class);
						intent.putExtra("USERID", USERID);

						SharedPreferences sharedPref = InChatLogin.this
								.getSharedPreferences(
										getString(R.string.in_chat_store),
										Context.MODE_PRIVATE);
						
						SharedPreferences.Editor editor = sharedPref.edit();
						editor.putString(
								getString(R.string.username_store_key), USERID);
						editor.putString(getString(R.string.password_store_key), PWD);
						editor.commit();
						InChatLogin.this.startActivity(intent);
						
						InChatLogin.this.finish();
					/*} catch (XMPPException e) {
						XmppTool.closeConnection();

						handler.sendEmptyMessage(2);
					}*/

				}
			});
			t.start();
			break;
		case R.id.formlogin_btcancel:
			finish();
			break;
		}
	}


	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) {

			if (msg.what == 1) {
				layout1.setVisibility(View.VISIBLE);
				layout2.setVisibility(View.GONE);
				

				
			} else if (msg.what == 2) {
				layout1.setVisibility(View.GONE);
				layout2.setVisibility(View.VISIBLE);
				Toast.makeText(InChatLogin.this, "��¼ʧ�ܣ�", Toast.LENGTH_SHORT)
						.show();
			}
		};
	};
}