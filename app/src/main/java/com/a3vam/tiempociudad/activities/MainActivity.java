package com.a3vam.tiempociudad.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.a3vam.tiempociudad.Contanst;
import com.a3vam.tiempociudad.R;
import com.a3vam.tiempociudad.model.RootObject;
import com.a3vam.tiempociudad.model.Weather;
import com.a3vam.tiempociudad.services.ServiceManager;
import com.a3vam.tiempociudad.services.callbacks.CallbackOpenMap;
import com.a3vam.tiempociudad.utils.BaseUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.places.Places;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "MainActivity";
    private GoogleApiClient mGoogleApiClient;
    private double mLatitude;
    private double mLongitude;
    private LocationRequest mLocationRequest;
    private Location mLastLocation;
    private Spinner spinnerCiudades;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    BaseUtils utils;
    ArrayList<com.a3vam.tiempociudad.model.List> listaCiudades;






    private Button btnShowLocation;
    private TextView lblLocation;
    private TextView tvCitytext;
    private  TextView tvcondDescr;
    private  TextView tvTemp;
    private  TextView tvPress;
    private  TextView tvHum;
    private  TextView tvWindspeed;
    private  TextView tvwindDeg;
    private  Button btnPronostico;
    private ImageView ivCodIcon;
    private String mIdCity2go;





    //private static final int  MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 2;


    @Override
    protected void onStart() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


           // Toast.makeText(this,"No posee permisos.", Toast.LENGTH_SHORT).show();

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    Contanst.MY_PERMISSIONS_REQUEST);


            return;
        }
        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        }

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
        utils= new BaseUtils(this);

        if(!utils.checkPlayServices(this)){
            return;
        }

        createLocationRequest();
        initGoogleApi();





    }

    private void initGoogleApi() {
        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .addApi(Places.GEO_DATA_API)
                    .addApi(Places.PLACE_DETECTION_API)
                    .build();
        }

        if (mGoogleApiClient!=null) {
            mGoogleApiClient.connect();
        }

    }

    private void initViews()
    {
        btnShowLocation = (Button) findViewById(R.id.btnShowLocation);

        spinnerCiudades = (Spinner)findViewById(R.id.spinner);
        tvCitytext = (TextView)findViewById(R.id.cityText);
        tvcondDescr = (TextView)findViewById(R.id.condDescr);
        tvTemp = (TextView)findViewById(R.id.temp);;

        tvPress = (TextView)findViewById(R.id.press);;
        tvHum = (TextView)findViewById(R.id.hum);;
        tvWindspeed = (TextView)findViewById(R.id.windSpeed);;
        tvwindDeg = (TextView)findViewById(R.id.windDeg);
        btnPronostico = (Button)findViewById(R.id.btnPronostico);
        btnPronostico.setClickable(false);
        ivCodIcon = (ImageView)findViewById(R.id.condIcon);


    }



    private void initListeners()
    {
        // Show location button click listener
        btnShowLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String ciudad = getCiudad(mLatitude,mLongitude);
                final RootObject[] objectValue = new RootObject[1];

                tvCitytext.setText("Lat: " + String.valueOf( mLatitude) + " - Lon: " + String.valueOf(mLongitude));

                showProgressDialog(false);
                ServiceManager.getCiudades(mLatitude, mLongitude, new CallbackOpenMap() {
               @Override
               public void onSuccess(RootObject value) {

                   objectValue[0] = value;

                   listaCiudades =  objectValue[0].getList();
                   ArrayList<String> arrayciudades = new ArrayList<String>();

                   for( com.a3vam.tiempociudad.model.List elemento : listaCiudades )
                   {
                       arrayciudades.add(elemento.getName());
                   }



                   ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_spinner_item, arrayciudades);
                   adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                   spinnerCiudades.setAdapter(adapter);

                   hideProgressDialog();

               }

               @Override
               public void onError(int errorCode, String errorMessage) {
                   Toast.makeText(MainActivity.this,errorMessage,Toast.LENGTH_SHORT).show();

                   hideProgressDialog();
               }
           });




            }
        });



        spinnerCiudades.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                com.a3vam.tiempociudad.model.List city = listaCiudades.get(i);
               // tvCitytext.setText(city.getName());
                tvTemp.setText( String.valueOf(city.getMain().getTemp()));
                ArrayList<Weather> tiempo = city.getWeather();
                tvcondDescr.setText(city.getWeather().get(0).getDescription());
                tvHum.setText(String.valueOf( city.getMain().getHumidity()));
                tvPress.setText(String.valueOf( city.getMain().getPressure() ));
                tvWindspeed.setText(String.valueOf(city.getWind().getSpeed()));
                tvwindDeg.setText(String.valueOf(city.getWind().getDeg()));
                btnPronostico.setClickable(true);
                mIdCity2go = String.valueOf(city.getId());

                Picasso.with(MainActivity.this).load(Contanst.URL_OPENweatherIcons + city.getWeather().get(0).getIcon() + ".png").resize(100,100).into(ivCodIcon);


                //tvcondDescr.setText(tiempo);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnPronostico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,Pronostico.class);
                intent.putExtra("id",mIdCity2go);
                intent.putExtra("nombreciudad",spinnerCiudades.getSelectedItem().toString());
                startActivity(intent);
                //finish();
            }
        });

    }

    private void displayLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (mLastLocation != null) {
            double latitude = mLastLocation.getLatitude();
            double longitude = mLastLocation.getLongitude();

            lblLocation.setText(latitude + ", " + longitude);

        } else {

            lblLocation
                    .setText("(Couldn't get the location. Make sure location is enabled on the device)");
        }
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        mLongitude =  location.getLongitude();
        mLatitude = location.getLatitude();
        if (location == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates (mGoogleApiClient, mLocationRequest, (LocationListener) this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
                + connectionResult.getErrorCode() +" - " +  connectionResult.getErrorMessage());
    }



    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(Contanst.UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(Contanst.FATEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

    }



    public String getCiudad(double lattitude, double longitude) {

        String cityName = "No encontrada";

        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {

            List<Address> addresses = gcd.getFromLocation(lattitude, longitude,
                    1000);



            for (Address adrs : addresses) {
                if (adrs != null) {

                    String city = adrs.getLocality();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                        break;

                    } else {

                    }
                    // // you should also try with addresses.get(0).toSring();

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Contanst.MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


}
