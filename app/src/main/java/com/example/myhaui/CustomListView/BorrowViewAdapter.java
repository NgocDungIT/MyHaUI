package com.example.myhaui.CustomListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhaui.Database.DatabaseQuery;
import com.example.myhaui.R;
import com.example.myhaui.model.Book;
import com.example.myhaui.model.Order;

import java.util.List;

public class BorrowViewAdapter extends BaseAdapter {
    private Context context;
    private List<Order> arrayListOrder;

    public BorrowViewAdapter(Context context, List<Order> data){
        this.context = context;
        this.arrayListOrder = data;
    }

    @Override
    public int getCount() {
        return arrayListOrder.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListOrder.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_borrow_book, parent, false);
        }

        DatabaseQuery query = new DatabaseQuery(context);
        Order currentOrder = (Order)getItem(position);
        Book book = query.getBookById(currentOrder.getBook_id());

        ImageView bookImg = convertView.findViewById(R.id.borrow_item_img);
        TextView bookName = convertView.findViewById(R.id.borrow_item_name);
        TextView bookStatus = convertView.findViewById(R.id.borrow_item_status);


        String imageName = book.getImage().toString().trim();
//        String imageName = "mangmaytinh";
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        bookImg.setImageResource(imageResId);
        bookName.setText(book.getName());
        if(currentOrder.getIs_returned() == 0){
            bookStatus.setText("Trạng thái: Đang mượn");
        }else {
            bookStatus.setText("Trạng thái: Đã trả");
        }

        return convertView;
    }
}
