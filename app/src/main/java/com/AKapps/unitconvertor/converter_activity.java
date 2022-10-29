package com.AKapps.unitconvertor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.AKapps.unitconvertor.fragrament.from_to_selection_fragment;

import java.util.ArrayList;
import java.util.Objects;

public class converter_activity extends AppCompatActivity {
    TextView toolbar,fromLabel,ToLabel,ResultSection;
    LinearLayout From ,To;
    Button Result;
    EditText FromInput;
    private boolean selectedCheck;
    Integer fromPosition=1,toPosition=0;
    public boolean fromclicked = true,toclicked = true;
    Bundle args;
    ArrayList<String> FromToList = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        //getting id's
        toolbar = findViewById(R.id.title);
        From = findViewById(R.id.from);
        To = findViewById(R.id.To);
        ResultSection = findViewById(R.id.ToValue);
        Result =findViewById(R.id.Result);
        fromLabel = findViewById(R.id.FromLabel);
        ToLabel = findViewById(R.id.ToLabel);
        FromInput = findViewById(R.id.fromValue);
        toolbar.setText(getIntent().getStringExtra("NameOfConverter"));

        //getting bundle provided by previous activity(MainActivity).
        Intent intent = getIntent();
        args = intent.getBundleExtra("ListOfWeight");//gotted data bundle moved to args variable(args is bundle type declared in global var).
        FromToList = (ArrayList<String>) args.getSerializable("ArrayList");

        //now setting the default name to the fromLabel and ToLabel (This are textView).
        fromLabel.setText(FromToList.get(1));
        ToLabel.setText(FromToList.get(0));

        // if user click on from block this method will open the fragment
        From.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCheck = true;
                if (fromclicked){
                    loadFragment(new from_to_selection_fragment());
                    fromclicked = false;
                }
                else {
                    fromclicked = true;
                   removeFragment();
                }

            }
        });

        // if user click on To block this method will open the fragment
        To.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedCheck = false;
                if (toclicked){
                    loadFragment(new from_to_selection_fragment());
                    toclicked = false;
                }
                else {
                    toclicked = true;
                    removeFragment();
                }
            }
        });

        //if user click on Result then method is called,it perform the getting result
        Result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputdata = String.valueOf(FromInput.getText());
                if(inputdata.isEmpty()){
                    FromInput.setError("Required");
                }else {
                    float conInput = Float.parseFloat(inputdata);
                    double answer = 0;
                    if(getIntent().getStringExtra("NameOfConverter").equals("Length")){
                        answer =  Length(fromPosition,toPosition)*conInput;
                    }
                    else if(getIntent().getStringExtra("NameOfConverter").equals("Weight")){
                        answer = Weight(fromPosition,toPosition)*conInput;
                    }
                    else if(getIntent().getStringExtra("NameOfConverter").equals("Area")){
                        answer = Area(fromPosition,toPosition)*conInput;
                    }
                    else if(getIntent().getStringExtra("NameOfConverter").equals("Temperature")){
                        answer = Temperature(fromLabel.getText().toString(),ToLabel.getText().toString(),conInput);
                    }
                    else if(getIntent().getStringExtra("NameOfConverter").equals("Volume")){
                        answer = Volume(fromPosition,toPosition)*conInput;
                    }
                    else if(getIntent().getStringExtra("NameOfConverter").equals("Time")){
                        answer = Time(fromPosition,toPosition)*conInput;
                    }
                    ResultSection.setText(""+answer);
                }
                closeKeyboard();
            }
        });
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    // this method to load a fragment in converter_activity
    private void loadFragment(Fragment fragment){
        fragment.setArguments(args);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.List_fragment,fragment,"yes");
        ft.commit();
    }

    //this method is used when user select the data in from button fragment or To button fragment the selected data will be
    //setted in the fromLabel and ToLabel(this are TextView).
    public void selectedData(String fromTo,int position){
        if(selectedCheck){
            fromLabel.setText(fromTo);
            fromPosition = position;
        }else {
            ToLabel.setText(fromTo);
            toPosition = position;
        }
        removeFragment();
    }

    //this function is used to remove the fragment
    private void removeFragment(){
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("yes");
        if(fragment != null)
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }

    // this function is used to calculate the selected data for result
    public double Length(int a,int b){
        double factor=1;
        boolean reverse=false;
        int small ,big;
        if(a<b){
            big=a;
            small =b;
            reverse = false;
        }
        else {
            small = a;
            big = b;
            reverse = true;
        }
        int []array = new int[]{11,10,9,8,7,6,5,4,3,2,1,0};
        while (big<small){
            if(array[big]==11){
                factor = factor*1000;
            }
            else if(array[big]==10){
                factor = factor*100;
            }
            else if(array[big]==9){
                factor = factor*10;
            }
            else if(array[big]==8){
                factor = factor*1000;
            }
            else if(array[big]==7){
                factor = factor*1000;
            }
            else if(array[big]==6){
                factor = factor*1000;
            }
            else if(array[big]==5){
                factor = factor* 6.213688756E-13 ;
            }
            else if(array[big]==4){
                factor = factor*11760.0065617;
            }
            else if(array[big]==3){
                factor = factor*3;
            }
            else if(array[big]==2){
                factor = factor*12;
            }
            else if(array[big]==1){
                factor = factor* 2.684802117E-18;
            }
            big ++;
        }
        if(reverse ){
            factor = 1/factor;
            return factor;
        }
        else {
            return factor;
        }

    }
    public double Weight(int a,int b){
        double factor=1;
        boolean reverse = false;
        int small ,big;
        if(a<b){
            big=a;
            small =b;
            reverse = false;
        }
        else {
            small = a;
            big = b;
            reverse = true;
        }
        int []array = new int[]{10,9,8,7,6,5,4,3,2,1,0};
        while (big<small){
            if(array[big]==10){
                factor = factor*1000;
            }
            else if(array[big]==9){
                factor = factor*1000;
            }
            else if(array[big]==8){
                factor = factor*9.999999999E-10;
            }
            else if(array[big]==7){
                factor = factor*0.9842073304;
            }
            else if(array[big]==6){
                factor = factor*1.12;
            }
            else if(array[big]==5){
                factor = factor* 2000;
            }
            else if(array[big]==4){
                factor = factor*16;
            }
            else if(array[big]==3){
                factor = factor*141.7475;
            }
            else if(array[big]==2){
                factor = factor*1.20442733E+23;
            }
            else if(array[big]==1){
                factor = factor*1.660540199E-27;
            }
            big ++;
        }
        if(reverse ){
            factor = 1/factor;
            return factor;
        }
        else {
            return factor;
        }

    }
    public double Area(int a,int b){
        double factor=1;
        boolean reverse=false;
        int small ,big;
        if(a<b){
            big=a;
            small =b;
            reverse = false;
        }
        else {
            small = a;
            big = b;
            reverse = true;
        }
        int []array = new int[]{11,10,9,8,7,6,5,4,3,2,1,0};
        while (big<small){
            if(array[big]==11){
                factor = factor*1000000;
            }
            else if(array[big]==10){
                factor = factor*10000;
            }
            else if(array[big]==9){
                factor = factor*100;
            }
            else if(array[big]==8){
                factor = factor*1000000;
            }
            else if(array[big]==7){
                factor = factor*1.E-16;
            }
            else if(array[big]==6){
                factor = factor*0.0038610188;
            }
            else if(array[big]==5){
                factor = factor* 3097602.26 ;
            }
            else if(array[big]==4){
                factor = factor*9;
            }
            else if(array[big]==3){
                factor = factor*144;
            }
            else if(array[big]==2){
                factor = factor*1.594225079E-7;
            }
            else if(array[big]==1){
                factor = factor* 4046.8564224;
            }
            big ++;
        }
        if(reverse ){
            factor = 1/factor;
            return factor;
        }
        else {
            return factor;
        }

    }
    public double Temperature(String a,String b,float data){
        double Fahrenheit, Celsius ,Kelvin;
        if(Objects.equals(a, "Fahrenheit") && Objects.equals(b, "Celsius")){
            Fahrenheit = data;
            Celsius  = ((Fahrenheit-32)*5)/9;
            return Celsius;
        }
        else if(Objects.equals(a, "Fahrenheit") && Objects.equals(b, "Kelvin")){
            Fahrenheit = data;
            Kelvin = (((Fahrenheit-32)*5)/9)+273.15;
            return Kelvin;
        }
        else if(Objects.equals(a, "Celsius") && Objects.equals(b, "Fahrenheit")){
            Celsius = data;
            Fahrenheit = (Celsius * 9/5) + 32;
            return Fahrenheit;
        }
        else if(Objects.equals(a, "Celsius") && Objects.equals(b, "Kelvin")){
            Celsius = data;
            Kelvin = Celsius + 273.15;
            return Kelvin;
        }
        else if(Objects.equals(a, "Kelvin") && Objects.equals(b, "Celsius")){
            Kelvin = data;
            Celsius = Kelvin - 273.15;;
            return Celsius;
        }
        else {
            Kelvin = data;
            Fahrenheit = (Kelvin - 273.15) * 9/5 + 32;
            return Fahrenheit;
        }
    }
    public double Volume(int a,int b){
        double factor=1;
        boolean reverse = false;
        int small ,big;
        if(a<b){
            big=a;
            small =b;
            reverse = false;
        }
        else {
            small = a;
            big = b;
            reverse = true;
        }
        int []array = new int[]{10,9,8,7,6,5,4,3,2,1,0};
        while (big<small){
            if(array[big]==10){
                factor = factor*1000000000;
            }
            else if(array[big]==9){
                factor = factor*1000000;
            }
            else if(array[big]==8){
                factor = factor*1000;
            }
            else if(array[big]==7){
                factor = factor*0.000001;
            }
            else if(array[big]==6){
                factor = factor*1000;
            }
            else if(array[big]==5){
                factor = factor* 2.399128636E-16;
            }
            else if(array[big]==4){
                factor = factor*5451773612.4;
            }
            else if(array[big]==3){
                factor = factor*27;
            }
            else if(array[big]==2){
                factor = factor*1728;
            }
            else if(array[big]==1){
                factor = factor*0.0000163871;
            }
            big ++;
        }
        if(reverse ){
            factor = 1/factor;
            return factor;
        }
        else {
            return factor;
        }

    }
    public double Time(int a,int b){
        double factor=1;
        boolean reverse=false;
        int small ,big;
        if(a<b){
            big=a;
            small =b;
            reverse = false;
        }
        else {
            small = a;
            big = b;
            reverse = true;
        }
        int []array = new int[]{11,10,9,8,7,6,5,4,3,2,1,0};
        while (big<small){
            if(array[big]==11){
                factor = factor*1000;
            }
            else if(array[big]==10){
                factor = factor*1000;
            }
            else if(array[big]==9){
                factor = factor*1000;
            }
            else if(array[big]==8){
                factor = factor*1000;
            }
            else if(array[big]==7){
                factor = factor*1.666666666E-14;
            }
            else if(array[big]==6){
                factor = factor*0.0166666667;
            }
            else if(array[big]==5){
                factor = factor* 0.0416666667 ;
            }
            else if(array[big]==4){
                factor = factor*0.1428571429;
            }
            else if(array[big]==3){
                factor = factor*0.2299794661;
            }
            else if(array[big]==2){
                factor = factor*0.0833333333;
            }
            else if(array[big]==1){
                factor = factor* 31557600;
            }
            big ++;
        }
        if(reverse ){
            factor = 1/factor;
            return factor;
        }
        else {
            return factor;
        }

    }
    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("yes");
        if(fragment != null){
            removeFragment();
        }else {
            super.onBackPressed();
        }

    }

}