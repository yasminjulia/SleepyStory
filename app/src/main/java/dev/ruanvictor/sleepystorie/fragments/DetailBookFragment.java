package dev.ruanvictor.sleepystorie.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.containers.RecommendationContainer;
import dev.ruanvictor.sleepystorie.model.Book;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailBookFragment extends Fragment {

    public DetailBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_book, container, false);
        Book book = (Book) getArguments().getSerializable("book");
        TextView titleDetails = view.findViewById(R.id.titleDetails);
        TextView authorDetails = view.findViewById(R.id.authorDetails);
        TextView descriptionDetail = view.findViewById(R.id.descriptionDetails);
        ImageView coverDetail = view.findViewById(R.id.coverDetail);

        titleDetails.setText(book.getTitle());
        authorDetails.setText(book.getAuthor());
        descriptionDetail.setText(book.getDescription());
        descriptionDetail.setMovementMethod(new ScrollingMovementMethod());
        coverDetail.setImageResource(book.getCover());
        coverDetail.setContentDescription(book.getTitle());

        RecommendationContainer.showGridRecommendation(view, getContext(), getFragmentManager());

        return view;
    }
}
