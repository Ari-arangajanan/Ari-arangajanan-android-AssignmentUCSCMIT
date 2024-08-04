package com.ucsc.assignment08;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);


        Button buttonLocal = findViewById(R.id.button_local);
        buttonLocal.setOnClickListener(v -> openLocalFile());

        Button buttonUrl = findViewById(R.id.button_url);
        buttonUrl.setOnClickListener(v -> openUrlDialog());

        // Handle the back button press
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish(); // or call super.onBackPressed() if available
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void openLocalFile() {
        // Load a local file in the assets folder
        String localFilePath = "file:///android_asset/sample.html";
        Log.d(TAG, "Loading local file: " + localFilePath);
        webView.loadUrl(localFilePath);

    }

    private void openUrlDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter URL");

        final EditText input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String url = input.getText().toString().trim();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            webView.loadUrl(url);
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            Log.d(TAG, "Loading URL in WebView: " + request.getUrl().toString());
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.e(TAG, "Error loading URL: " + request.getUrl().toString() + ", error: " + error.getDescription().toString());
            }
            super.onReceivedError(view, request, error);
        }


        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            Log.e(TAG, "Error loading URL: " + failingUrl + ", error: " + description);
            super.onReceivedError(view, errorCode, description, failingUrl);
        }
    }
}