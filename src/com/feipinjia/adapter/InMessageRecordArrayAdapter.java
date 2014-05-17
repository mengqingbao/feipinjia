package com.feipinjia.adapter;

import java.util.ArrayList;
import java.util.List;

import com.feipinjia.model.InMessage;
import com.feipinjia.util.DateUtil;
import com.feipinjia.util.FaceConversionUtil;
import com.feipinjia.util.StringUtils;

import com.feipinjia.activity.R;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InMessageRecordArrayAdapter extends BaseAdapter {

	private List<InMessage> coll;// 初始化数据
	private LayoutInflater mInflater;
	private Context context;
	public InMessageRecordArrayAdapter(Context context,List<InMessage> coll){
		if(coll!=null){
		this.coll=coll;
		}else{
			coll=new ArrayList<InMessage>();
		}
		mInflater = LayoutInflater.from(context);
		this.context=context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		InMessage item = coll.get(position);
		System.out.println(item.getCreateDate());
		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (item.isType()) {
				convertView = mInflater.inflate(
						R.layout.in_message_record_list_item, null);
			} else {
				convertView = mInflater.inflate(
						R.layout.in_message_record_list_item, null);
			}
			viewHolder = new ViewHolder();
			viewHolder.tvNick = (TextView) convertView
					.findViewById(R.id.mr_nick);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.mr_TextView01);
			viewHolder.tvDate = (TextView) convertView
					.findViewById(R.id.mr_TextView02);
			viewHolder.tvStatu = (TextView) convertView
					.findViewById(R.id.mr_TextView03);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		
		viewHolder.tvDate.setText(DateUtil.getDistanceTime(item.getCreateDate().getTime()));
		//viewHolder.tvUserName.setText(item.getInUser().getNick());
		if(!TextUtils.isEmpty(item.getContent())){
		//SpannableString spannableString = FaceConversionUtil.getInstace().getExpressionString(context, item.getContent());
			String str=item.getContent();
			if(str.length()>15){
				str=str.substring(0, 14)+"...";
			}
			if(item.isType()){
				str="诊："+str;
			}else{
				str="问："+str;
			}
		viewHolder.tvContent.setText(str);
		}else{
			viewHolder.tvContent.setText(item.getContent());
		}
		viewHolder.tvNick.setText(item.getFriendNick());
		if (item.isType()) {
			if (item.getCommentStatus() == 1) {
				viewHolder.tvStatu.setText("有效");
			} else if (item.getCommentStatus() == 0) {
				viewHolder.tvStatu.setText("无效");
			}
		}else{
			viewHolder.tvStatu.setText("");
		}
		return convertView;
	}
	
	static class ViewHolder {
		public TextView tvNick;
		public TextView tvContent;
		public TextView tvDate;
		public TextView tvStatu;
	}

	@Override
	public int getCount() {
		return coll.size();
	}

	@Override
	public Object getItem(int position) {
		return coll.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
}
