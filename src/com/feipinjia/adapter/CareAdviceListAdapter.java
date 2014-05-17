package com.feipinjia.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.feipinjia.model.CareAdvice;
import com.feipinjia.model.CareComment;
import com.feipinjia.activity.R;

public class CareAdviceListAdapter extends  ArrayAdapter<CareAdvice> {
	public CareAdviceListAdapter(Context context, int resource,
			List<CareAdvice> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public CareAdviceListAdapter(Context context, int resource) {
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
		CareAdvice careAdvice=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.care_advice_item,
					null);
			((TextView) convertView.findViewById(R.id.care_adivce_tv1)).setText(careAdvice.getSubject());
			((TextView) convertView.findViewById(R.id.care_adivce_tv2)).setText(careAdvice.getContent());
			//((TextView) convertView.findViewById(R.id.cm_createDate)).setText("评价："+total+" / "+str);
		return convertView;
	}

}
