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
import com.feipinjia.activity.R;

public class Fr2ListAdapter extends  ArrayAdapter<Care> {
	public Fr2ListAdapter(Context context, int resource,
			List<Care> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public Fr2ListAdapter(Context context, int resource) {
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
			Care car=this.getItem(postion);
			convertView = this.mInflater.inflate(R.layout.in_frag2_list_item,
					null);
			((TextView) convertView.findViewById(R.id.in_fr2_tv1)).setText(car.getTitle());
			((TextView) convertView.findViewById(R.id.in_fr2_tv2)).setText(car.getDraw());
			int total=car.getGood()+car.getNornal()+car.getBad();
			String str=null;
			if(total!=0){
			 DecimalFormat fmt = new DecimalFormat(".#%");
			 float rate=(float)car.getGood()/(float)total;
			 str=fmt.format(rate);
			}
			((TextView) convertView.findViewById(R.id.in_fr2_tv3)).setText("评价："+total+" / "+str);
		return convertView;
	}

}
