package com.example.verticalstack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class AnimationActivity extends Activity{
	
	Animation animPushUp, animPushDown;
	LinearLayout pull,expand;
	Button pushBtn;
	  ListView list;
      CustomAdapter adapter;
      public  AnimationActivity CustomListView = null;
      public  ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_main);
		
		animPushUp = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.out_from_up);
		animPushDown = AnimationUtils.loadAnimation(getApplicationContext(),
				R.anim.in_from_up);
		
		CustomListView = this;
		
		  /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();
         
        Resources res =getResources();
        list= ( ListView )findViewById( R.id.list );  // List defined in XML ( See Below )
         
        /**************** Create Custom Adapter *********/
        adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
        list.setAdapter( adapter );
		/*pull=(LinearLayout)findViewById(R.id.pull_layout);
		expand=(LinearLayout) findViewById(R.id.expand_layout);
		pushBtn=(Button)findViewById(R.id.btn);
		
		pull.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pull.setVisibility(View.GONE);
				expand.setVisibility(View.VISIBLE);
				expand.startAnimation(animPushDown);
			}
		});
		
		pushBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				expand.startAnimation(animPushUp);
				expand.setVisibility(View.GONE);
				pull.setVisibility(View.VISIBLE);
			}
		});
		*/
	}
	
    /****** Function to set data in ArrayList *************/
    public void setListData()
    {
         
        for (int i = 0; i < 11; i++) {
             
            final ListModel sched = new ListModel();
                 
              /******* Firstly take data in model object ******/
               sched.setTxt("Test "+i);
              // sched.setImage("image"+i);
               ///sched.setUrl("http:\\www."+i+".com");
                
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }
         
    }
    

   /*****************  This function used by adapter ****************/
    public void onItemClick(int mPosition)
    {
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);
        
        pull=tempValues.getPullLayout();
        expand=tempValues.getExpand();
        pushBtn=tempValues.getPushBtn();
        tempValues.getPullLayout().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				pull.setVisibility(View.GONE);
				expand.setVisibility(View.VISIBLE);
				expand.startAnimation(animPushDown);
			}
		});
        
pushBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				expand.startAnimation(animPushUp);
				expand.setVisibility(View.GONE);
				pull.setVisibility(View.VISIBLE);
			}
		});
        
      /* // SHOW ALERT                  

        Toast.makeText(CustomListView,
                ""+tempValues.getCompanyName()
                  +" 
Image:"+tempValues.getImage()
                  +" 
Url:"+tempValues.getUrl(),
                Toast.LENGTH_LONG)
        .show();*/
    }
}
