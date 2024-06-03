package com.example.myhaui.CustomListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Friend;
import com.example.myhaui.model.Order;

import java.util.List;

public class FriendViewAdapter extends BaseAdapter {
    private Context context;
    private List<Friend> arrayListFriend;

    public FriendViewAdapter(Context context, List<Friend> data){
        this.context = context;
        this.arrayListFriend = data;
    }

    @Override
    public int getCount() {
        return arrayListFriend.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListFriend.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_friend, parent, false);
        }

        Friend currentFriend = (Friend)getItem(position);

        TextView txtName = convertView.findViewById(R.id.item_friend_name);
        TextView txtCode = convertView.findViewById(R.id.item_friend_code);
        txtName.setText(currentFriend.getFullName());
        txtCode.setText(currentFriend.getCode());

        return convertView;
    }
}
