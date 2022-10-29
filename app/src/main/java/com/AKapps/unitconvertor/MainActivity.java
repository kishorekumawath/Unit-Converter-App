package com.AKapps.unitconvertor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.AKapps.unitconvertor.adapter.converter_type_adapter;
import com.AKapps.unitconvertor.interfaces.onclickConverterType;
import com.AKapps.unitconvertor.model.converter_type;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    converter_type_adapter adapter ;// this is custom adapter
    RecyclerView ConverterRV;
    ArrayList<converter_type> list = new ArrayList<>();//this ArrayList is for recyclerview
    ArrayList<String> GettingData = new ArrayList<String>();//this ArrayList is used for sending array data to next activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // getting the recycler view id
        ConverterRV = findViewById(R.id.ConverterTypes);

        //getting the Unit converter list data;
        list = getdata();

        // setting adapter to recycler view
        adapter = new converter_type_adapter(list,this,onclickConverterType);
        ConverterRV.setLayoutManager(new GridLayoutManager(this,2, LinearLayoutManager.VERTICAL,false));
        ConverterRV.setAdapter(adapter);

    }

    private ArrayList<converter_type> getdata() {
        ArrayList<converter_type> new_list = new ArrayList<>();
        new_list.add(new converter_type(R.drawable.weight,"Weight"));
        new_list.add(new converter_type(R.drawable.area,"Area"));
        new_list.add(new converter_type(R.drawable.length,"Length"));
        new_list.add(new converter_type(R.drawable.hot,"Temperature"));
        new_list.add(new converter_type(R.drawable.volume,"Volume"));
        new_list.add(new converter_type(R.drawable.time,"Time"));
        new_list.add(new converter_type(R.drawable.comingsoon,"Remaining Converters"));
        return new_list;
    }

    // this interface is used when user click the list item data in main activity
    // In this interface it sends the required data to converter_activity
    public final onclickConverterType onclickConverterType = new onclickConverterType() {
        @Override
        public void onclick(converter_type converter_type) {
            Bundle bundle = new Bundle();
            // the bundle is passed to next activity
            // if user click on list item in main activity, according to that type the data bundle will send to the next activity
            // as example if user click on Area item view the area data will be passed to the converter_activity
            if (converter_type.getName() == "Length"){
                GettingData = getLengthData();
                Intent intent = new Intent(MainActivity.this,converter_activity.class);
                bundle.putSerializable("ArrayList",(Serializable)GettingData);
                intent.putExtra("ListOfWeight",bundle);
                intent.putExtra("NameOfConverter",converter_type.getName());
                startActivity(intent);
            }
            if (converter_type.getName() == "Area"){
                GettingData  = getAreaData();
                Intent intent = new Intent(MainActivity.this,converter_activity.class);
                bundle.putSerializable("ArrayList",(Serializable)GettingData);
                intent.putExtra("ListOfWeight",bundle);
                intent.putExtra("NameOfConverter",converter_type.getName());
                startActivity(intent);
            }
            if (converter_type.getName() == "Weight"){
                GettingData  = getWeightData();
                Intent intent = new Intent(MainActivity.this,converter_activity.class);
                bundle.putSerializable("ArrayList",(Serializable)GettingData);
                intent.putExtra("ListOfWeight",bundle);
                intent.putExtra("NameOfConverter",converter_type.getName());
                startActivity(intent);
            }
            if (converter_type.getName() == "Temperature"){
                GettingData  = getTemperatureData();
                Intent intent = new Intent(MainActivity.this,converter_activity.class);
                bundle.putSerializable("ArrayList",(Serializable)GettingData);
                intent.putExtra("ListOfWeight",bundle);
                intent.putExtra("NameOfConverter",converter_type.getName());
                startActivity(intent);
            }
            if (converter_type.getName() == "Volume"){
                GettingData  = getVolumeData();
                Intent intent = new Intent(MainActivity.this,converter_activity.class);
                bundle.putSerializable("ArrayList",(Serializable)GettingData);
                intent.putExtra("ListOfWeight",bundle);
                intent.putExtra("NameOfConverter",converter_type.getName());
                startActivity(intent);
            }
            if (converter_type.getName() == "Time"){
                GettingData  = getTimeData();
                Intent intent = new Intent(MainActivity.this,converter_activity.class);
                bundle.putSerializable("ArrayList",(Serializable)GettingData);
                intent.putExtra("ListOfWeight",bundle);
                intent.putExtra("NameOfConverter",converter_type.getName());
                startActivity(intent);
            }

        }
    };

    // here below function are used to setting required data in GettingData ArrayList( which declared in global )
    private ArrayList<String> getLengthData() {
        ArrayList<String> new_list = new ArrayList<>();
        new_list.add("Kilometer");
        new_list.add("Meter");
        new_list.add("Centimeter");
        new_list.add("Millimeter");
        new_list.add("Micrometer");
        new_list.add("Nanometer");
        new_list.add("Mile");
        new_list.add("Yard");
        new_list.add("Foot");
        new_list.add("Inch");
        new_list.add("Light Year");
        return new_list;
    }
    private ArrayList<String> getAreaData() {
        ArrayList<String> new_list = new ArrayList<>();
        new_list.add("Square Kilometer");
        new_list.add("Square Meter");
        new_list.add("Square Centimeter");
        new_list.add("Square Millimeter");
        new_list.add("Square Micrometer");
        new_list.add("Hectare");
        new_list.add("Square Mile");
        new_list.add("Square Yard");
        new_list.add("Square Foot");
        new_list.add("Square Inch");
        new_list.add("Acre");
        return new_list;
    }
    private ArrayList<String> getWeightData() {
        ArrayList<String> new_list = new ArrayList<>();
        new_list.add("Kilogram");
        new_list.add("Gram");
        new_list.add("Milligram");
        new_list.add("Metric Ton");
        new_list.add("Long Ton");
        new_list.add("Short Ton");
        new_list.add("Pound");
        new_list.add("Ounce");
        new_list.add("Carrat");
        new_list.add("Atomic Mass Unit");
        return new_list;
    }
    private ArrayList<String> getTemperatureData() {
        ArrayList<String> new_list = new ArrayList<>();
        new_list.add("Celsius");
        new_list.add("Kelvin");
        new_list.add("Fahrenheit");
        return new_list;
    }
    private ArrayList<String> getVolumeData() {
        ArrayList<String> new_list = new ArrayList<>();
        new_list.add("Cubic Kilometer");
        new_list.add("Cubic Meter");
        new_list.add("Cubic Centimeter");
        new_list.add("Cubic Millimeter");
        new_list.add("Liter");
        new_list.add("Milliliter");
        new_list.add("Cubic Mile");
        new_list.add("Cubic Yard");
        new_list.add("Cubic Foot");
        new_list.add("Cubic Inch");
        return new_list;
    }
    private ArrayList<String> getTimeData() {
        ArrayList<String> new_list = new ArrayList<>();
        new_list.add("Second");
        new_list.add("Millisecond");
        new_list.add("Microsecond");
        new_list.add("Nanosecond");
        new_list.add("Picosecond");
        new_list.add("Minute");
        new_list.add("Hour");
        new_list.add("Day");
        new_list.add("Week");
        new_list.add("Month");
        new_list.add("Year");
        return new_list;
    }


}