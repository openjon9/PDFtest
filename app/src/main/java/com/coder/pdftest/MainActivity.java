package com.coder.pdftest;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;

public class MainActivity extends AppCompatActivity {

    private String pdfPath2 = "https://docs.google.com/viewer?url=";
    private WebView webview;
    private String TAG = "filePath";
    private PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pdfView = (PDFView) findViewById(R.id.pdfView);

    }

    public void openPDF(View view) {

        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 100);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //  Uri uri = data.getData();
        // Log.d(TAG, "uri:" + uri);

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                try {

                    Uri uri = data.getData();

                    Log.d(TAG, "uri:" + uri);
                    Log.d(TAG, "uri:" + uri.toString());
                    String path = getRealPathFromURI(uri);

                    Log.d(TAG, "path:" + path);
                    // webview.loadUrl(pdfPath2+uri);
                    //  startActivity(FileUtils.getPdfFileIntent());

                    pdfView.fromUri(uri)
                            .defaultPage(0)
                            .onPageChange(new OnPageChangeListener() {
                                @Override
                                public void onPageChanged(int page, int pageCount) {
                                    //  Toast.makeText(MainActivity.this, "第" + page + "頁", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .enableAnnotationRendering(true)
                            .swipeHorizontal(false)
                            .onPageError(new OnPageErrorListener() {
                                @Override
                                public void onPageError(int page, Throwable t) {

                                }
                            }).load();
                } catch (Exception e) {

                }
            }
        }

    }

    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (null != cursor && cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }


    public void webviewopenPDF(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }
}
