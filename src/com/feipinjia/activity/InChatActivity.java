package com.feipinjia.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.feipinjia.component.InMessageArrayAdapter;
import com.feipinjia.model.InMessage;
import com.feipinjia.persistence.InMessageStore;
import com.feipinjia.util.SoundMeter;
import com.feipinjia.util.XmppTool;
import com.feipinjia.activity.R;

public class InChatActivity extends Activity implements OnClickListener {

	private ListView listView;
	private InMessageArrayAdapter iadapter;
	private View rl_voice, rl_input;
	private ImageView voiceValueImage;
	private View rcChat_popup;
	//等待，记录，太短界面
	private LinearLayout voice_rcd_hint_loading, voice_rcd_hint_rcding,
			voice_rcd_hint_tooshort;
	private LinearLayout del_re;
	private boolean isVocie = false;
	private int flag = 1;
	private Handler mHandler = new Handler();
	private SoundMeter mSensor;
	private boolean isShosrt = false;
	private ImageView img1, sc_img1;
	// 录音开始结束时间。
	private long startVoiceT, endVoiceT;
	// 拍照弹出框
	private PopupWindow popw;
	// 录音文件名称
	private String voiceFileName;
	// 点
	private ChatManager cm;
	private Chat chat;
	private String friendId;
	private String friendNick;
	private String userId;
	private String nick;
	private List<InMessage> msgs;
	private Button sendBtn;
	private Button fotoBtn, voictBtn, msgBtn, takeFotoBtn, fotoSetBtn,btn3;
	private TextView voiceTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.in_chat_activity);

		listView = (ListView) this.findViewById(R.id.listview);
		String defaultValue = getResources().getString(
				R.string.username_store_key);
		friendId = getIntent().getStringExtra("docId");
		friendNick = getIntent().getStringExtra("docNick");
		SharedPreferences sharedPref = this.getSharedPreferences(
				getString(R.string.in_chat_store), Context.MODE_PRIVATE);
		userId = sharedPref.getString(getString(R.string.username_store_key),
				"");
		nick = sharedPref.getString(getString(R.string.username_store_key), "");
		// get chat history data from db
		msgs = InMessageStore.getInstance().getMessages(userId, friendId, 0, 5,
				this);
		if (msgs == null) {
			msgs = new ArrayList<InMessage>();
		}
		iadapter = new InMessageArrayAdapter(this, msgs);
		listView.setAdapter(iadapter);
		//cm = XmppTool.getConnection().getChatManager();
		//chat = cm.createChat(friendId, null); //todo 阉割

		// 注册接受其
		IntentFilter intentFilter = new IntentFilter(
				"pro.chinasoft.activity.InChatActivity");
		registerReceiver(mReceiver, intentFilter);

		init();
		initRecordVoice();
	}

	private void init() {
		// this.findViewById(R.id.rl_bottom_more).setVisibility(View.GONE);
		TextView tv = (TextView) this.findViewById(R.id.in_chat_activity_title);
		tv.setText(friendId);
		sendBtn = (Button) this.findViewById(R.id.btn_send);
		sendBtn.setOnClickListener(this);
		this.fotoBtn = (Button) this.findViewById(R.id.foto_btn);
		this.voictBtn = (Button) this.findViewById(R.id.btn_voice);
		msgBtn = (Button) this.findViewById(R.id.msg_btn);
		fotoBtn.setOnClickListener(this);
		voictBtn.setOnClickListener(this);
		msgBtn.setOnClickListener(this);

		// 录音界面 输入页面
		rl_input = this.findViewById(R.id.rl_input);
		rl_voice = this.findViewById(R.id.rl_voice);
		voiceTextView = (TextView) this.findViewById(R.id.voiceTextView);
		voiceTextView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				// 按下语音录制按钮时返回false执行父类OnTouch
				return false;
			}
		});

	}

	// 录音页面
	public void initRecordVoice() {
		voiceValueImage = (ImageView) this.findViewById(R.id.voice_value_image);
		voice_rcd_hint_rcding = (LinearLayout) this
				.findViewById(R.id.voice_rcd_hint_rcding);
		voice_rcd_hint_loading = (LinearLayout) this
				.findViewById(R.id.voice_rcd_hint_loading);
		voice_rcd_hint_tooshort = (LinearLayout) this
				.findViewById(R.id.voice_rcd_hint_tooshort);
		del_re = (LinearLayout) this.findViewById(R.id.del_re);
		rcChat_popup = this.findViewById(R.id.rcChat_popup);
		mSensor = new SoundMeter();
		img1 = (ImageView) this.findViewById(R.id.img1);
		sc_img1 = (ImageView) this.findViewById(R.id.sc_img1);
	}

	@Override
	public void onBackPressed() {
		unregisterReceiver(mReceiver);
		InMessageStore.getInstance().close();
		this.finish();
	}

	// type:true message from yourself,false:msg from friend
	private void refresh(String content, boolean type) {
		InMessage msg = new InMessage();
		msg.setContent(content);
		msg.setCreateDate(new Date());
		msg.setType(type);
		msgs.add(msg);
		iadapter.notifyDataSetChanged();
		listView.setSelection(msgs.size() - 1);
	}

	BroadcastReceiver mReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String friendId = intent.getStringExtra("friendId");
			if (friendId.equals(friendId)) {
				String content = intent.getStringExtra("content");
				refresh(content, true);
			}
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_voice:  //enter voice model
			rl_voice.setVisibility(View.VISIBLE);
			rl_input.setVisibility(View.GONE);
			isVocie = true;
			break;
		case R.id.btn_send: //发送按钮
			sendMessage();
			break;
		case R.id.foto_btn: //弹出发送图片弹窗
			popMenu(fotoBtn);
			System.out.println("foto_btn");
			break;
		case R.id.msg_btn: //返回文字输入
			rl_voice.setVisibility(View.GONE);
			rl_input.setVisibility(View.VISIBLE);
			isVocie = false;
			break;
		case R.id.button1: //拍照
			popw.dismiss();
			takeFoto();
			break;
		case R.id.button2:  //选择照片
			popw.dismiss();
			fotoset();
			break;
		case R.id.button3:  //取消
			popw.dismiss();
			break;
		}
	}

	private void updateDisplay(double signalEMA) {

		switch ((int) signalEMA) {
		case 0:
		case 1:
			voiceValueImage.setImageResource(R.drawable.amp1);
			break;
		case 2:
		case 3:
			voiceValueImage.setImageResource(R.drawable.amp2);

			break;
		case 4:
		case 5:
			voiceValueImage.setImageResource(R.drawable.amp3);
			break;
		case 6:
		case 7:
			voiceValueImage.setImageResource(R.drawable.amp4);
			break;
		case 8:
		case 9:
			voiceValueImage.setImageResource(R.drawable.amp5);
			break;
		case 10:
		case 11:
			voiceValueImage.setImageResource(R.drawable.amp6);
			break;
		default:
			voiceValueImage.setImageResource(R.drawable.amp7);
			break;
		}
	}

	// send message
	public void sendMessage() {
		EditText text = (EditText) this.findViewById(R.id.et_sendmessage);
		String message = text.getText().toString();
		if (message == null || message == "") {
			return;
		}
		// refresh ui
		refresh(message, false);

		//doSaveAndSend(message);
		// clear the enter editText;
		text.setText("");
	}

	public void cancel(View v) {
		this.unregisterReceiver(mReceiver);
		this.finish();
	}

	// 按下语音录制按钮时
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (!Environment.getExternalStorageDirectory().exists()) {
			Toast.makeText(this, "No SDCard", Toast.LENGTH_LONG).show();
			return false;
		}

		if (isVocie) {
			int[] location = new int[2];
			voiceTextView.getLocationInWindow(location); // 获取在当前窗口内的绝对坐标
			int btn_rc_Y = location[1];
			int btn_rc_X = location[0];
			int[] del_location = new int[2];
			del_re.getLocationInWindow(del_location);
			int del_Y = del_location[1];
			int del_x = del_location[0];
			if (event.getAction() == MotionEvent.ACTION_DOWN && flag == 1) {
				if (!Environment.getExternalStorageDirectory().exists()) {
					Toast.makeText(this, "No SDCard", Toast.LENGTH_LONG).show();
					return false;
				}
				if (event.getY() > btn_rc_Y && event.getX() > btn_rc_X) {// 判断手势按下的位置是否是语音录制按钮的范围内
					System.out.println("3");
					voiceTextView
							.setBackgroundResource(R.drawable.voice_rcd_btn_pressed);
					rcChat_popup.setVisibility(View.VISIBLE);
					voice_rcd_hint_loading.setVisibility(View.VISIBLE);
					voice_rcd_hint_rcding.setVisibility(View.GONE);
					voice_rcd_hint_tooshort.setVisibility(View.GONE);
					mHandler.postDelayed(new Runnable() {
						public void run() {
							if (!isShosrt) {
								voice_rcd_hint_loading.setVisibility(View.GONE);
								voice_rcd_hint_rcding
										.setVisibility(View.VISIBLE);
							}
						}
					}, 300);
					img1.setVisibility(View.VISIBLE);
					del_re.setVisibility(View.GONE);
					startVoiceT = SystemClock.currentThreadTimeMillis();
					voiceFileName = startVoiceT + ".amr";
					start(voiceFileName);
					flag = 2;
				}
			} else if (event.getAction() == MotionEvent.ACTION_UP && flag == 2) {// 松开手势时执行录制完成
				voiceTextView
						.setBackgroundResource(R.drawable.voice_rcd_btn_nor);
				if (event.getY() >= del_Y
						&& event.getY() <= del_Y + del_re.getHeight()
						&& event.getX() >= del_x
						&& event.getX() <= del_x + del_re.getWidth()) {
					rcChat_popup.setVisibility(View.GONE);
					img1.setVisibility(View.VISIBLE);
					del_re.setVisibility(View.GONE);
					stop();
					flag = 1;
					File file = new File(
							android.os.Environment
									.getExternalStorageDirectory()
									+ "/"
									+ voiceFileName);
					if (file.exists()) {
						file.delete();
					}
				} else {

					voice_rcd_hint_rcding.setVisibility(View.GONE);
					stop();
					endVoiceT = SystemClock.currentThreadTimeMillis();
					flag = 1;
					int time = (int) ((endVoiceT - startVoiceT) / 1000);
					if (time < 1) {
						isShosrt = true;
						voice_rcd_hint_loading.setVisibility(View.GONE);
						voice_rcd_hint_rcding.setVisibility(View.GONE);
						voice_rcd_hint_tooshort.setVisibility(View.VISIBLE);
						mHandler.postDelayed(new Runnable() {
							public void run() {
								voice_rcd_hint_tooshort
										.setVisibility(View.GONE);
								rcChat_popup.setVisibility(View.GONE);
								isShosrt = false;
							}
						}, 500);
						return false;
					}
					rcChat_popup.setVisibility(View.GONE);
					// 保存发送文件
					refresh(voiceFileName, false);
					doSaveAndSend(voiceFileName);

				}
			}
			if (event.getY() < btn_rc_Y) {// 手势按下的位置不在语音录制按钮的范围内
				System.out.println("5");
				Animation mLitteAnimation = AnimationUtils.loadAnimation(this,
						R.anim.cancel_rc);
				Animation mBigAnimation = AnimationUtils.loadAnimation(this,
						R.anim.cancel_rc2);
				img1.setVisibility(View.GONE);
				del_re.setVisibility(View.VISIBLE);
				del_re.setBackgroundResource(R.drawable.voice_rcd_cancel_bg);
				if (event.getY() >= del_Y
						&& event.getY() <= del_Y + del_re.getHeight()
						&& event.getX() >= del_x
						&& event.getX() <= del_x + del_re.getWidth()) {
					del_re.setBackgroundResource(R.drawable.voice_rcd_cancel_bg_focused);
					sc_img1.startAnimation(mLitteAnimation);
					sc_img1.startAnimation(mBigAnimation);
				}
			} else {

				img1.setVisibility(View.VISIBLE);
				del_re.setVisibility(View.GONE);
				del_re.setBackgroundResource(0);
			}
		}
		return super.onTouchEvent(event);
	}

	private static final int POLL_INTERVAL = 300;

	private Runnable mSleepTask = new Runnable() {
		public void run() {
			stop();
		}
	};
	private Runnable mPollTask = new Runnable() {
		public void run() {
			double amp = mSensor.getAmplitude();
			updateDisplay(amp);
			mHandler.postDelayed(mPollTask, POLL_INTERVAL);

		}
	};

	private void start(String name) {
		mSensor.start(name);
		mHandler.postDelayed(mPollTask, POLL_INTERVAL);
	}

	private void stop() {
		mHandler.removeCallbacks(mSleepTask);
		mHandler.removeCallbacks(mPollTask);
		mSensor.stop();
		voiceValueImage.setImageResource(R.drawable.amp1);
	}

	public void doSaveAndSend(String message) {
		// save the message comes from friends
		InMessageStore.getInstance().saveOrUpdate(userId, friendId, friendNick,
				message, false, nick, this);
		try {
			chat.sendMessage(message);
		} catch (XMPPException e) {
			System.out.println(e.getMessage() + "exception");
		}
	}

	public void popMenu(View parent) {
		if (popw == null) {
			LayoutInflater layoutInflater = (LayoutInflater) this
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View popwv = layoutInflater.inflate(
					R.layout.in_chat_activity_popmenu, null);
			takeFotoBtn = (Button) popwv.findViewById(R.id.button1);
			fotoSetBtn = (Button) popwv.findViewById(R.id.button2);
			takeFotoBtn.setOnClickListener(this);
			fotoSetBtn.setOnClickListener(this);
			btn3=(Button) popwv.findViewById(R.id.button3);
			btn3.setOnClickListener(this);
			int with=this.getWindowManager().getDefaultDisplay().getWidth();
			popw = new PopupWindow(popwv, with-10, 180);
		}
		popw.setFocusable(true);
		popw.setOutsideTouchable(true);
		popw.setAnimationStyle(R.style.inchatpopup); // 弹出效果样式
		popw.setBackgroundDrawable(new BitmapDrawable());
		WindowManager wm = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		popw.showAtLocation(fotoSetBtn, Gravity.BOTTOM, 0, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 处理结果
		if (resultCode == 0) {
			return;
		}
		if (data == null) {
			return;
		}
		if (requestCode == PHOTO_GRAPH) {
			Bundle extras = data.getExtras();
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				fileName = new SimpleDateFormat("yyyyMMdd_hhmmss")
						.format(new Date()) + ".jpg";
				File dir = new File("/sdcard/gqtcm");
				if (!dir.exists()) {
					dir.mkdir();
				}
				fileName = "/sdcard/gqtcm/" + fileName;
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(fileName);
					photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
				} catch (FileNotFoundException e) {
					Log.d("gqtcm", "save jpg error: "+e.getMessage());
				} finally {
					try {
						if (fos != null) {
							fos.flush();
							fos.close();
						}
					} catch (IOException e) {
					}
				}
				this.refresh(fileName, false);
				//doSaveAndSend(fileName);
			}
			
		}
		if (requestCode == PHOTO_PICK) {
			Uri uri=data.getData();
			String[] filePathColumns={MediaStore.Images.Media.DATA};
			Cursor cursor=this.getContentResolver().query(uri, filePathColumns, null, null, null);
			cursor.moveToFirst();
			int columnIndex=cursor.getColumnIndex(filePathColumns[0]);
			String picPath=cursor.getString(columnIndex);
			System.out.println(picPath);
			/*for(int i=0;i<cursor.getColumnCount();i++){
				System.out.println(i+"========="+cursor.getColumnName(i)+"+++++++++"+cursor.getString(i));
			}*/
			this.refresh(picPath, false);
			//doSaveAndSend(picPath);
			cursor.close();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	// 拍照
	private int PHOTO_GRAPH = 1;
	private String fileName = null;

	//拍照
	public void takeFoto() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		/*
		 * Uri uri=Uri.fromFile(new
		 * File(Environment.getExternalStorageDirectory(), fileName));
		 * intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
		 */
		startActivityForResult(intent, PHOTO_GRAPH);
	}
	//图片浏览器
	private int PHOTO_PICK=2;
	public void fotoset(){
		Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(intent, PHOTO_PICK);
	}
}
