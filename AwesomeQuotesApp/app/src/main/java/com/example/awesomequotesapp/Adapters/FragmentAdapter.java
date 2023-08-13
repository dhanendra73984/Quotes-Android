package com.example.awesomequotesapp.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.awesomequotesapp.Fragments.AllQuotesFragment;
import com.example.awesomequotesapp.Fragments.MyFavQuotesFragment;
import com.example.awesomequotesapp.Fragments.MyQuotesFragment;

public class FragmentAdapter extends FragmentStateAdapter {


    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position)
        {
            case 0:
                return new AllQuotesFragment();
            case 1:
                return new MyQuotesFragment();
            case 2:
                return new MyFavQuotesFragment();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
