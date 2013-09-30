package com.dks.bgame.client.android;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class NumberGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_number_game);
		
		
	
		initUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.number_game, menu);
		return true;
	}

	Button button1, button2, button3, button4, button5, button6;
	Button button7;
	private void initUI() {
	
		button1 =  (Button)this.findViewById(R.id.button1);
		button2 =  (Button)this.findViewById(R.id.button2);
		button3 =  (Button)this.findViewById(R.id.button3);
		button4 =  (Button)this.findViewById(R.id.button4);
		button5 =  (Button)this.findViewById(R.id.button5);
		button6 =  (Button)this.findViewById(R.id.button6);
		
		button7 =  (Button)this.findViewById(R.id.button7);
		
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				button7.setText(button7.getText() + " " + button1.getText());
			}
		});
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				button7.setText(button7.getText() + " " + button2.getText());
			}
		});
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				button7.setText(button7.getText() + " " + button3.getText());
			}
		});
		button4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				button7.setText(button7.getText() + " " + button4.getText());
			}
		});
		button5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				button7.setText(button7.getText() + " " + button5.getText());
			}
		});
		button6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {				
				button7.setText(button7.getText() + " " + button6.getText());
			}
		});
	}
	
}
