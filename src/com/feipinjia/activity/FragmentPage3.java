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
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.feipinjia.adapter.Fr3ListAdapter;
import com.feipinjia.adapter.TreeViewAdapter;
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
	private ExpandableListView expandableList;  
    private TreeViewAdapter adapter;
    private String[] groups = { "热门城市", "B", "C"};  
    private String[][]  child= {  
            { "北京", "上海", "广州", "南京" },  
            { "北京", "江苏", "广西"},  
            { "成都", "重庆" }  
    };  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {	
		 view= inflater.inflate(R.layout.fragment_3, container, false);
		 adapter=new TreeViewAdapter(this.getActivity().getApplicationContext(),TreeViewAdapter.PaddingLeft>>1);  
	     expandableList=(ExpandableListView) view.findViewById(R.id.cityTableListView); 
	     initExpandableListView();
         return view;		
	}	
	
	

	public void initExpandableListView(){
		List<TreeViewAdapter.TreeNode> treeNode = adapter.GetTreeNode();  
        for(int i=0;i<groups.length;i++)  
        {  
            TreeViewAdapter.TreeNode node=new TreeViewAdapter.TreeNode();  
            node.parent=groups[i];  
            for(int ii=0;ii<child[i].length;ii++)  
            {  
                node.childs.add(child[i][ii]);  
            }  
            treeNode.add(node);  
        }  
          
        adapter.UpdateTreeNode(treeNode);       
        expandableList.setAdapter(adapter);  
        expandableList.setOnChildClickListener(new OnChildClickListener(){  

            @Override  
            public boolean onChildClick(ExpandableListView arg0, View arg1,  
                    int parent, int children, long arg4) {  
                  
                String str="parent id:"+String.valueOf(parent)+",children id:"+String.valueOf(children);  
                Toast.makeText(FragmentPage3.this.getActivity().getApplicationContext(), str, 300).show();  
                return false;  
            }  
        });
	}
  
   
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}  
	
}