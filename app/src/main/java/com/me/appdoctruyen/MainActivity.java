package com.me.appdoctruyen;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.window.java.layout.WindowInfoTrackerCallbackAdapter;
import androidx.window.layout.DisplayFeature;
import androidx.window.layout.FoldingFeature;
import androidx.window.layout.WindowInfoTracker;
import androidx.window.layout.WindowLayoutInfo;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.navigationrail.NavigationRailView;
import com.me.appdoctruyen.databinding.ActivityMainBinding;
import com.me.appdoctruyen.utils.AdaptiveUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

public class MainActivity extends AppCompatActivity implements HasAndroidInjector {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Configuration configuration;
    private View container;
    private DrawerLayout drawerLayout;
    private NavigationView modalNavDrawer;
    private BottomNavigationView bottomNav;
    private NavigationRailView navRail;
    private NavigationView navDrawer;
    private ExtendedFloatingActionButton navFab;
    private FirstFragment firstFragment;

    @Nullable private WindowInfoTrackerCallbackAdapter windowInfoTracker;
    private final Consumer<WindowLayoutInfo> stateContainer = new StateContainer();
    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        firstFragment = new FirstFragment();
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        configuration = getResources().getConfiguration();
        container = binding.feedActivityContainer;
        drawerLayout = binding.drawerLayout;
        modalNavDrawer = binding.modalNavDrawer;
        windowInfoTracker =
                new WindowInfoTrackerCallbackAdapter(WindowInfoTracker.getOrCreate(this));
        configuration = getResources().getConfiguration();
        bottomNav = binding.bottomNav;
        navRail = binding.navRail;
        navDrawer = binding.navDrawer;
        navFab = findViewById(R.id.nav_fab);

        // Update navigation views according to screen width size.
        int screenWidth = configuration.screenWidthDp;
        AdaptiveUtils.updateNavigationViewLayout(
                screenWidth,
                drawerLayout,
                modalNavDrawer,
                /* fab= */ null,
                bottomNav,
                navRail,
                navDrawer,
                navFab);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return androidInjector;
    }

    private class StateContainer implements Consumer<WindowLayoutInfo> {

        public StateContainer() {}

        @Override
        public void accept(WindowLayoutInfo windowLayoutInfo) {
            if (firstFragment == null) {
                return;
            }

            if (configuration.screenWidthDp < AdaptiveUtils.MEDIUM_SCREEN_WIDTH_SIZE) {
//                firstFragment.setClosedLayout();
            } else {
                List<DisplayFeature> displayFeatures = windowLayoutInfo.getDisplayFeatures();
                boolean isClosed = true;

                for (DisplayFeature displayFeature : displayFeatures) {
                    if (displayFeature instanceof FoldingFeature) {
                        FoldingFeature foldingFeature = (FoldingFeature) displayFeature;
                        if (foldingFeature.getState().equals(FoldingFeature.State.HALF_OPENED)
                                || foldingFeature.getState().equals(FoldingFeature.State.FLAT)) {
                            FoldingFeature.Orientation orientation = foldingFeature.getOrientation();
                            if (orientation.equals(FoldingFeature.Orientation.VERTICAL)) {
                                int foldPosition = foldingFeature.getBounds().left;
                                int foldWidth = foldingFeature.getBounds().right - foldPosition;
                                // Device is open and fold is vertical.
//                                firstFragment.setOpenLayout(foldPosition, foldWidth);
                            } else {
                                // Device is open and fold is horizontal.
//                                firstFragment.setOpenLayout(container.getWidth() / 2, 0);
                            }
                            isClosed = false;
                        }
                    }
                }
                if (isClosed) {
                    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        // Device is closed or not foldable and in portrait.
//                        firstFragment.setClosedLayout();
                    } else {
                        // Device is closed or not foldable and in landscape.
//                        firstFragment.setOpenLayout(container.getWidth() / 2, 0);
                    }
                }
            }
        }
    }
}