package com.feipinjia.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.filter.AndFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.filter.PacketTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;

import com.feipinjia.persistence.InMessageStore;
import com.feipinjia.service.InSmsService;
import com.feipinjia.util.StringUtils;
import com.feipinjia.util.XmppTool;

import com.feipinjia.activity.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

/**
 * @author mengqingbao
 *	
 */
public class MainTabActivity extends FragmentActivity{	
	private FragmentTabHost mTabHost;
	private LayoutInflater layoutInflater;
	private GridView gridview; 
	private Class fragmentArray[] = {FragmentPage1.class,FragmentPage2.class,FragmentPage3.class};
	
	private int mImageViewArray[] = {R.drawable.feipinzhijia,R.drawable.wymgjfp,
									 R.drawable.qhcs};
	
	private String mTextviewArray[] = {"废品之家", "我要高价卖废品", "城市切换"};
	
	//底部菜单常量参数
	private List<int[]> griditem = new ArrayList<int[]>();  
    {  
        griditem.add(new int[] { R.drawable.denglu, R.string.reg });//图片资源，标题,可自己设定  
        griditem.add(new int[] { R.drawable.sy, R.string.index });  
        griditem.add(new int[] { R.drawable.denglu, R.string.denglu });   
    };  
	private String userid;
	private List<Msg> listMsg = new ArrayList<Msg>();

	
	public class Msg {
		String userid;
		String msg;
		String date;
		String from;

		public Msg(String userid, String msg, String date, String from) {
			this.userid = userid;
			this.msg = msg;
			this.date = date;
			this.from = from;
		}
	}
	@Override
	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.userid = getIntent().getStringExtra("USERID");

       setContentView(R.layout.main_tab_layout);
        
        initView();
        initGrid();
       
    }
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	/**
	 * 初始化界面
	 */
	private void initView(){
		layoutInflater = LayoutInflater.from(this);
		mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);	
		
		int count = fragmentArray.length;	
				
		for(int i = 0; i < count; i++){	
			TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
			mTabHost.addTab(tabSpec, fragmentArray[i], null);
			mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
		}
	}
				
	private View getTabItemView(int index){
		View view = layoutInflater.inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageViewArray[index]);
		TextView textView = (TextView) view.findViewById(R.id.textview);		
		textView.setText(mTextviewArray[index]);
	
		return view;
	}
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(android.os.Message msg) 
		{
							
			switch (msg.what) {
			case 1:
				String[] args = (String[]) msg.obj;
				listMsg.add(new Msg(args[0], args[1], args[2], args[3]));
				break;			
			}
		};
	};	

	//退出
		@Override
		public void onBackPressed()
		{
			//关闭数据库连接退出
       		InMessageStore.getInstance().close();
       		System.exit(0);
       		android.os.Process.killProcess(android.os.Process.myPid());
		}

		private boolean isExit=false;
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			
			if(keyCode==KeyEvent.KEYCODE_BACK){
				if(!isExit){
					isExit=true;
					Toast.makeText(this.getApplicationContext(), "再按一次退出", Toast.LENGTH_SHORT).show();
					new Handler().postDelayed(new Runnable(){
						public void run(){
							isExit=false;
						}
					},2000);
					return false;
				}
			}
			
			return super.onKeyDown(keyCode, event);
		}
		
		
		//初始化底部菜单
		private void initGrid() {  
	        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();  
	  
	        for (int[] item : griditem) {  
	            Map<String, Object> map = new HashMap<String, Object>();  
	            map.put("image", item[0]);  
	            map.put("title", this.getApplication().getString(item[1]));  
	            items.add(map);  
	        }  
	  
	        SimpleAdapter adapter = new SimpleAdapter(getApplication(),  
	                items, // 列表内容  
	                R.layout.main_tab_activity_botton_grid_item, new String[] { "title", "image" },  
	                new int[] { R.id.item_text, R.id.item_image });  
	  
	        gridview = (GridView) findViewById(R.id.mygridview);  
	        // 为GridView设置数据  
	        gridview.setAdapter(adapter);  
	  
	    }  
}