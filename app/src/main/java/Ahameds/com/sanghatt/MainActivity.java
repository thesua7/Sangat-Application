package Ahameds.com.sanghatt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageButton india;
    Dialog popup;
    DatabaseHelper myDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popup = new Dialog(this);//POPUP
        myDB = new DatabaseHelper(this);
        //myDB.getWritableDatabase();
        india = (ImageButton)findViewById(R.id.indiajar);

        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Showpopup(v);
            }
        });




    }


       public void Showpopup(View v){
           TextView exit;
           final TextView Show;
           final EditText FName,Email,Phone;
           Button AddUsers;

        popup.setContentView(R.layout.custompopup);
        exit = (TextView) popup.findViewById(R.id.exitbtn);
        FName = (EditText) popup.findViewById(R.id.fullname);
        Email = (EditText) popup.findViewById(R.id.emailid);
        Phone = (EditText) popup.findViewById(R.id.phonenumber);
        Show = (TextView) popup.findViewById(R.id.test);

        AddUsers = (Button) popup.findViewById(R.id.addbtn);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        popup.show();
        AddUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean flag =myDB.insertData(FName.getText().toString(),Email.getText().toString(),Phone.getText().toString(),"India");
                if(flag==true){
                    Show.setText("Added");
                }
                else{
                    Show.setText(FName.getText());//ok
                }
            }
        });

       }


}
