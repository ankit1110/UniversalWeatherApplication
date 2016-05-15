package ohmsoftwaresinc.com.universalweatherapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import ohmsoftwaresinc.com.universalweatherapplication.Adapter.ProductsAdapter;
import ohmsoftwaresinc.com.universalweatherapplication.Model.Example;
import ohmsoftwaresinc.com.universalweatherapplication.Service.WeatherAPI;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SplashScreen extends Activity {


    RecyclerView mRecyclerView;
    ProductsAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.list_data);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        RestAdapter restadapter = new RestAdapter.Builder()
                    .setEndpoint("http://api.openweathermap.org")
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();

        WeatherAPI weatherAPI = restadapter.create(WeatherAPI.class);
        weatherAPI.getWeather("atlanta", "182fb8c97915175c2623f2a0fb3629ff", new Callback<Example>()
        {
            @Override
            public void success(Example weatherClass, Response response)
            {


                mAdapter = new ProductsAdapter(weatherClass,R.layout.card_row,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);
                Toast.makeText(getApplicationContext(),"Hi",Toast.LENGTH_SHORT);


            }

            @Override
            public void failure(RetrofitError error)
            {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT);
            }
        });




    }
}

//http://api.openweathermap.org/data/2.5/forecast?q=atlanta&appid=182fb8c97915175c2623f2a0fb3629ff


//http://api.openweathermap.org/data/2.5/weather?q=atlanta&appid=182fb8c97915175c2623f2a0fb3629ff

//http://api.openweathermap.org/data/2.5/weather?q=Atlanta&appid=182fb8c97915175c2623f2a0fb3629ff
//List<ohmsoftwaresinc.com.universalweatherapplication.Model.List> item = weatherClass.getList();

               /* for(int i=0;i< item.size();i++)
                {
                    ohmsoftwaresinc.com.universalweatherapplication.Model.List data = item.get(i);
                    Log.d("Pressure",data.getMain().getPressure().toString());
                    Log.d("Pressure",data.getDtTxt().toString());

                   // Toast.makeText(getApplicationContext(),data.getSys().getPod().toString(),Toast.LENGTH_SHORT);

                } */

//List<Sys> item = weatherClass.

//String cord = weatherClass.getCoord().getLat().toString();
// Log.d("Logitude",weatherClass.getCoord().getLat().toString());
//  Log.d("Latitude",weatherClass.getCoord().getLon().toString());

// Log.d("Country Name",weatherClass.getSys().getCountry());





               /*
                for(int i=0;i<=item.size();i++)
                {
                    Weather data = item.get(i);
                    Log.d("----------",data.getDescription().toString());
                }*/

