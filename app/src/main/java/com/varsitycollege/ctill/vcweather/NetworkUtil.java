package com.varsitycollege.ctill.vcweather;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil
{

    /*the  network utility class will be used to build a URL which we will append our API KEY and
parameters to in order to issue a GET request which will allow us to pull and entire JSON array from
AcuWeather*/


/* make sure you include the city key from the locations API in order to search for the 5 day
forecast in Durban
 */
    public static final String TAG ="Network Utility Class";

    private static final String WEATHERDB_BASE_URL = "http://dataservice.accuweather.com/" +
            "forecasts/v1/daily/5day/305605";

    // Copy in your API key give to you by ACW

    private static final String METRIC_VALUE ="true";

    private static final String  API_KEY = "U6Gg58EaVvWAs7LGVb6cqyUT7gA5ARWJ";

// we need to append the api key parameter to our URL so that ACW knows what we are looking for
    private static final String  PARAM_API_KEY ="apikey";

    private static final String PARAM_METRIC ="metric";

    //method to build our URL

    public static URL buildURLForWeather()
    { /* Take hte string BaseURL and convert it to a URI , add to that URI the Pamp API Key and
     Api key to build one solid URI*/

        Uri builtUri = Uri.parse(WEATHERDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .appendQueryParameter(PARAM_METRIC,METRIC_VALUE)
                .build();

        // now we use our URI (the who) to create the URL (Who and Where)

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // we just output our URL so that we can double check it and find our errors easily
        Log.i(TAG, "buildURLForWeather: builtURLMethod" + url);

        return url;
    }

    /* we now need to use our URL to connect to ACW and pull our JSON we need to return if
    we have managed to make a successful connection AND if our JSON arry contains data.
     */

    public static String getResponseFromHttpUrl(URL url) throws IOException
    {
        // create  and open the connection

        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("//A");
            boolean hasInput = scanner.hasNext();

            if(hasInput)
            {
                return scanner.next();
            }
            else
            {
                return null;
            }
        }

        finally {
            urlConnection.disconnect();
        }

    }

}
