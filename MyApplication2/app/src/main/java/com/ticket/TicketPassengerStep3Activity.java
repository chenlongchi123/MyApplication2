package com.ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.chen.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketPassengerStep3Activity extends Activity {
	ListView lvTicketPassengerStep3;
	List<Map<String, Object>> data = null;
	TextView tvTicketPassengerStep3PassengerList;
	TextView tvTicketPassengerStep3Submit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ticket_passenger_step3);

		lvTicketPassengerStep3 = (ListView) findViewById(R.id.lvTicketPassengerStep3);
		tvTicketPassengerStep3PassengerList = (TextView) findViewById(R.id.tvTicketPassengerStep3PassengerList);
		tvTicketPassengerStep3Submit = (TextView) findViewById(R.id.tvTicketPassengerStep3Submit);

		data = new ArrayList<Map<String, Object>>();

		Map<String, Object> row1 = new HashMap<String, Object>();
		row1.put("name", "李四");
		row1.put("idCard", "身份证:10010019990101012X");
		row1.put("tel", "电话:13812345578");
		data.add(row1);

		Map<String, Object> row2 = new HashMap<String, Object>();
		row2.put("name", "订单");
		row2.put("idCard", "身份证:20010019990101012X");
		row2.put("tel", "电话:13912345578");
		data.add(row2);

		Map<String, Object> row3 = new HashMap<String, Object>();
		row3.put("name", "中午");
		row3.put("idCard", "身份证:30010019990101012X");
		row3.put("tel", "电话:13712345578");
		data.add(row3);

		lvTicketPassengerStep3.setAdapter(new TicketPassengerStep3Adapter());

		tvTicketPassengerStep3PassengerList
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent = new Intent();
						intent.setClass(TicketPassengerStep3Activity.this,
								TicketPassengerListStep3Activity.class);
						startActivity(intent);
					}
				});

		tvTicketPassengerStep3Submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 跳转到下一步
				Intent intent = new Intent();
				intent.setClass(TicketPassengerStep3Activity.this,
						TicketOrderSuccessStep4Activity.class);
				startActivity(intent);
			}
		});
	}

	class TicketPassengerStep3Adapter extends BaseAdapter {

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
						TicketPassengerStep3Activity.this).inflate(
						R.layout.item_ticket_passenger_step3, null);

				holder.tvTicketPassengerStep3Name = (TextView) convertView
						.findViewById(R.id.tvTicketPassengerStep3Name);
				holder.tvTicketPassengerStep3IdCard = (TextView) convertView
						.findViewById(R.id.tvTicketPassengerStep3IdCard);
				holder.tvTicketPassengerStep3Tel = (TextView) convertView
						.findViewById(R.id.tvTicketPassengerStep3Tel);
				holder.imgTicketPassengerStep3Del = (ImageView) convertView
						.findViewById(R.id.imgTicketPassengerStep3Del);

				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// 赋值
			holder.tvTicketPassengerStep3Name.setText(data.get(position)
					.get("name").toString());
			holder.tvTicketPassengerStep3IdCard.setText(data.get(position)
					.get("idCard").toString());
			holder.tvTicketPassengerStep3Tel.setText(data.get(position)
					.get("tel").toString());
			holder.imgTicketPassengerStep3Del
					.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							data.remove(position);
							notifyDataSetChanged();
						}
					});

			return convertView;
		}

	}

	class ViewHolder {
		TextView tvTicketPassengerStep3Name;
		TextView tvTicketPassengerStep3IdCard;
		TextView tvTicketPassengerStep3Tel;
		ImageView imgTicketPassengerStep3Del;
	}



}
