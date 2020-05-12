package dev.ruanvictor.sleepystorie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import dev.ruanvictor.sleepystorie.listeners.OnBookListener;
import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.model.Book;

public class RecommendAdapter extends ArrayAdapter<Book> {
    private List<Book> books;
    private OnBookListener onBookListener;
    private Context context;
    private int layoutResource;

    public RecommendAdapter(@NonNull Context context, int resource, @NonNull List<Book> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layoutResource = resource;
        this.books = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder.cover = convertView.findViewById(R.id.imageCoverRecommend);
            viewHolder.title = convertView.findViewById(R.id.textTitleRecommend);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Book book = getItem(position);
        viewHolder.cover.setImageResource(book.getCover());
        viewHolder.title.setText(book.getTitle());

        return convertView;
    }

    //    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemList = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_recommend, parent, false);
//
//        return new MyViewHolder(itemList, onBookListener);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Book book = this.books.get(position);
//        holder.cover.setImageResource(book.getCover());
//        holder.cover.setContentDescription(book.getTitle());
//        holder.title.setText(book.getTitle());
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.books.size();
//    }

    public class ViewHolder{
        ImageView cover;
        TextView title;
    }
}
