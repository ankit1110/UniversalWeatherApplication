package ohmsoftwaresinc.com.universalweatherapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //show_dialogue();


    }

    public void show_dialogue()

    {

        AlertDialog alertDialog1 = new AlertDialog.Builder(
                 this).create();
                // Setting Dialog Title
        alertDialog1.setTitle("Developer Blog");
                // Setting Dialog Message
         alertDialog1.setMessage("Tejal Ankitkumar Rana , Poland");

         // Setting Icon to Dialog
         alertDialog1.setIcon(R.mipmap.tejal);

        // Setting OK Button
        alertDialog1.setButton("OK", new DialogInterface.OnClickListener() {

              public void onClick(DialogInterface dialog, int which) {
             // Write your code here to execute after dialog
             // closed
             Toast.makeText(getApplicationContext(),
                    "You clicked on OK", Toast.LENGTH_SHORT).show();
         }
         });
                // Showing Alert Message
         alertDialog1.show();


    }

    public void get_data(View v)
    {

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


                // Toast.makeText(getApplicationContext(),"",Toast.LENGTH_SHORT).show();
                mAdapter = new ProductsAdapter(weatherClass,R.layout.card_row,getApplicationContext());
                mRecyclerView.setAdapter(mAdapter);




            }

            @Override
            public void failure(RetrofitError error)
            {

            }
        });

    }

}
