package com.feipinjia.common;

import com.feipinjia.activity.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

public class BaseActivity extends Activity implements OnClickListener {

	private Button backBtn;
	private RelativeLayout rl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_detail);
		backBtn=(Button) this.findViewById(R.id.care_detail_btn_back);
		backBtn.setOnClickListener(this);
		rl=(RelativeLayout) this.findViewById(R.id.care_detail_rl_layout2);
		rl.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

}
