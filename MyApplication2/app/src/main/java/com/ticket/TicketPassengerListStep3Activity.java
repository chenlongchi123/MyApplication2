package com.ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.chen.myapplication.R;
import com.examplechen.my.MycontactEditActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketPassengerListStep3Activity extends Activity {

	ListView lvTicketPassengerListStep3;
	List<Map<String, Object>> data;
	ArrayList<Boolean> ckFlg = new ArrayList<Boolean>();
	Button btnTicketPassengerListStep3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_passenger_list_step3);

		lvTicketPassengerListStep3 = (ListView) findViewById(R.id.lvTicketPassengerListStep3);
		btnTicketPassengerListStep3 = (Button) findViewById(R.id.btnTicketPassengerListStep3);

		data = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < 10; i++) {
			Map<String, Object> row = new HashMap<String, Object>();
			row.put("name", "熊大" + i + "(成人)");
			row.put("id", "身份证号:110110199901011234");
			row.put("tel", "电话:13700001234");
			data.add(row);
		}

		lvTicketPassengerListStep3.setAdapter(new TicketPassengerListStep3());

		// 初始化ckFlg
		for (int i = 0; i < data.size(); i++) {
			ckFlg.add(false);
		}

		lvTicketPassengerListStep3
				.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(TicketPassengerListStep3Activity.this,
								MycontactEditActivity.class);
						intent.putExtra("row", (Serializable)data.get(position));
						startActivity(intent);
					}
				});

		btnTicketPassengerListStep3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 传回数据的过程在实现中完成
				finish();
			}
		});
	}

	class TicketPassengerListStep3 extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int position, View convertView,
							ViewGroup parent) {
			// TODO Auto-generated method stub
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();

				convertView = LayoutInflater.from(
						TicketPassengerListStep3Activity.this).inflate(
						R.layout.item_ticket_passenger_list_step3, null);

				holder.tvTicketPassengerListStep3Name = (TextView) convertView
						.findViewById(R.id.tvTicketPassengerListStep3Name);
				holder.tvTicketPassengerListStep3IdCard = (TextView) convertView
						.findViewById(R.id.tvTicketPassengerListStep3IdCard);
				holder.tvTicketPassengerListStep3Tel = (TextView) convertView
						.findViewById(R.id.tvTicketPassengerListStep3Tel);
				holder.ckTicketPassengerListStep3 = (CheckBox) convertView
						.findViewById(R.id.ckTicketPassengerListStep3);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tvTicketPassengerListStep3Name.setText(data.get(position)
					.get("name").toString());
			holder.tvTicketPassengerListStep3IdCard.setText(data.get(position)
					.get("id").toString());
			holder.tvTicketPassengerListStep3Tel.setText(data.get(position)
					.get("tel").toString());
			holder.ckTicketPassengerListStep3.setChecked(ckFlg.get(position));

			holder.ckTicketPassengerListStep3
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							ckFlg.set(position, !ckFlg.get(position));
							Toast.makeText(
									TicketPassengerListStep3Activity.this,
									ckFlg.toString(), Toast.LENGTH_SHORT)
									.show();
						}
					});

			return convertView;
		}

	}

	class ViewHolder {
		TextView tvTicketPassengerListStep3Name;
		TextView tvTicketPassengerListStep3IdCard;
		TextView tvTicketPassengerListStep3Tel;
		CheckBox ckTicketPassengerListStep3;
	}


}
