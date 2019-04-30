package com.youxiake.tinkerdemo;

import android.util.Log;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by lhy on 2019/4/29.
 */
public class JZApplication extends TinkerApplication {

    public JZApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL,
                "com.youxiake.tinkerdemo.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader",
                false);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("init", "初始化数据"+this.getPackageName());
        //自己的逻辑
    }



}
