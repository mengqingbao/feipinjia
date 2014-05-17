package com.feipinjia.layout;

import com.feipinjia.activity.FragmentPage1;
import com.feipinjia.activity.FragmentPage2;
import com.feipinjia.activity.FragmentPage3;
import com.feipinjia.activity.FragmentPage4;
import com.feipinjia.activity.FragmentPage5;
import com.feipinjia.activity.R;

import android.content.Context;
import android.support.v4.app.FragmentTabHost;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class CareCommentLayout extends RelativeLayout  {
	
	private Context context;

	public CareCommentLayout(Context context) {
		super(context);
		this.context=context;
		
	}
	public CareCommentLayout(Context context, AttributeSet attrs){
		super(context, attrs);
		this.context=context;
	}
	public CareCommentLayout(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		this.context=context;
	}
	
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		 init();
	}
	public void init(){
		
		/*
		tabHost.setup();
		TabSpec tabSpec = tabHost.newTabSpec("Tab1").setIndicator("好评",this.getResources().getDrawable(R.drawable.d1));
		tabSpec.setContent(R.id);
		tabHost.addTab(tabSpec);
		TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("中评",this.getResources().getDrawable(R.drawable.d1));
		tabSpec.setContent(R.id.tab2);
		tabHost.addTab(tabSpec2);
		TabSpec tabSpec3 = tabHost.newTabSpec("Tab3").setIndicator("差评",this.getResources().getDrawable(R.drawable.d1));
		tabSpec.setContent(R.id.tab3);
		tabHost.addTab(tabSpec3);
		tabHost.setCurrentTab(0);*/
		
	}
	
	private View getTabItemView(String str){
		View view = this.findViewById(R.layout.tab_item_view);
		
		//ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		//imageView.setImageResource(mImageViewArray[index]);
		
		TextView textView = (TextView) view.findViewById(R.id.textview);		
		textView.setText(str);
	
		return view;
	}

}
