package com.example.charul.entername;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int count=0,noOfCoffees,price;String cream1,cream2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

   public void increament(View view){
   count++;
       if (count>100){
           Toast.makeText(this,"More than 100 coffees cannot be ordered",Toast.LENGTH_SHORT).show();
           return;
       }
          noOfCoffees = display(count);
       link();

       }

    public void decreament(View view){
        count--;
        if (count<1){
            Toast.makeText(this,"Minimum 1 coffee has to be ordered",Toast.LENGTH_SHORT).show();
            count++;
            return;}

            noOfCoffees=display(count);
        link();

    }
    public int display(int n){
        TextView textView = (TextView) findViewById(R.id.quantity);
        textView.setText(String.valueOf(n));
        return(n);

    }
    public void haswhippedcream(){
        CheckBox checkBox1 =(CheckBox) findViewById(R.id.whippedcream);
        if(checkBox1.isChecked()==true){
            cream1="yes";
        }
        else{
            cream1="no";
        }
    }
    public void haschocolate(){
        CheckBox checkBox2 =(CheckBox) findViewById(R.id.chocolate);
        if(checkBox2.isChecked()==true) {
            cream2 = "yes";
        }
        else{
            cream2="no";

        }
    }
    public void link(){
        haswhippedcream();
        haschocolate();
        displayprice(cream1,cream2);
    }
    public void displayprice(String x, String y){
        if((x=="yes")&&(y=="yes"))
            price=noOfCoffees*40;
        else if(((x=="yes")&&(y=="no"))||((x=="no")&&(y=="yes")))
            price=noOfCoffees*30;
        else
        price=noOfCoffees*20;
        TextView textview=(TextView) findViewById(R.id.price);
        textview.setText(String.valueOf(price));
    }
   public void call(View view){
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:8218766281"));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }}

    public void order(View view){
        link();
        EditText editText = (EditText) findViewById(R.id.name);
        String str =editText.getText().toString();
    Intent intent1 = new Intent(Intent.ACTION_SENDTO);
        intent1.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent1.putExtra(Intent.EXTRA_SUBJECT,"Coffee order for "+str);
        intent1.putExtra(Intent.EXTRA_TEXT, "Whipped cream: "+cream1+ "\nChocolate: "+cream2+"\nNo of coffees: "+noOfCoffees +"\nTotal amount to be paid: "+price);
        if (intent1.resolveActivity(getPackageManager()) != null) {
            startActivity(intent1);
        }

    }
}
