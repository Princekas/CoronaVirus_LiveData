package com.prince.coronavirus_livedata;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LiveData extends AppCompatActivity {
    WebView web;
    ProgressBar progressBar;
    TextView text;
    private AdView adView1;
    private AdView adView2;
    private AdView adView3;
    private InterstitialAd interstitialAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_data);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        MobileAds.initialize(this,"ca-app-pub-5987730059541217~8682339331");

        adView1 = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);

        adView2 = findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        adView2.loadAd(adRequest1);
        adView3 = findViewById(R.id.adView2);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        adView3.loadAd(adRequest2);

        interstitialAd= new InterstitialAd(this);
        interstitialAd.setAdUnitId(("ca-app-pub-3940256099942544/8691691433"));
        AdRequest adRequest5 = new AdRequest.Builder().build();
        interstitialAd.loadAd(adRequest5);


        ////////////////////////////////////////////////////////////////////////////////////////////////////////

        text=(TextView)findViewById(R.id.text1);
        web = (WebView) findViewById(R.id.webview1);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);

        web.setWebViewClient(new myWebClient());
        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("https://infographics.channelnewsasia.com/covid-19/map.html");
    }

    public class myWebClient extends WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            // TODO Auto-generated method stub
            progressBar.setVisibility(View.VISIBLE);
            view.loadUrl(url);
            return true;

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
            text.setVisibility(View.GONE);

            progressBar.setVisibility(View.GONE);
            hideapp();
        }
    }
@Override
public  void onBackPressed(){

        if(interstitialAd.isLoaded())
        {
         interstitialAd.show();
         interstitialAd.setAdListener(new AdListener()
         {
             @Override
             public void onAdClosed(){
                 super.onAdClosed();
                 finish();
         }
         });
        }
            else {
            Toast.makeText(this, "no result", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
}


    //////////////////////////////////////////
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();      // it inflate the menu item in the code and its xml file will be too inflated
        menuInflater.inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.Help) {
            Intent mailIntent = new Intent(Intent.ACTION_VIEW);
            Uri data = Uri.parse("mailto:?subject=" + " Subject of Your Query...  " + "&body=" + " Please Write your Query... " + "&to=" + "pkasaudhan93@gmail.com");
            mailIntent.setData(data);
            startActivity(mailIntent);
            return true;
        } else if (item.getItemId() == R.id.About) {

            Intent intent = new Intent(getApplicationContext(), feedback.class);
            startActivity(intent);

            return true;
        } else if (item.getItemId() == R.id.Share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
            else if (item.getItemId() == R.id.setting) {

                Intent intent = new Intent(getApplicationContext(), Important.class);
                startActivity(intent);

                return true;
            }




        return false;
    }
    public void hideapp()
    {
        adView1.setVisibility(View.GONE);
        adView2.setVisibility(View.GONE);
        adView3.setVisibility(View.GONE);


    }


}
