package com.telivergrocerydriver;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.multidex.MultiDexApplication;
import android.util.AttributeSet;
import android.widget.TextView;

import com.teliver.sdk.core.Teliver;


public class Application extends MultiDexApplication {

    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        super.onCreate();
        Teliver.init(this,"teliver_key");
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }


    public void init(TextView txtView, Context context, AttributeSet attrs) {
        try {
            Typeface typeface = getCustomFont(context, attrs);
            if (typeface != null)
                txtView.setTypeface(typeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Typeface getCustomFont(Context context, AttributeSet attrs) {
        Typeface typeface = null;
        try {
            TypedArray typedArray = context.obtainStyledAttributes(attrs,
                    R.styleable.CustomWidget);
            for (int i = 0, count = typedArray.getIndexCount(); i < count; i++) {
                int attribute = typedArray.getIndex(i);
                if (attribute == R.styleable.CustomWidget_font_name) {
                    typeface = Typeface.createFromAsset(context.getResources()
                            .getAssets(), typedArray.getString(attribute));
                }
            }
            typedArray.recycle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return typeface;
    }


    public void storeBooleanInPref(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }


    public boolean getBooleanInPef(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void deletePreference(){
        editor.clear();
        editor.commit();
    }
}
