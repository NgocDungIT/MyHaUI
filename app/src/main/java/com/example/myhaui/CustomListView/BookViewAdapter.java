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
import com.example.myhaui.model.Author;
import com.example.myhaui.model.Book;

import java.util.List;

public class BookViewAdapter extends BaseAdapter {
    private Context context;
    private List<Book> arrayListBoook;

    public BookViewAdapter(Context context, List<Book> data){
        this.context = context;
        this.arrayListBoook = data;
    }

    @Override
    public int getCount() {
        return arrayListBoook.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayListBoook.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_book, parent, false);
        }

        Book currentBook = (Book)getItem(position);

        TextView bookDescription = convertView.findViewById(R.id.item_book_decription);
        ImageView bookImg = convertView.findViewById(R.id.item_book_img);
        TextView bookName = convertView.findViewById(R.id.item_book_name);

                String imageName = currentBook.getImage().toString().trim();
//        String imageName = "mangmaytinh";
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());
        bookImg.setImageResource(imageResId);
        bookName.setText(currentBook.getName());
        bookDescription.setText("Mô tả: " + currentBook.getDescription().toString());

        return convertView;
    }

}
