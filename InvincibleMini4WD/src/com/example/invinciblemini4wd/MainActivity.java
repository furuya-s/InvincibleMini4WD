package com.example.invinciblemini4wd;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	private TextView auto_label;
	private TextView manual_label;
	private Button auto_stop;
	private Button auto_start;
	private Button manual_stop;
	private Button manual_forward;
	private Button manual_backward;
	private Button manual_brake;
	
	private boolean manual = false;
	private int stat = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		auto_label = (TextView)findViewById(R.id.auto_label);
		manual_label = (TextView)findViewById(R.id.manual_label);
		auto_stop = (Button)findViewById(R.id.auto_stop);
		auto_start = (Button)findViewById(R.id.auto_start);
		manual_stop = (Button)findViewById(R.id.manual_stop);
		manual_forward = (Button)findViewById(R.id.manual_forward);
		manual_backward = (Button)findViewById(R.id.manual_backward);
		manual_brake = (Button)findViewById(R.id.manual_brake);
		
		//////////
		auto_label.setEnabled(false);
		auto_stop.setEnabled(false);
		auto_start.setEnabled(false);
		//////////
		
		
		
		auto_stop.setOnClickListener(this);
		auto_start.setOnClickListener(this);
		manual_stop.setOnClickListener(this);
		manual_forward.setOnClickListener(this);
		manual_backward.setOnClickListener(this);
		manual_brake.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.auto_stop:
				manual = false;
				auto_label.setTextColor(Color.BLACK);
				manual_label.setTextColor(Color.BLACK);
				stat = 0;
				break;
			case R.id.auto_start:
				manual = false;
				auto_label.setTextColor(Color.RED);
				manual_label.setTextColor(Color.BLACK);
				stat = 1;
				break;
			case R.id.manual_stop:
				manual = true;
				auto_label.setTextColor(Color.BLACK);
				manual_label.setTextColor(Color.BLACK);
				stat = 2;
				break;
			case R.id.manual_forward:
				manual = true;
				auto_label.setTextColor(Color.BLACK);
				manual_label.setTextColor(Color.RED);
				stat = 3;
				break;
			case R.id.manual_backward:
				manual = true;
				auto_label.setTextColor(Color.BLACK);
				manual_label.setTextColor(Color.RED);
				stat = 4;
				break;
			case R.id.manual_brake:
				manual = true;
				auto_label.setTextColor(Color.BLACK);
				manual_label.setTextColor(Color.BLACK);
				stat = 5;
				break;
		}
		
		UltraSonicHttpGetTask task = new UltraSonicHttpGetTask(this);
		task.execute(stat);
		
	}

}
