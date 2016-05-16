package ohmsoftwaresinc.com.universalweatherapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ohmsoftwaresinc.com.universalweatherapplication.Adapter.ProductsAdapter;
import ohmsoftwaresinc.com.universalweatherapplication.Model.Example;
import ohmsoftwaresinc.com.universalweatherapplication.Service.WeatherAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    ProductsAdapter mAdapter;
    TextView countryname,cityid,longitute,lat,cityname;
    EditText ed_cityname,not_use;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);

        countryname = (TextView)findViewById(R.id.tv_country_name);
        cityid = (TextView)findViewById(R.id.tv_cityid);
        longitute = (TextView)findViewById(R.id.tv_long);
        lat = (TextView)findViewById(R.id.tv_lat);
        cityname = (TextView)findViewById(R.id.tv_city_name);


        ed_cityname=(EditText)findViewById(R.id.ed_cityname);
        not_use=(EditText)findViewById(R.id.not_use);
       // not_use.clearFocus();

        mRecyclerView = (RecyclerView)findViewById(R.id.list_data);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
       // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();

    }



    public void get_data(View v)
    {
      //  Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();

        RestAdapter restadapter = new RestAdapter.Builder()
                .setEndpoint("http://api.openweathermap.org")
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        WeatherAPI weatherAPI = restadapter.create(WeatherAPI.class);


        weatherAPI.getWeather(ed_cityname.getText().toString(), "182fb8c97915175c2623f2a0fb3629ff", new Callback<Example>()
     //   weatherAPI.getWeather("valsad", "182fb8c97915175c2623f2a0fb3629ff", new Callback<Example>()
        {
            @Override
            public void success(Example weatherClass, Response response)
            {


                // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                mAdapter = new ProductsAdapter(weatherClass,R.layout.card_row,getApplicationContext());
               mRecyclerView.setAdapter(mAdapter);


                cityname.setText(weatherClass.getCity().getName().toString());
                countryname.setText(weatherClass.getCity().getCountry().toString());
                lat.setText(weatherClass.getCity().getCoord().getLat().toString());
                longitute.setText(weatherClass.getCity().getCoord().getLon().toString());
                cityid.setText(weatherClass.getCity().getId().toString());
                ed_cityname.setText("");

                hide_keyboard();


            }

            @Override
            public void failure(RetrofitError error)
            {
                Log.d("-----------------",error.toString());
                Toast.makeText(getApplicationContext(),"Enter Valid City Name",Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void hide_keyboard()
    {
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
