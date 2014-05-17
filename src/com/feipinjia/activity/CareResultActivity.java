package com.feipinjia.activity;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.feipinjia.adapter.CareResultListAdapter;
import com.feipinjia.listener.CareResultItemOnClickListener;
import com.feipinjia.model.CareAdvice;
import com.feipinjia.model.CareResult;
import com.feipinjia.persistence.CareResultStroe;
import com.feipinjia.activity.R;

public class CareResultActivity extends Activity  implements OnClickListener {

	private ListView listView;
	
	private Button cancelBtn;
	
	private CareResultListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_result);
		cancelBtn=(Button) this.findViewById(R.id.care_result_btn_back);
		cancelBtn.setOnClickListener(this);
		initData();
	}
	
	public void initData(){
		listView=(ListView) this.findViewById(R.id.care_result_list_view);
		adapter=new CareResultListAdapter(this,R.layout.care_result_item);
		List<CareResult> careResults = CareResultStroe.getInstance().queryPageList(0, 10, this, 1);
		for(CareResult cr:careResults){
			CareAdvice cc=new CareAdvice();
			cc.setSubject(cr.getName());
			cc.setContent(cr.getContent());
			cc.setContentDetail(cr.getContentDetial());
			adapter.add(cc);
		}
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new CareResultItemOnClickListener(this));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.care_result_btn_back:
			this.finish();
			break;

		default:
			break;
		}
	}
}
