package com.feipinjia.adapter;

import java.text.DecimalFormat;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.feipinjia.model.Care;
import com.feipinjia.model.CareComment;
import com.feipinjia.activity.R;

public class CareCommentListAdapter extends  ArrayAdapter<CareComment> {
	public CareCommentListAdapter(Context context, int resource,
			List<CareComment> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public CareCommentListAdapter(Context context, int resource) {
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
		CareComment comment=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.care_comment_item,
					null);
			((TextView) convertView.findViewById(R.id.cm_username)).setText(comment.getUsername());
			((TextView) convertView.findViewById(R.id.cm_comment)).setText(comment.getContent());
			//((TextView) convertView.findViewById(R.id.cm_createDate)).setText("评价："+total+" / "+str);
		return convertView;
	}

}
