package com.feipinjia.activity;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout.LayoutParams;

import com.feipinjia.adapter.Fr3ListAdapter;
import com.feipinjia.adapter.Fr4ListAdapter;
import com.feipinjia.adapter.Fr5ListAdapter;
import com.feipinjia.adapter.ViewPagerAdapter;
import com.feipinjia.model.News;
import com.feipinjia.persistence.NewsStroe;
import com.feipinjia.activity.R;

public class FragmentPage4 extends Fragment{
	private ViewPager pa;
	private List<View> views;
	private ArrayList<ImageView> pointViews;
	/** 游标显示布局 */
	private LinearLayout layout_point;
	
	private ListView listView;
	private Fr4ListAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 View view = inflater.inflate(R.layout.fragment_4, container, false);
		 initPaper(view);
		 Init_Point(view);
		 
		 //别表
		 listView = (ListView) view.findViewById(R.id.fragment_4_list);
		 
		 List<News> list = NewsStroe.getInstance().queryPageList(0, 10, this.getActivity(),1);
		 adapter= new Fr4ListAdapter(this.getActivity(),R.layout.in_frag4_list_item,list);
         listView.setAdapter(adapter);
         return view;	
	}	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
	}
	
	private void initPaper(View view){
		 pa= (ViewPager)view.findViewById(R.id.vp_contains);
		 views = new ArrayList<View>();
		 //GridView gridView = (GridView) findViewById(R.layout.in_chat_activity_face);///(GridView)this.getLayoutInflater().inflate(R.layout.in_chat_activity_face,null);
		 for(int i=0;i<3;i++){
			 ImageView imageView = new ImageView(this.getActivity());;
			 if(i==0)
			 imageView.setBackgroundResource(R.drawable.banner1);
			 if(i==1)
				 imageView.setBackgroundResource(R.drawable.banner2);
			 if(i==2)
				 imageView.setBackgroundResource(R.drawable.banner3);
			 views.add(imageView);
		 }
		 pa.setAdapter(new ViewPagerAdapter(views));
		 pa.setOnPageChangeListener(new OnPageChangeListener() {
				@Override
				public void onPageSelected(int position) {
					draw_Point(position);
					pa.setCurrentItem(position);
				}
				@Override
				public void onPageScrolled(int arg0, float arg1, int arg2) {
				}
				@Override
				public void onPageScrollStateChanged(int arg0) {
				}
			});
		 
		
	}
	
	private void Init_Point(View view) {
		layout_point = (LinearLayout) view.findViewById(R.id.iv_image);
		pointViews = new ArrayList<ImageView>();
		ImageView imageView;
		System.out.println(views.size()+"init_point");
		for (int i = 0; i < views.size(); i++) {
			imageView = new ImageView(this.getActivity());
			imageView.setBackgroundResource(R.drawable.d1);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT));
			layoutParams.leftMargin = 10;
			layoutParams.rightMargin = 10;
			layoutParams.width = 8;
			layoutParams.height = 8;
			layout_point.addView(imageView, layoutParams);
			if (i == 0) {
				imageView.setBackgroundResource(R.drawable.d2);
			}else{
				imageView.setBackgroundResource(R.drawable.d1);
			}
			pointViews.add(imageView);

		}
	}
	
	//redraw point when pages change.
	public void draw_Point(int index) {
		for (int i = 0; i < pointViews.size(); i++) {
			if (index == i) {
				pointViews.get(i).setBackgroundResource(R.drawable.d2);
			} else {
				pointViews.get(i).setBackgroundResource(R.drawable.d1);
			}
		}
	}

}