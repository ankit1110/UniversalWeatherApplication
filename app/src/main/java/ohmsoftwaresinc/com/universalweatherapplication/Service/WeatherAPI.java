package ohmsoftwaresinc.com.universalweatherapplication.Service;

import ohmsoftwaresinc.com.universalweatherapplication.Model.Example;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Ankitkumar on 5/14/2016.
 */
public interface WeatherAPI
{
//http://api.openweathermap.org/data/2.5/forecast?q=Atlanta&appid=182fb8c97915175c2623f2a0fb3629ff

    //http://api.openweathermap.org/data/2.5/weather?q=atlanta,usa&appid=2de143494c0b295cca9337e1e96b00e0
    @GET("/data/2.5/forecast")
    //public void getWeather(@Query("q") String name, @Query ("appid")String key , Callback<List<WeatherClass>> response);

    public void getWeather(@Query("q") String name,@Query ("appid")String key , Callback<Example> response);

}
