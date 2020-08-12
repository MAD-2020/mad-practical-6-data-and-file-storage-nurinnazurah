package sg.edu.np.week_6_whackamole_3_0;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private static final String FILENAME = "Main2Activity.java";
    private static final String TAG = "Whack-A-Mole3.0!";

    MyDBHandler dbHandler = new MyDBHandler(this,null,null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button create = findViewById(R.id.buttonCreate);
        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                EditText etCreateUsername = findViewById(R.id.editText_CreateUsername);
                EditText etCreatePassword = findViewById(R.id.editText_CreatePassword);

                UserData userData = dbHandler.findUser(etCreateUsername.getText().toString());
                if(userData == null)
                {
                    UserData dbUserData = new UserData();
                    dbUserData.setMyUserName(etCreateUsername.getText().toString());
                    dbUserData.setMyPassword(etCreatePassword.getText().toString());
                    dbHandler.addUser(dbUserData);
                    Toast.makeText(Main2Activity.this, "New User Created!", Toast.LENGTH_SHORT).show();
                    Log.v(TAG, FILENAME + "Created: " + etCreateUsername.getText().toString() );
                    Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Log.v(TAG, FILENAME + ": User already exist during new user creation!");
                    Toast.makeText(Main2Activity.this, "User already exist. \nPlease try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button cancel = findViewById(R.id.buttonCancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    protected void onStop() {
        super.onStop();
        finish();
    }
}
