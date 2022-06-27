package com.me.appdoctruyen.ui.book.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.slidingpanelayout.widget.SlidingPaneLayout;

import com.me.appdoctruyen.data.models.Book;
import com.me.appdoctruyen.databinding.FragmentFirstBinding;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class FirstFragment extends DaggerFragment implements BookItemClicked {

    private FragmentFirstBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    BookViewModel viewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(BookViewModel.class);
        SlidingPaneLayout slidingPaneLayout = binding.slidingPaneLayout;
        slidingPaneLayout.setLockMode(SlidingPaneLayout.LOCK_MODE_LOCKED);
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new BooksListOnBackPressedCallback(slidingPaneLayout));

        BookAdapter bookAdapter = new BookAdapter(this);
        binding.recyclerView.setAdapter(bookAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClicked(@NonNull Book book) {
        binding.slidingPaneLayout.openPane();
    }
}

class BooksListOnBackPressedCallback extends OnBackPressedCallback implements SlidingPaneLayout.PanelSlideListener {

    private SlidingPaneLayout slidingPaneLayout;

    public BooksListOnBackPressedCallback(SlidingPaneLayout slidingPaneLayout) {
        super(slidingPaneLayout.isSlideable() && slidingPaneLayout.isOpen());
        this.slidingPaneLayout = slidingPaneLayout;
    }

    @Override
    public void handleOnBackPressed() {
        slidingPaneLayout.closePane();
    }

    @Override
    public void onPanelSlide(@NonNull View panel, float slideOffset) {

    }

    @Override
    public void onPanelOpened(@NonNull View panel) {
        setEnabled(true);
    }

    @Override
    public void onPanelClosed(@NonNull View panel) {
        setEnabled(false);
    }
}