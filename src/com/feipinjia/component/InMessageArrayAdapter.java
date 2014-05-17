package com.feipinjia.component;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.feipinjia.model.InMessage;
import com.feipinjia.util.DateUtil;
import com.feipinjia.util.FaceConversionUtil;

import com.feipinjia.activity.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class InMessageArrayAdapter extends BaseAdapter {

	private List<InMessage> coll;
	private LayoutInflater mInflater;
	private Context context;
	private MediaPlayer mMediaPlayer = new MediaPlayer();

	public InMessageArrayAdapter(Context context, List<InMessage> coll) {
		if (coll != null) {
			this.coll = coll;
		} else {
			coll = new ArrayList<InMessage>();
		}
		mInflater = LayoutInflater.from(context);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final InMessage item = coll.get(position);
		System.out.println(item.getCreateDate());
		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (item.isType()) {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_left, null);
			} else {
				convertView = mInflater.inflate(
						R.layout.chatting_item_msg_text_right, null);
			}
			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView
					.findViewById(R.id.tv_sendtime);
			viewHolder.tvUserName = (TextView) convertView
					.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_chatcontent);

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		viewHolder.tvSendTime.setText(DateUtil.toString(item.getCreateDate()));
		// viewHolder.tvUserName.setText(item.getInUser().getNick());
		if (!TextUtils.isEmpty(item.getContent())) {
			if (item.getContent().contains(".amr")) {
				viewHolder.tvContent.setText("");
				viewHolder.tvContent.setCompoundDrawablesWithIntrinsicBounds(0,
						0, R.drawable.chatto_voice_playing, 0);
			} else if(item.getContent().contains(".jpg")){
				System.out.println("*&*&*&*&*&");
				viewHolder.tvContent.setText(Html.fromHtml("<img src=\""+item.getContent()+"\" />",imgGetter,null));
			}else{
				SpannableString spannableString = FaceConversionUtil
						.getInstace().getExpressionString(context,
								item.getContent());
				viewHolder.tvContent.setText(spannableString);
			}
		}
		viewHolder.tvContent.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				if (item.getContent().contains(".amr")) {
					playMusic(android.os.Environment
							.getExternalStorageDirectory()
							+ "/"
							+ item.getContent());
				}
			}
		});
		return convertView;
	}

	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
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

	private void playMusic(String name) {
		try {
			if (mMediaPlayer.isPlaying()) {
				mMediaPlayer.stop();
			}
			mMediaPlayer.reset();
			mMediaPlayer.setDataSource(name);
			mMediaPlayer.prepare();
			mMediaPlayer.start();
			mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 图盘显示处理
	private ImageGetter imgGetter = new Html.ImageGetter() {
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
/*			URL url;
			try {
				url = new URL(source);
				drawable = Drawable.createFromStream(url.openStream(), "");
			} catch (Exception e) {
				return null;
			}*/
			drawable=Drawable.createFromPath(source);
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

}
