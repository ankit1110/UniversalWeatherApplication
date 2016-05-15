package ohmsoftwaresinc.com.universalweatherapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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