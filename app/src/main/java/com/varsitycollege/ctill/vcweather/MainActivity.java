package com.varsitycollege.ctill.vcweather;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "URL We Created";
    private ArrayList<Temprature> weatherArraylist = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        URL weatherURL = NetworkUtil.buildURLForWeather();
        Log.i(TAG, "onCreate: URL We Made " + weatherURL);

        new FetchWeatherData().execute(weatherURL);
        listView= findViewById(R.id.weatherList);
    }



    private class FetchWeatherData extends AsyncTask<URL,Void,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        // do in background will make an array of type URL called urls ... = varargs
        @Override
        protected String doInBackground(URL... urls)
        {
            //storing our url in the array
            URL weatherUrl = urls[0];
            String weatherSearchResults = null;


                try {
                    weatherSearchResults = NetworkUtil.getResponseFromHttpUrl(weatherUrl);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            Log.i(TAG, "doInBackground: WeatherSerachResultContains" + weatherSearchResults);

            return weatherSearchResults;
        }

        @Override
        protected void onPostExecute(String weatherSearchResults) {

            if (weatherSearchResults!= null && !weatherSearchResults.equals(""))
            {
                weatherArraylist = ParseJSON(weatherSearchResults);

            super.onPostExecute(weatherSearchResults);
        }
    }


}

        private ArrayList<Temprature> ParseJSON(String weatherSearchResults)
         {
             if(weatherArraylist != null)
             {
                 weatherArraylist.clear();
             }
             if (weatherSearchResults!= null)
             {
                 try {

                     JSONObject rootObject = new JSONObject(weatherSearchResults);
                     JSONArray apiResults = rootObject.getJSONArray("DailyForecasts");

                     for (int i = 0; i < apiResults.length(); i++)
                     {
                         Temprature temprature = new Temprature();
                         JSONObject apiResultsObject = apiResults.getJSONObject(i);
                         String date = apiResultsObject.getString("Date");
                         temprature.setDate(date);
                         Log.i(TAG, "ParseJSON: date" + date);

                         JSONObject tempratureObj = apiResultsObject.getJSONObject("Temperature");
                         String minTemp = tempratureObj.getJSONObject("Minimum").getString("Value");
                         temprature.setMinTemp(minTemp);
                         Log.i(TAG, "MIN: MinTemp"+minTemp);
                         String maxTemp = tempratureObj.getJSONObject("Maximum").getString("Value");
                         temprature.setMaxTemp(maxTemp);
                         Log.i(TAG, "MAX: MaxTemp"+maxTemp);

                         String Link = apiResultsObject.getString("Link");
                         temprature.setLink(Link);
                         Log.i(TAG, "Link"+ Link);


                         weatherArraylist.add(temprature);

                     }

                     if ( weatherArraylist != null)
                     {
                         WeatherArrayAdapter adapter = new WeatherArrayAdapter(this,weatherArraylist);
                         listView.setAdapter(adapter);
                     }

                     return  weatherArraylist;
                     }



                     catch (JSONException e)
                     {
                     e.printStackTrace();
                         Log.i(TAG, "JSONERROR TEST: " +e.getMessage());
                 }
             }

            return null;

         }
    }


