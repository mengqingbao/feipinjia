package com.feipinjia.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.feipinjia.model.CareItem;
import com.feipinjia.activity.R;

public class CareDetailListAdapter extends  ArrayAdapter<CareItem> {
	public CareDetailListAdapter(Context context, int resource,
			List<CareItem> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public CareDetailListAdapter(Context context, int resource) {
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
		CareItem item=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.care_detail_item,
					null);
			((TextView) convertView.findViewById(R.id.cm_name)).setText(item.getTitle());
			((RadioButton) convertView.findViewById(R.id.radio0)).setText(item.getCondition0());
			((RadioButton) convertView.findViewById(R.id.radio1)).setText(item.getCondition1());
			((RadioButton) convertView.findViewById(R.id.radio2)).setText(item.getCondition2());
			//((TextView) convertView.findViewById(R.id.cm_createDate)).setText("评价："+total+" / "+str);
		return convertView;
	}

}
