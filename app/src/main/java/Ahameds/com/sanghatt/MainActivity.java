package Ahameds.com.sanghatt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.hardware.Sensor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.hardware.SensorManager; //package for android hardware
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton india;
    private ImageButton roomOne;
    Dialog popup,popupRoom;//ONE POPUP FOR JAR AND ONE FOR ROOM

    DatabaseHelper myDB;
    private SensorManager sensorManager;
    private Sensor gyroscope; //gyroscope object


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);// getting all available sensors
        gyroscope=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE); // getting only gyroscope
        if(gyroscope==null)
        {
            Toast.makeText(this,"this device has no gyroscope sensor",Toast.LENGTH_SHORT).show();
            finish();
        }

        popup = new Dialog(this);//POPUP OBJECT JAR
        myDB = new DatabaseHelper(this);//DB OBJECT
        popupRoom = new Dialog(this);//POPUP OBJECT ROOM

        india = (ImageButton)findViewById(R.id.indiajar);
        roomOne = (ImageButton)findViewById(R.id.room1);

        india.setOnClickListener(new View.OnClickListener() { //INDIA JAR BUTTON
            @Override
            public void onClick(View v) {
                Showpopup(v);//CALLING POP UP
            }
        });

        roomOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowRoomPopup(v);// CALLING ROOM POP UP
            }
        });



    }


       public void Showpopup(View v){//DECLARING POP UP FUNCTION
           TextView exit;
           final TextView Show;
           final EditText FName,Email,Phone; //POP UP COMPONENTS
           Button AddUsers;

        popup.setContentView(R.layout.custompopup);//SET IN THE LAYOUT WINDOW
        exit = (TextView) popup.findViewById(R.id.exitbtn);
        FName = (EditText) popup.findViewById(R.id.fullname);
        Email = (EditText) popup.findViewById(R.id.emailid);
        Phone = (EditText) popup.findViewById(R.id.phonenumber);
        Show = (TextView) popup.findViewById(R.id.test);

        AddUsers = (Button) popup.findViewById(R.id.addbtn);

        exit.setOnClickListener(new View.OnClickListener() { //EXIT BUTTON
            @Override
            public void onClick(View v) {
                popup.dismiss();//CALLING DISMISS FOR CLOSING THE POP UP
            }
        });

        popup.show(); //SHOWING POP UP
        AddUsers.setOnClickListener(new View.OnClickListener() {//ADD PEOPLES BUTTON
            @Override
            public void onClick(View v) {//SAVING TO DB
                Boolean flag =myDB.insertData(FName.getText().toString(),Email.getText().toString(),Phone.getText().toString(),"India");
                if(flag==true){
                    Show.setText("Added");
                }
                else{
                    Show.setText(FName.getText());//FOR TESTING
                }
            }
        });

       }

       public void ShowRoomPopup(View v){
           TextView exit;

        popupRoom.setContentView(R.layout.custompopupone);

           exit = (TextView) popupRoom.findViewById(R.id.exitbtn);

           exit.setOnClickListener(new View.OnClickListener() { //EXIT BUTTON
               @Override
               public void onClick(View v) {
                   popupRoom.dismiss();//CALLING DISMISS FOR CLOSING THE POP UP
               }
           });

           popupRoom.show();


       }


}
