package com.londonappbrewery.climapm;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class WeatherController extends AppCompatActivity {

    // Constants:
    final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather";
    // App ID to use OpenWeather data
    final String APP_ID = "e72____PLEASE_REPLACE_ME_____13";
    // Time between location updates (5000 milliseconds or 5 seconds)
    final long MIN_TIME = 5000;
    // Distance between location updates (1000m or 1km)
    final float MIN_DISTANCE = 1000;

    // TODO: Set LOCATION_PROVIDER here:
    String LOCATION_PROVIDER = LocationManager.GPS_PROVIDER;
    // pela rede de dados LocationManager.NETWORK_PROVIDER
    // Tag de debug
    final String LOGCAT_TAG = "Clima";

    LocationManager mLocationManager;
    LocationListener mLocationListener;



    // Member Variables:
    TextView mCityLabel;
    ImageView mWeatherImage;
    TextView mTemperatureLabel;

    // TODO: Declare a LocationManager and a LocationListener here:


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weather_controller_layout);

        // Linking the elements in the layout to Java code
        mCityLabel = (TextView) findViewById(R.id.locationTV);
        mWeatherImage = (ImageView) findViewById(R.id.weatherSymbolIV);
        mTemperatureLabel = (TextView) findViewById(R.id.tempTV);
        ImageButton changeCityButton = (ImageButton) findViewById(R.id.changeCityButton);

        // TODO: Add an OnClickListener to the changeCityButton here:
        changeCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(WeatherController.this, ChangeCityController.class);
                startActivity(myIntent);
            }
        });

    }


    // TODO: Add onResume() here:
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOGCAT_TAG, "onResume() called");
        Intent myIntent = getIntent();
        String city = myIntent.getStringExtra( "City");
        if(city!=null){
            getWeatherForNewCity(city);
        }else{
            Log.d(LOGCAT_TAG,"Getting weather for current location");
            getWeatherForCurrentLocation();
        }
    }



    // TODO: Add getWeatherForNewCity(String city) here:



    // TODO: Add getWeatherForCurrentLocation() here:
    private void getWeatherForCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {
            }
            @Override
            public void onProviderDisabled(String s) {
            }
        };
    }



    // TODO: Add letsDoSomeNetworking(RequestParams params) here:
    private void letsDoSomeNetworking(RequestParams params){
        AsyncHttpClient client = new AsyncHttpClient();
        client.get( WEATHER_URL,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers,
                                  JSONObject response) {
                Log. d(LOGCAT_TAG,"Sucess! JSON: "+response.toString());
                WeatherDataModel weatherData = WeatherDataModel.fromJson(response);
                updateUI(weatherData);
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable
                    throwable, JSONObject errorResponse) {
                Log. e(LOGCAT_TAG,"Fail "+throwable.toString());
                Log. d(LOGCAT_TAG,"Status code "+ statusCode);
            }
        });
    }



    // TODO: Add updateUI() here:
    private void updateUI(WeatherDataModel weatherData){
        mCityLabel.setText(weatherData.getCity());
        mTemperatureLabel.setText(weatherData.getTemperature());
        int resourceID =
                getResources().getIdentifier(weatherData.getIconName(), "drawable",getPackageName(
                ));
        mWeatherImage.setImageResource(resourceID);
    }



    // TODO: Add onPause() here:
    @Override
    protected void onPause() {
        super.onPause();
        if(mLocationManager != null) mLocationManager.removeUpdates( mLocationListener);
    }



}
