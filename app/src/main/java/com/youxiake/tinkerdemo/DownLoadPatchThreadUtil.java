package com.youxiake.tinkerdemo;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2017/2/20.
 */

public class DownLoadPatchThreadUtil {

    private String commonPath = "";
    private String newFileAllPath = "";
    private String apatchName = "";

    public DownLoadPatchThreadUtil(Context context) {
        apatchName = "jz_patch" + BuildConfig.VERSION_NAME.replace(".", "");
        commonPath = FileDirectoryUtil.getOwnCacheDirectory(context) + "/tinker/";
        newFileAllPath = commonPath + apatchName+".apk";
        Log.i("DownLoadPatchThread", "" + newFileAllPath);
    }


    public String getNewFileAllPath() {
        return newFileAllPath;
    }


    /**
     * 选择下载提示框 SD卡存在并可用，启动下载线程
     */
    public void downLoadIng() {
        if (!TextUtils.isEmpty(newFileAllPath)) {
            DownLoad();
        }
    }


    /**
     * 启动下载线程
     */
    private void DownLoad() {
        DownThread thread = new DownThread();
        thread.start();
    }


    /**
     * 下载线程内部类
     *
     * @author Administrator
     */
    private class DownThread extends Thread {

        @Override
        public void run() {
            super.run();
            String urlStr = "http://192.168.69.250/patch/" + apatchName;

            try {
                Log.i("DownLoadPatchThread", "正在下载中" + urlStr);
                // 构造URL
                URL url = new URL(urlStr);
                // 打开连接
                URLConnection con = url.openConnection();
                if (con != null) {
                    // 输入流
                    InputStream is = con.getInputStream();
                    File filePath = new File(commonPath);
                    if (!filePath.exists()) {
                        filePath.mkdirs();
                    }
                    File file = new File(newFileAllPath);
                    if (file.exists()) {
                        Log.i("DownLoadPatchThread", "文件已存在");
                        return;
                    } else {
                        file.createNewFile();
                    }
                    // 1K的数据缓冲
                    byte[] bs = new byte[1024];
                    // 读取到的数据长度
                    int len;
                    // 输出的文件流
                    if (!TextUtils.isEmpty(newFileAllPath)) {

                        OutputStream os = new FileOutputStream(newFileAllPath);
                        // 开始读取
                        while ((len = is.read(bs)) != -1) {
                            os.write(bs, 0, len);
                        }
                        // 完毕，关闭所有链接
                        os.close();
                    }
                    is.close();
                    Log.i("DownLoadPatchThread", "下载完毕");

                    //如果目标文件已经存在，则删除。产生覆盖旧文件的效果
                    if (file.exists()) {
                        TinkerManager.getInstance().addPatch(file.getAbsolutePath());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}


