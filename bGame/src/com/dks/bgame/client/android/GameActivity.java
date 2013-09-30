package com.dks.bgame.client.android;


import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.exceptions.SFSException;

import sfs2x.client.SmartFox;
import sfs2x.client.core.BaseEvent;
import sfs2x.client.core.IEventListener;
import sfs2x.client.core.SFSEvent;
import sfs2x.client.entities.Room;
import sfs2x.client.requests.ExtensionRequest;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements IEventListener {

	private final String CMD_Guess = "guess"; 
	private final String RESPONSE_Guess = "guess";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		initUI();
		
		initSmartFox();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	SmartFox sfsClient;
	
	EditText inputNumberEditor;
	View buttonGuess;
	TextView log;
	
	private void initUI() {			
		
		inputNumberEditor = (EditText) findViewById(R.id.edit_guess_number);
		buttonGuess = (View) findViewById(R.id.button_guess);
		log = (TextView) findViewById(R.id.label_log);
		
		buttonGuess.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
									
				String input = inputNumberEditor.getText().toString();
				if (input.isEmpty())
					return;
				
				Integer inputNumber = Integer.parseInt(input);
				
				
				// Send the selected square to SFS2X
				ISFSObject sfso = new SFSObject();
				sfso.putInt("number", inputNumber);				
				
				Room s = sfsClient.getLastJoinedRoom();
				
				sfsClient.send(new ExtensionRequest(CMD_Guess, sfso, sfsClient.getLastJoinedRoom()));
				
				log.setText(log.getText() + "\nTry:" + inputNumber.toString());
				
			}});
	}
		
	
	
	private void initSmartFox() {
		// Instantiate SmartFox client
	    sfsClient = SFSController.getSFSClient();
	    
	    sfsClient.addEventListener(SFSEvent.EXTENSION_RESPONSE, this);
	    
	}
	
	public void dispatch(final BaseEvent event) throws SFSException {
		
		if (event.getType().equalsIgnoreCase(SFSEvent.EXTENSION_RESPONSE)) {
			String cmd = event.getArguments().get("cmd").toString();
			ISFSObject resObj = new SFSObject();
			resObj = (ISFSObject) event.getArguments().get("params");
			
			if (cmd == RESPONSE_Guess) {
			
			final String result = resObj.getUtfString("res");
			
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					
					//Toast.makeText(getApplicationContext(), "Result:" + result, Toast.LENGTH_LONG);
					log.setText(log.getText() + "\nResult:" + result);				
				}
			});
			
			}
		}
		
	}
}
