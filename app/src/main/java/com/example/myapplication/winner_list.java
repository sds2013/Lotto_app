package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class winner_list extends AppCompatActivity {
    private String TAG = "로또당첨자 리스트뷰";
    private ListView listview = null;
    private ListViewAdapter adapter = null;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_list);

        listview = (ListView) findViewById(R.id.listview);
        adapter = new ListViewAdapter();

        adapter.addItem(new winner_item("1", "527회차", R.drawable.one));
        adapter.addItem(new winner_item("2", "528회차", R.drawable.secondjpg));
        adapter.addItem(new winner_item("3", "529회차", R.drawable.three));
        adapter.addItem(new winner_item("4", "530회차", R.drawable.four));
        adapter.addItem(new winner_item("5", "531회차", R.drawable.five));
        adapter.addItem(new winner_item("6", "532회차", R.drawable.six));

         listview.setAdapter(adapter);
    }

    public class ListViewAdapter extends BaseAdapter {
        ArrayList<winner_item> items = new ArrayList<winner_item>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(winner_item item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final winner_item winner_item = items.get(position);

            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.activity_winner_list_item, viewGroup, false);

            } else {
                View view = new View(context);
                view = (View) convertView;
            }

            TextView tv_num = (TextView) convertView.findViewById(R.id.textView2);
            TextView tv_name = (TextView) convertView.findViewById(R.id.textView3);
            ImageView iv_icon = (ImageView) convertView.findViewById(R.id.imageView);

            tv_num.setText(winner_item.getNum());
            tv_name.setText(winner_item.getName());
            iv_icon.setImageResource(winner_item.getResId());
            Log.d(TAG, "getView() - [ "+position+" ] "+winner_item.getName());

            //각 아이템 선택 event
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, winner_item.getNum()+" 번 - "+winner_item.getName()+" 입니당! ", Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;  //뷰 객체 반환
        }
    }
}