package com.londonappbrewery.climapm;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


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

    }


    // TODO: Add onResume() here:
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(LOGCAT_TAG, "onResume() called");
        getWeatherForCurrentLocation();
    }


    // TODO: Add getWeatherForNewCity(String city) here:



    // TODO: Add getWeatherForCurrentLocation() here:
    private void getWeatherForCurrentLocation() {
        mLocationManager = (LocationManager) getSystemService(Context. LOCATION_SERVICE);
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



    // TODO: Add updateUI() here:



    // TODO: Add onPause() here:



}
