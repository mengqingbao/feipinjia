package com.feipinjia.activity;

import java.util.List;

import com.feipinjia.adapter.CareDetailListAdapter;
import com.feipinjia.model.CareCondition;
import com.feipinjia.model.CareItem;
import com.feipinjia.persistence.CareConditionStroe;
import com.feipinjia.activity.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class CareDetailActivity extends Activity  implements OnClickListener{

	private Button backBtn;
	private RelativeLayout rl;
	private ListView listView;
	private CareDetailListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_detail);
		backBtn=(Button) this.findViewById(R.id.care_detail_btn_back);
		backBtn.setOnClickListener(this);
		rl=(RelativeLayout) this.findViewById(R.id.care_detail_rl_layout2);
		rl.setOnClickListener(this);
		listView=(ListView) this.findViewById(R.id.care_detail_list_view);
		adapter=new CareDetailListAdapter(this,R.layout.care_detail_item);
		initData();
	}
	
	public void initData(){
		List<CareCondition> careConditions=CareConditionStroe.getInstance().queryPageList(0, 10, this, 1);
		for(CareCondition cc:careConditions){
			CareItem item=new CareItem();
			item.setTitle(cc.getName());
			String[] cons=cc.getValue().split(";");
			for(int i=0;i<cons.length;i++){
				switch (i) {
				case 0:
					item.setCondition0(cons[i]);
					break;
				case 1:
					item.setCondition1(cons[i]);				
					break;
				case 2:
					item.setCondition2(cons[i]);
					break;
				default:
					break;
				}
			}
			adapter.add(item);
		}
		listView.setAdapter(adapter);
	}
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.care_detail_btn_back:
			this.finish();
			break;
		case R.id.care_detail_rl_layout2:
			Intent intent=new Intent(this,CareCommentActivity.class);
			this.startActivity(intent);
			break;

		default:
			break;
		}
	}
}
