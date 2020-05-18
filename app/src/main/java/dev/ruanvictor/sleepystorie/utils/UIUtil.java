package dev.ruanvictor.sleepystorie.utils;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import com.google.android.material.textfield.TextInputLayout;

import dev.ruanvictor.sleepystorie.R;
import dev.ruanvictor.sleepystorie.fragments.DetailBookFragment;
import dev.ruanvictor.sleepystorie.model.Book;

public class UIUtil {

    public static void openBookDetails(Book book, FragmentManager fragmentManagerContext){
        DetailBookFragment detailBookFragment = new DetailBookFragment();
        Bundle params = new Bundle();
        params.putSerializable("book", book);
        detailBookFragment.setArguments(params);

        fragmentManagerContext.beginTransaction()
                .replace(R.id.nav_host_fragment, detailBookFragment, detailBookFragment.getTag())
                .commit();
    }

    public static void clearErrorStyle(TextInputLayout textInputLayout) {
        textInputLayout.setError(null);
        textInputLayout.setErrorEnabled(false);
    }
}
