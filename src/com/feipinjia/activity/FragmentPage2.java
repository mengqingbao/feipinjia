package com.feipinjia.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.feipinjia.adapter.Fr2ListAdapter;
import com.feipinjia.listener.Fr2ItemOnClickListener;
import com.feipinjia.listener.Fr3ItemOnClickListener;
import com.feipinjia.model.Care;
import com.feipinjia.persistence.CareStore;
import com.feipinjia.activity.R;

public class FragmentPage2 extends Fragment{
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_2, container, false);
		
		return view;
	}
}
