package com.feipinjia.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.feipinjia.model.Config;
import com.feipinjia.model.News;
import com.feipinjia.activity.R;

public class Fr4ListAdapter extends  ArrayAdapter<News> {
	public Fr4ListAdapter(Context context, int resource,
			List<News> news) {
		super(context, resource, news);
		this.init(context, resource);
	}
	public Fr4ListAdapter(Context context, int resource) {
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
		News news=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.in_frag4_list_item,
					null);
			((TextView) convertView.findViewById(R.id.frag4_list_tv1)).setText(news.getTitle());
			((TextView) convertView.findViewById(R.id.frag4_list_tv2)).setText(news.getDesc());
			((TextView) convertView.findViewById(R.id.frag4_list_tv3)).setText(news.getCommits()+"条跟帖");;

		return convertView;
	}

}
