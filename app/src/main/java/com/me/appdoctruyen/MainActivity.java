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
    private DrawerLayout drawerLayout;
    private NavigationView modalNavDrawer;
    private BottomNavigationView bottomNav;
    private NavigationRailView navRail;
    private NavigationView navDrawer;
    private ExtendedFloatingActionButton navFab;

    @Inject
    DispatchingAndroidInjector<Object> androidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        configuration = getResources().getConfiguration();
        drawerLayout = binding.drawerLayout;
        modalNavDrawer = binding.modalNavDrawer;

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
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
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

}