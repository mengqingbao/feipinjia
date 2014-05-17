package com.feipinjia.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.feipinjia.adapter.CareAdviceListAdapter;
import com.feipinjia.model.CareAdvice;
import com.feipinjia.activity.R;

public class CareAdviceActivity extends Activity  implements OnClickListener {

	private ListView listView;
	
	private Button cancelBtn;
	
	private TextView subject;
	
	private CareAdviceListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_advice);
		subject=(TextView) this.findViewById(R.id.subject);
		cancelBtn=(Button) this.findViewById(R.id.care_advice_btn_back);
		cancelBtn.setOnClickListener(this);
		initData();
	}
	
	public void initData(){
		listView=(ListView) this.findViewById(R.id.care_advice_list_view);
		adapter=new CareAdviceListAdapter(this,R.layout.care_advice_item);
		String Title=getIntent().getStringExtra("name");
		subject.setText(Title);
		
		String content=getIntent().getStringExtra("content");
		System.out.print(content);
		if(content==null){
			return;
		}
		String[] strs=content.split(";");
		for(String str:strs){
			if(str==null){continue;}
			String[] vas=str.split(":");
			if(vas.length<2){continue;}
			CareAdvice cc=new CareAdvice();
			cc.setSubject(vas[0]);
			cc.setContent(vas[1]);
			adapter.add(cc);
		}
		listView.setAdapter(adapter);
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.care_advice_btn_back:
			this.finish();
			break;

		default:
			break;
		}
	}
}
