package projects.in.projectstructure.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import projects.in.projectstructure.MainActivity;
import projects.in.projectstructure.utils.BaseActivity;

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
} 