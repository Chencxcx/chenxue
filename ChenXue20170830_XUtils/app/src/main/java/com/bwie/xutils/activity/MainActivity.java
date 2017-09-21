package com.bwie.xutils.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bwie.xutils.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

//加载activity布局
@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewInject(R.id.load_pic)
    Button load_pic;
    @ViewInject(R.id.pic)
    ImageView pic;
    @ViewInject(R.id.pb)
    ProgressBar pb;
    String url = "http://www.93.gov.cn/93app/data.do";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //设置xutils注解
        x.view().inject(this);
    }
    //Xutils注册点击事件
    @Event(value = {R.id.load_pic,R.id.http_post,R.id.upload,R.id.download})
    private void click(View view){
        switch (view.getId()){
            case R.id.load_pic:
                String path = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=729991163,92764446&fm=11&gp=0.jpg";
                //点击加载图片
                ImageOptions options = new ImageOptions.Builder()
                        .setLoadingDrawableId(R.mipmap.ic_launcher)
                        .setCircular(true)
                        .build();
                x.image().bind(pic,path,options);
                break;
            case R.id.http_post:
                RequestParams params = new RequestParams(url);
                params.addBodyParameter("channelId","0");
                params.addBodyParameter("startNum","0");
                x.http().post(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(MainActivity.this, "post请求成功"+result, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {

                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
                break;
            case R.id.upload:
                //上传
                upFile();
                break;
            case R.id.download:
                //下载
                downLoad();
                break;
        }
    }

    //注册长按事件
    @Event(type = View.OnLongClickListener.class,value = R.id.http_get)
    private boolean longClick(View view){
        RequestParams params = new RequestParams(url);
        params.addParameter("channelId","1");
        params.addParameter("startNum","1");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Toast.makeText(MainActivity.this, "get请求成功"+result, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
        return true;
    }

    //上传文件的方法
    private void upFile() {
        File file = new File("/mnt/sdcard/xzq.mkv");
        String uri = "http://169.254.26.25:8080/08web/FileUploadServlet";
        RequestParams params = new RequestParams(uri);
        params.addBodyParameter("file",file);
        params.setMultipart(true);
        x.http().post(params, new Callback.CommonCallback<String>() {

            @Override
            public void onSuccess(String result) {
                Toast.makeText(MainActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
    //下载文件的方法
    private void downLoad() {
        pb.setVisibility(View.VISIBLE);
        String url = "http://imtt.dd.qq.com/16891/4AC03B9C122D51E5867B6D90F50280B9.apk?fsname=com.tencent.androidqqmail_5.3.4_10126683.apk&csr=97c2";
        RequestParams params = new RequestParams(url);
        //设置允许命名
        params.setAutoRename(true);
        //设置下载的位置
        params.setSaveFilePath("/mnt/sdcard/apk");
        x.http().get(params, new Callback.CommonCallback<File>() {
            @Override
            public void onSuccess(File result) {
                Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                //下载成功进行安装
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(result),"application/vnd.android.package-archive");
                startActivity(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
