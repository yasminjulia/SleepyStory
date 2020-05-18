package dev.ruanvictor.sleepystorie.containers;

import android.content.Context;
import android.view.View;
import android.widget.GridView;

import androidx.fragment.app.FragmentManager;

import java.util.List;

import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.adapter.RecommendAdapter;
import dev.ruanvictor.sleepystorie.model.Book;
import dev.ruanvictor.sleepystorie.utils.MountBooks;
import dev.ruanvictor.sleepystorie.utils.UIUtil;

public class RecommendationContainer {

    public static void showGridRecommendation(View view, Context context, FragmentManager fragmentManager) {
        List<Book> books = MountBooks.books();

        RecommendAdapter recommendAdapter = new RecommendAdapter(context, R.layout.item_recommend, books);
        GridView gridViewRecommend = view.findViewById(R.id.gridViewRecommend);
        gridViewRecommend.setAdapter(recommendAdapter);

        gridViewRecommend.setOnItemClickListener((parent, view1, position, id) -> {
            Book book = books.get(position);

            UIUtil.openBookDetails(book, fragmentManager);
        });
    }
}
