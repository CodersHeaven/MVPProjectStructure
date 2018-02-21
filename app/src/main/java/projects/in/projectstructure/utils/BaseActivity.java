package projects.in.projectstructure.utils;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import projects.in.projectstructure.R;
import projects.in.projectstructure.data.local.SharedPreferenceManager;
import projects.in.projectstructure.data.remote.RemoteDataSource;
import projects.in.projectstructure.utils.threading.MainUiThread;
import projects.in.projectstructure.utils.threading.ThreadExecutor;


/**
 * Created by prime on 16-12-2017.
 */

public class BaseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    private MainUiThread mainUiThread;
    private ThreadExecutor threadExecutor;
    private SharedPreferenceManager sharedPreferenceManager;

    private RemoteDataSource remoteDataSource;
    public DisplayMetrics metrices;

    Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainUiThread = MainUiThread.getInstance();
        threadExecutor = ThreadExecutor.getInstance();
        remoteDataSource = RemoteDataSource.getInstance(mainUiThread, threadExecutor);

        sharedPreferenceManager = SharedPreferenceManager.getInstance(this);

        metrices = getResources().getDisplayMetrics();

        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    public <T extends Fragment> void showFragment(Class<T> fragmentClass, Bundle bundle,
                                                  boolean addToBackStack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragment = getSupportFragmentManager().findFragmentByTag(
                fragmentClass.getSimpleName());
        if (fragment == null) {
            try {
                fragment = fragmentClass.newInstance();
                fragment.setArguments(bundle);
            } catch (InstantiationException e) {
                throw new RuntimeException("New Fragment should have been created", e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException("New Fragment should have been created", e);
            }
        }

        fragmentTransaction.setCustomAnimations(R.anim.slide_in_right,
                R.anim.slide_out_left, android.R.anim.slide_in_left,
                android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.container, fragment,
                fragmentClass.getSimpleName());


        if (addToBackStack) {
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }

    public <T extends Fragment> void showFragment(Class<T> fragmentClass) {
        showFragment(fragmentClass, null, false);
    }

    public void popFragmentBackStack() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            exitDialog(false);
        }
    }

    private void shouldShowActionBarUpButton() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackStackChanged() {

    }

    public RemoteDataSource getRemoteDataSource() {
        return remoteDataSource;
    }

    public SharedPreferenceManager getSharedPreferenceManager() {
        return sharedPreferenceManager;
    }

    public void exitDialog(final boolean logout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(logout) {
                    SharedPreferenceManager.getInstance(BaseActivity.this).setBoolean(PrefUtils.ISLOGGEDIN, false);
                    //SharedPreferenceManager.getInstance(BaseActivity.this).clearDB();
                    //startActivity(new Intent(BaseActivity.this, LoginActivity.class));
                }
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }
}
