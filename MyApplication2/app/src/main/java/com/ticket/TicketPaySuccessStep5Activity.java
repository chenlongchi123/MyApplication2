package com.ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.chen.myapplication.MainActivity;
import com.example.chen.myapplication.R;


public class TicketPaySuccessStep5Activity extends Activity {

	Button btnTicketPaySuccessStep5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_pay_success_step5);

		btnTicketPaySuccessStep5 = (Button) findViewById(R.id.btnTicketPaySuccessStep5);

		btnTicketPaySuccessStep5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(TicketPaySuccessStep5Activity.this,
						MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
	}


}
