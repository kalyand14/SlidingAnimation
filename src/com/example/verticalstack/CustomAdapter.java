package com.example.verticalstack;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList data;
	private static LayoutInflater inflater = null;
	public Resources res;
	ListModel tempValues = null;
	int i = 0;
	Animation animPushUp, animPushDown;

	/************* CustomAdapter Constructor *****************/
	public CustomAdapter(Activity a, ArrayList d, Resources resLocal) {

		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;
		animPushUp = AnimationUtils.loadAnimation(a.getApplicationContext(),
				R.anim.out_from_up);
		animPushDown = AnimationUtils.loadAnimation(a.getApplicationContext(),
				R.anim.in_from_up);

		/*********** Layout inflator to call external xml layout () ***********/
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/******** What is the size of Passed Arraylist Size ************/
	public int getCount() {

		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder Class to contain inflated xml file elements *********/
	public static class ViewHolder {

		public TextView text;
		// public TextView text1;
		// public TextView textWide;
		// public ImageView image;
		public Button pushBtn;
		public LinearLayout pullLayout;
		public LinearLayout container;
		public LinearLayout expand;

	}

	/****** Depends upon data size called for each row , Create each ListView row *****/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		final ViewHolder holder;

		if (convertView == null) {

			/****** Inflate tabitem.xml file for each row ( Defined below ) *******/
			vi = inflater.inflate(R.layout.test, null);

			/****** View Holder Object to contain tabitem.xml file elements ******/

			holder = new ViewHolder();
			holder.text = (TextView) vi.findViewById(R.id.box_txt);
			holder.pushBtn = (Button) vi.findViewById(R.id.btn);
			holder.pullLayout = (LinearLayout) vi
					.findViewById(R.id.pull_layout);
			holder.container=(LinearLayout)vi.findViewById(R.id.container);
			holder.expand = (LinearLayout) vi.findViewById(R.id.expand_layout);

			/*
			 * holder.text1=(TextView)vi.findViewById(R.id.text1);
			 * holder.image=(ImageView)vi.findViewById(R.id.image);
			 */

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (ViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.text.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			tempValues = null;
			tempValues = (ListModel) data.get(position);

			/************ Set Model values in Holder elements ***********/

			holder.text.setText(tempValues.getTxt());

			/*
			 * holder.text1.setText( tempValues.getUrl() );
			 * holder.image.setImageResource( res.getIdentifier(
			 * "com.androidexample.customlistview:drawable/"
			 * +tempValues.getImage() ,null,null));
			 */
			/******** Set Item Click Listner for LayoutInflater for each row *******/
			/*holder.pullLayout.setOnClickListener(new OnItemClickListener(
					position) {
				@Override
				public void onClick(View v) {
					switch (v.getId()) {
					case R.id.pull_layout:
						
						  holder.pullLayout.setVisibility(View.GONE);
						  expand.setVisibility(View.VISIBLE);
						  expand.startAnimation(animPushDown);
						 

						Toast.makeText(
								activity,
								"clicked inside the onClick() in onCLick() pull layout",
								Toast.LENGTH_SHORT).show();
					}
				}
			});*/
			
			holder.pullLayout.setOnClickListener(new OnItemClickListener(position) {
				
				@Override
				public void onClick(View v) {
					 holder.pullLayout.setVisibility(View.GONE);
					 holder.container.setVisibility(View.VISIBLE);
					 holder.expand.setVisibility(View.VISIBLE);
					 holder.expand.startAnimation(animPushDown);
					 
				}
			});
			
			holder.pushBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					holder.expand.startAnimation(animPushUp);
					new Handler().postDelayed(new Runnable() {
						public void run() {
							holder.container.setVisibility(View.GONE);
							holder.expand.setVisibility(View.GONE);
							holder.pullLayout.setVisibility(View.VISIBLE);
						}
					},1400);
				
				}
			});
			
			
			//vi.setOnClickListener(new OnItemClickListener(position));
		}
		return vi;
	}

	/*
	 * @Override public void onClick(View v) { switch (v.getId()) { case
	 * R.id.pull_layout: pullLayout.setVisibility(View.GONE);
	 * expand.setVisibility(View.VISIBLE); expand.startAnimation(animPushDown);
	 * 
	 * Toast.makeText(activity,
	 * "clicked inside the onClick() in onCLick() pull layout"
	 * ,Toast.LENGTH_SHORT).show(); } }
	 */

	/********* Called when Item click in ListView ************/
	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		public OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {

			Toast.makeText(
					activity,
					"clicked inside the onITEmClick() in onCLick() pull layout",
					Toast.LENGTH_SHORT).show();
			// CustomListViewAndroidExample sct =
			// (CustomListViewAndroidExample)activity;
			/*AnimationActivity aniActivity = (AnimationActivity) activity;
			*//****
			 * Call onItemClick Method inside CustomListViewAndroidExample Class
			 * ( See Below )
			 ****//*
			aniActivity.onItemClick(mPosition);*/
			// sct.onItemClick(mPosition);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}