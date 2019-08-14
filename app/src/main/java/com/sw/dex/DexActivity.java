package com.sw.dex;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class DexActivity extends AppCompatActivity {
    public static final String SHOWSTRINGCLASS_PATH = "com.isw.playsdk.demo.activity.hello";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMyDex();
    }

    private void loadMyDex() {
        File cacheFile = FileUtils.getCacheDir(getApplicationContext());
        String internalPath = cacheFile.getAbsolutePath() + File.separator + "hello.dex";
        File desFile = new File(internalPath);
        try {
            if (!desFile.exists()) {
                desFile.createNewFile();
                FileUtils.copyFiles(this, "hello.dex", desFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        DexClassLoader dexClassLoader = new DexClassLoader(internalPath, cacheFile.getAbsolutePath(), null, getClassLoader());
        Class dexClazz;
        try {
            dexClazz = dexClassLoader.loadClass(SHOWSTRINGCLASS_PATH);
            Object instance = dexClazz.newInstance();
            Method action = dexClazz.getDeclaredMethod("sayHello");
            String hello = action.invoke(instance).toString();
            Toast.makeText(this, hello, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.i("SIMON", e.getMessage());
        }
    }
}
