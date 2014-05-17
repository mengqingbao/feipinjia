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

import com.feipinjia.adapter.CareCommentListAdapter;
import com.feipinjia.model.CareComment;
import com.feipinjia.activity.R;

public class CareCommentActivity extends Activity  implements OnClickListener {

	private ListView listView;
	
	private Button cancelBtn;
	
	private CareCommentListAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.care_comment);
		cancelBtn=(Button) this.findViewById(R.id.care_comment_btn_back);
		cancelBtn.setOnClickListener(this);
		initTabHOst();
		initData();
	}
	
	public void initData(){
		listView=(ListView) this.findViewById(R.id.listView1);
		adapter=new CareCommentListAdapter(this,R.layout.care_comment_item);
		for(int i=0;i<5;i++){
			CareComment cc=new CareComment();
			cc.setUsername("上海网友：");
			cc.setContent("很好，一级就好了");
			adapter.add(cc);
		}
		listView.setAdapter(adapter);
		
	}
	public void initTabHOst(){
		 final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);  
	        // 如果没有继承TabActivity时，通过该种方法加载启动tabHost  
	        tabHost.setup();  
	        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("好评(374)").setContent(  
	                R.id.tab1));  
	  
	        tabHost.addTab(tabHost.newTabSpec("tab3").setIndicator("一般(4)")  
	                .setContent(R.id.tab1));  
	  
	        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("差评(2)")  
	                .setContent(R.id.tab1)); 
	        tabHost.setOnClickListener(this);
	        TabWidget tabWidget = tabHost.getTabWidget();
	        for (int i =0; i < tabWidget.getChildCount(); i++) {  
	            tabWidget.getChildAt(i).getLayoutParams().height = 50;  
	            //tabWidget.getChildAt(i).getLayoutParams().width = 65;
	            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
	            tv.setTextSize(20);
	            tv.setTextColor(this.getResources().getColorStateList(android.R.color.white));
	            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) tv.getLayoutParams(); 
	            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, 0); //取消文字底边对齐 
	            params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE); 
	        }
	        updateTabBackground(tabHost);
	      //选择时背景更改。
	        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
	            @Override
	            public void onTabChanged(String tabId) {
	                updateTabBackground(tabHost);
	            }
	        });
	}
	 private void updateTabBackground(final TabHost tabHost) {
	        for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {
	            View vvv = tabHost.getTabWidget().getChildAt(i);
	            if (tabHost.getCurrentTab() == i) {
	                //选中后的背景
	                vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.u37_normal));
	            } else {
	                //非选择的背景
	                vvv.setBackgroundDrawable(getResources().getDrawable(R.drawable.u35_normal));
	            }
	        }
	    }
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.care_comment_btn_back:
			this.finish();
			break;

		default:
			break;
		}
	}
}
