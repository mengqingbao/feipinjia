package com.feipinjia.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.feipinjia.model.Config;
import com.feipinjia.activity.R;

public class Fr5ListAdapter extends  ArrayAdapter<Config> {
	public Fr5ListAdapter(Context context, int resource,
			List<Config> cfgs) {
		super(context, resource, cfgs);
		this.init(context, resource);
	}
	public Fr5ListAdapter(Context context, int resource) {
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
			Config sv=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.in_frag5_list_item,
					null);
			((TextView) convertView.findViewById(R.id.frag5_list_tv1)).setText(sv.getTitle());;
			((TextView) convertView.findViewById(R.id.frag5_list_tv2)).setText(sv.getDesc());;
		return convertView;
	}

}
