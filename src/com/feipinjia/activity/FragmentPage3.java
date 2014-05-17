package com.feipinjia.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.feipinjia.adapter.Fr3ListAdapter;
import com.feipinjia.listener.Fr3ItemOnClickListener;
import com.feipinjia.model.Config;
import com.feipinjia.model.StringVar;
import com.feipinjia.persistence.ConfigStroe;
import com.feipinjia.activity.R;

public class FragmentPage3 extends Fragment  implements OnClickListener{

	//private Button btn1;
	//private Button btn2;
	private View view ;
	//private ListView listView;
	//private Fr3ListAdapter adapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 view= inflater.inflate(R.layout.fragment_3, container, false);

		// listView = (ListView) view.findViewById(R.id.fragment_3_list);
		 //adapter=createAdpater(1);
     /*    listView.setAdapter(adapter);
         listView.setOnItemClickListener(new Fr3ItemOnClickListener(this.getActivity()));
         init(view);*/
         return view;		
	}	
	
	public void init(View view){
		/*btn1=(Button) view.findViewById(R.id.fr3_ly_btn1);
		btn2=(Button) view.findViewById(R.id.fr3_ly_btn2);*/
		//btn1.setBackground(R.drawable.u37_normal);
		//btn1.setOnClickListener(this);
	//	btn2.setOnClickListener(this);
		//btn1.setBackgroundResource(R.drawable.u37_normal);
	}

	@Override
	public void onClick(View view) {
		/*switch (view.getId()) {
		case R.id.fr3_ly_btn1:
			doctor();
			break;
		case R.id.fr3_ly_btn2:
			drugStore();
			break;
		}*/
	}
	
	public void drugStore(){
		//listView.setAdapter(createAdpater(2));
		//adapter.notifyDataSetChanged();
	//	btn1.setBackgroundResource(R.drawable.u35_normal);
		//btn2.setBackgroundResource(R.drawable.u37_normal);
	}
	
	public void doctor(){
		//listView.setAdapter(createAdpater(1));
		//adapter.notifyDataSetChanged();
		//btn1.setBackgroundResource(R.drawable.u37_normal);
		//btn2.setBackgroundResource(R.drawable.u35_normal);
	}
	
	public Fr3ListAdapter createAdpater(int type){
		List<Config> cfgs=ConfigStroe.getInstance().queryPageList(0, 10, this.getActivity(),type);
		List<StringVar> list=new ArrayList<StringVar>();
		for (Config cfg:cfgs) {
			StringVar sv = new StringVar();
			sv.setStr1(cfg.getTitle());
			if(cfg.getExta()>0){
				sv.setStr2(cfg.getExta()+cfg.getDesc());
			}else{
				sv.setStr2(cfg.getDesc());
			}
			list.add(sv);
		}
         return new Fr3ListAdapter(this.getActivity(),R.layout.in_frag3_list_item,list);
	}
}