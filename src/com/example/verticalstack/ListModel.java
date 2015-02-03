package com.example.verticalstack;

import android.widget.Button;
import android.widget.LinearLayout;

public class ListModel {

	private String txt = "";
	public Button pushBtn;
	public LinearLayout pullLayout;
	public LinearLayout expand;
	public LinearLayout container;

	/*********** Set Methods ******************/
	public String getTxt() {
		return txt;
	}

	public LinearLayout getContainer() {
		return container;
	}

	public void setContainer(LinearLayout container) {
		this.container = container;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Button getPushBtn() {
		return pushBtn;
	}

	public void setPushBtn(Button pushBtn) {
		this.pushBtn = pushBtn;
	}

	public LinearLayout getPullLayout() {
		return pullLayout;
	}

	public void setPullLayout(LinearLayout pullLayout) {
		this.pullLayout = pullLayout;
	}

	public LinearLayout getExpand() {
		return expand;
	}

	public void setExpand(LinearLayout expand) {
		this.expand = expand;
	}

	/*********** Get Methods ****************/

}
