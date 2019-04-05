package com.interview.weather;

import android.os.Bundle;
import android.util.Log;

import com.interview.weather.adapter.CountriesAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = MainActivity.class.getSimpleName();

  private WeatherClient weatherClient;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    weatherClient = new WeatherClientImpl();
    setContentView(R.layout.activity_main);

    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    HashMap<String, List<String>> myCountries = new HashMap<>();

    try {

      // Obtenemos la lista de las ciudades del Servicio
      List<City> myCities = weatherClient.getCities().cities;
      List<Object> myObjectList = new ArrayList<>();

      // Recorremos la lista de ciudades para limpiar repetidos y crear un Hashmap
      for (City myCity : myCities) {
        if (myCountries.containsKey(myCity.countryName)) {
          List<String> cityList = myCountries.get(myCity.countryName);
          if (!cityList.contains(myCity.name)) {
            cityList.add(myCity.name);
          }
          myCountries.put(myCity.countryName, cityList);
        } else {
          List<String> cityList = new ArrayList<>();
          cityList.add(myCity.name);
          myCountries.put(myCity.countryName, cityList);
        }
      }

      // Ordenamos el Hashmap
      Map mapOrdenado = new TreeMap(myCountries);
      Set ref  = mapOrdenado.keySet();
      Iterator it = ref.iterator();
      while (it.hasNext()) {
        String country = (String) it.next();
        // Creamos una nueva lista para el Recyclerview
        myObjectList.add(country);
        myObjectList.add(myCountries.get(country));
      }

      CountriesAdapter countriesAdapter = new CountriesAdapter(this, myObjectList);
      recyclerView.setAdapter(countriesAdapter);
      recyclerView.setLayoutManager(linearLayoutManager);
    }
    catch(final IOException e) {
      Log.e(TAG, "Error on getCities", e);
    }
  }



}
