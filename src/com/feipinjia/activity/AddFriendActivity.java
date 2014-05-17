package com.feipinjia.activity;

import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.XMPPException;

import com.feipinjia.util.XmppTool;

import com.feipinjia.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddFriendActivity extends Activity implements OnClickListener{

	private String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_friend);

		username = getIntent().getStringExtra("USERID");
		TextView tv = (TextView) this.findViewById(R.id.add_f_username);
		tv.setText(username);
		Button btn=(Button) this.findViewById(R.id.add_f_submit);
		btn.setOnClickListener(this);

	}
	public void cancel(View v){
		this.finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_friend, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Roster roster = XmppTool.getConnection().getRoster();
		try {
			roster.createEntry(username, null, new String[] { "friends" });
		} catch (XMPPException e) {
			Toast.makeText(this, "��Ӻ���ʧ��", Toast.LENGTH_SHORT).show();
		}
		Toast.makeText(this, "��Ӻ��ѳɹ�", Toast.LENGTH_SHORT).show();
	}
}
