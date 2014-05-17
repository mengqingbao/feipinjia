package com.feipinjia.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.feipinjia.adapter.CareCommentListAdapter;
import com.feipinjia.model.CareComment;
import com.feipinjia.activity.R;

public class CareCommentFragment extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		 View view = inflater.inflate(R.layout.fragment_care_comment, container, false);

		 ListView listView = (ListView) view.findViewById(R.id.care_comment_content);
         
		 CareCommentListAdapter adapter=new CareCommentListAdapter(this.getActivity(),R.layout.care_comment_item);
		for(int i=0;i<5;i++){
			CareComment cc=new CareComment();
			cc.setUsername("上海网友：");
			cc.setContent("很好，一级就好了");
			adapter.add(cc);
		}
		listView.setAdapter(adapter);
		 return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}



}
