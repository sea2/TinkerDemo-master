package com.youxiake.tinkerdemo;

import android.content.Context;

import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Created by lhy on 2019/4/29.
 */
public class TinkerManager {

    private static TinkerManager sInstance;
    private ApplicationLike mApplicationLike;
    private boolean mIsInstall = false;

    private TinkerManager() {
    }

    public static TinkerManager getInstance() {
        if (sInstance == null) {
            synchronized (TinkerManager.class) {
                if (sInstance == null) {
                    sInstance = new TinkerManager();
                }
            }
        }
        return sInstance;
    }

    public void install(ApplicationLike applicationLike) {
        if (!mIsInstall) {
            mApplicationLike = applicationLike;
            TinkerInstaller.install(mApplicationLike);
            mIsInstall = true;
        }
    }

    public void addPatch(String path) {
        if (Tinker.isTinkerInstalled()) {
            TinkerInstaller.onReceiveUpgradePatch(getApplication(), path);
        }
    }

    public Context getApplication() {
        if (mApplicationLike != null) {
            return mApplicationLike.getApplication();
        }
        return null;
    }

}
