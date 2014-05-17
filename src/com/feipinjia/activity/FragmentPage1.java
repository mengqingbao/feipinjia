package com.feipinjia.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.feipinjia.adapter.Fr5ListAdapter;
import com.feipinjia.model.Config;
import com.feipinjia.persistence.ConfigStroe;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

public class FragmentPage1 extends Fragment implements OnClickListener{
	private PopupWindow popw;
	private List<int[]> griditem = new ArrayList<int[]>();  
    {  
        griditem.add(new int[] { R.drawable.fei_pin_a_26, R.string.wymfp });//图片资源，标题,可自己设定  
        griditem.add(new int[] { R.drawable.fei_pin_a_28, R.string.jghq });  
        griditem.add(new int[] { R.drawable.fei_pin_p_23, R.string.fpzx });  
        griditem.add(new int[] { R.drawable.fei_pin_a_36, R.string.gyxx });  
        griditem.add(new int[] { R.drawable.fei_pin_a_37, R.string.txqz });  
        griditem.add(new int[] { R.drawable.fei_pin_a_34, R.string.more });  

    };  

    private GridView gridview; 
    private Fr5ListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_1, container, false);
		 
         return view;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		
	}
	@Override
	public void onClick(View v) {
		/*switch (v.getId()) {
		case R.id.frag1_add_chat:
			
			break;

		default:
			break;
		}*/
		
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		initGrid();
		super.onActivityCreated(savedInstanceState);
	}
	public void popMenu(View parent){
		
		if(popw==null){
			LayoutInflater layoutInflater = (LayoutInflater) this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View popwv=layoutInflater.inflate(R.layout.fragment_1_popmenu, null);
			popw=new PopupWindow(popwv,100,100);
		}
		popw.setFocusable(true);
		popw.setOutsideTouchable(true);
		popw.setBackgroundDrawable(new BitmapDrawable());
		WindowManager wm=(WindowManager) this.getActivity().getSystemService(Context.WINDOW_SERVICE);
		int xPos=wm.getDefaultDisplay().getWidth()-popw.getWidth();
		System.out.println(xPos);
		popw.showAsDropDown(parent,xPos,0);
	
	}
	
	 private void initGrid() {  
	        List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();  
	  
	        for (int[] item : griditem) {  
	            Map<String, Object> map = new HashMap<String, Object>();  
	            map.put("image", item[0]);  
	            map.put("title", this.getActivity().getString(item[1]));  
	            items.add(map);  
	        }  
	  
	        SimpleAdapter adapter = new SimpleAdapter(this.getActivity(),  
	                items, // 列表内容  
	                R.layout.fragment_1_grid_item, new String[] { "title", "image" },  
	                new int[] { R.id.item_text, R.id.item_image });  
	  
	        gridview = (GridView) this.getActivity().findViewById(R.id.menu_gridview);  
	        // 为GridView设置数据  
	        gridview.setAdapter(adapter);  
	  
	    }  

}
