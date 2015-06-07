package com.projektgrupowy;

import android.app.Activity;
import android.os.Bundle;

import com.example.googleglass.R;
import com.google.android.glass.widget.CardBuilder;

public class MainActivity extends Activity {

	private CardBuilder cardBuilder;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_main);
		
		
	}

}
