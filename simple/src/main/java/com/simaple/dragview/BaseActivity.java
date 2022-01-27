package com.simaple.dragview;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Yue on 2017/4/20.
 */

public class BaseActivity extends AppCompatActivity {
    public void showShortToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show();
    }

    public void showShortToast(int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }

    public void showLongToast(int res) {
        Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
    }
}
