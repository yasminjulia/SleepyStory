package dev.ruanvictor.sleepystorie.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.ruanvictor.sleepystorie.listeners.OnBookListener;
import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.adapter.SearchAdapter;
import dev.ruanvictor.sleepystorie.model.Book;
import dev.ruanvictor.sleepystorie.utils.MountBooks;
import dev.ruanvictor.sleepystorie.utils.OpenFragments;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements OnBookListener {

    private List<Book> books;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView listSearch = view.findViewById(R.id.listSearch);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        listSearch.setLayoutManager(layoutManager);

        books = MountBooks.books();

        SearchAdapter searchAdapter = new SearchAdapter(this.books, this);
        listSearch.setAdapter(searchAdapter);

        return view;
    }



    @Override
    public void onBookClick(int position) {
        Book book = books.get(position);

        OpenFragments.openBookDetails(book, getFragmentManager());
    }

}
