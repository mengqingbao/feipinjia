package com.feipinjia.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.feipinjia.model.InUser;
import com.feipinjia.model.StringVar;
import com.feipinjia.activity.R;

public class Fr3ListAdapter extends  ArrayAdapter<StringVar> {
	public Fr3ListAdapter(Context context, int resource,
			List<StringVar> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public Fr3ListAdapter(Context context, int resource) {
		super(context, resource);
		this.init(context, resource);
	}
	private void init(Context context, int resource) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}
	private LayoutInflater mInflater;

	@Override
	public View getView(int postion, View convertView, ViewGroup vg) {
			StringVar sv=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.in_frag3_list_item,
					null);
			((TextView) convertView.findViewById(R.id.frag3_list_tv1)).setText(sv.getStr1());;
			((TextView) convertView.findViewById(R.id.frag3_list_tv2)).setText(sv.getStr2());;
		return convertView;
	}

}
