package com.zzg.rxpermissions_master;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openPermissions();
    }

    /**
     * 打开权限
     */
    private void openPermissions(){
        final RxPermissions rxPermissions = new RxPermissions(MainActivity.this); // where this is an Activity or Fragment instance
//        rxPermissions.request(
//                Manifest.permission.CAMERA,
//                Manifest.permission.READ_PHONE_STATE
//        ).subscribe(granted ->{
//            if (granted){
//                Log.d("执行","权限都通过了");
//            }else {
//                Log.d("执行","至少有一个权限被拒绝了");
//                openPermissions();
//            }
//        });
//        rxPermissions.requestEach(
//                Manifest.permission.CAMERA,
//                Manifest.permission.READ_PHONE_STATE
//        ).subscribe(permission -> {
//           if (permission.granted){
//                    Log.d("执行","权限都通过了");
//                    //有个问题，如果第一回全部禁止，第二回把第一个禁止，然后开启第二个，会直接省略掉第一个
//                }else if (permission.shouldShowRequestPermissionRationale){
//                    Log.d("执行","至少有一个权限被拒绝了");
//                    openPermissions();
//                }else {
//                    Log.d("执行","转到设置");
//                }
//        });
//        rxPermissions.requestEachCombined(
//                Manifest.permission.CAMERA,
//                Manifest.permission.READ_PHONE_STATE
//        ).subscribe(permission -> {
//           if (permission.granted){
//                    Log.d("执行","权限都通过了");
//                }else if (permission.shouldShowRequestPermissionRationale){
//                    Log.d("执行","至少有一个权限被拒绝了");
//                    openPermissions();
//                }else {
//                    Log.d("执行","转到设置");
//                }
//        });

        rxPermissions.requestEachCombined(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_CONTACTS
        ).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted){
                    Log.d("执行","权限都通过了");
                }else if (permission.shouldShowRequestPermissionRationale){
                    Log.d("执行","至少有一个权限被拒绝了");
                    openPermissions();
                }else {
                    Log.d("执行","转到设置");
                }
            }
        });

    }
}
