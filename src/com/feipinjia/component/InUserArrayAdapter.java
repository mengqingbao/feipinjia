package com.feipinjia.component;

import java.util.List;

import com.feipinjia.model.InUser;

import com.feipinjia.activity.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InUserArrayAdapter extends ArrayAdapter<InUser> {

	public InUserArrayAdapter(Context context, int resource,
			List<InUser> objects) {
		super(context, resource, objects);
		this.init(context, resource);
	}
	public InUserArrayAdapter(Context context, int resource) {
		super(context, resource);
		this.init(context, resource);
	}

	LayoutInflater mInflater;

	private void init(Context context, int resource) {
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		InUser item = getItem(position);
		View view = mInflater.inflate(R.layout.in_user_list_item, parent, false);
		TextView label = (TextView) view.findViewById(R.id.u_nick);
		label.setText(item.getNick());
		ImageView icon=(ImageView)view.findViewById(R.id.u_avatar);
		return view;
	}
	
}
