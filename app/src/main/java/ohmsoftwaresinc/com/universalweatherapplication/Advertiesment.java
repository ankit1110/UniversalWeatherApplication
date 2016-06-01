package ohmsoftwaresinc.com.universalweatherapplication;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import org.w3c.dom.Text;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Advertiesment extends AppCompatActivity {
        //MobileAds.initialize(this, "ca-app-pub-7221431013965161/7283348538");
    AdView mAdView;
    InterstitialAd mInterstitialAd;
    private InterstitialAd interstitial;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertiesment);

        TextView tv = (TextView)findViewById(R.id.idofphone);
        //Toast.makeText(Advertiesment.this,device_id.toString(), Toast.LENGTH_SHORT).show();


        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
         String device_id = md5(android_id).toUpperCase();
        tv.setText(device_id);
        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("8A0DCB375B3135995716FF98F7E61440")
                .build();
        adView.loadAd(adRequest);


      //  mAdView = (AdView) findViewById(R.id.adView);
      //  AdRequest adRequest = new AdRequest.Builder()
       //         .build();
       // mAdView.loadAd(adRequest);
       // mAdView.setVisibility(View.VISIBLE);

      //  AdRequest adRequest = new AdRequest.Builder().build();
       // AdView adView = (AdView)this.findViewById(R.id.adView);
       // adView.loadAd(adRequest);

    }
    public String md5(String s) {
        try {

            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();


            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void next_activity(View v)
    {
        Intent i = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(i);
    }
}
