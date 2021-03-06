package a404_notfound.sourceappwater.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.Admin;
import a404_notfound.sourceappwater.model.Manager;
import a404_notfound.sourceappwater.model.RegisteredUser;
import a404_notfound.sourceappwater.model.Worker;

/**
 * Controller for creating a new user account
 */
public class RegistrationActivity extends AppCompatActivity
        implements AdapterView.OnItemSelectedListener {

    private DatabaseReference mRef;
    private  final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String TAG = "Info";
    private static final List<String> userCert = Arrays.asList("User", "Worker", "Manager",
            "Administrator");

    // Firebase Objects
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private String userClassification;
    private EditText mName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mName = (EditText) findViewById(R.id.name_of_user);
        mName.setOnEditorActionListener(new TextView.OnEditorActionListener()  {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                return ((id == R.id.login) || (id == EditorInfo.IME_NULL));
            }
        });

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,
                userCert);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Set up fire base Authentication and Listener for that authentication
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        mRef = database.getReference();
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        Button mUpdateButton = (Button) findViewById(R.id.update_button);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatabaseReference mRefChild = mRef.child(mAuth.getCurrentUser().getUid());
                createProfile((String) spinner.getSelectedItem());
                //mRefChild.setValue(tpe);
                Intent switchScreen = new Intent(getApplicationContext(),NavigationMain.class);
                startActivity(switchScreen);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    //Stop the firebase Listener
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    /**
     *Method responsible for creating the user instance
     * @param ucas the Type of user, Worker, User, Manager, etc.
     */
    private void createProfile(String ucas) {
        String name = mName.getText().toString();
        String a = "/users/";
        Map<String, Object> values = new HashMap<>();

        if (("Administrator").equals(ucas)) {
            Admin e = new Admin(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        } else if(("Worker").equals(ucas)) {
            Worker e = new Worker(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        } else if (("Manager").equals(ucas)) {
            Manager e = new Manager(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        } else {
            RegisteredUser e = new RegisteredUser(name);
            String nme = e.getUsername();
            String addrs = e.getAddress();
            String coor = e.getCoordinates();
            String uid = mAuth.getCurrentUser().getUid();
            values.put(a + uid + "/name/", nme);
            values.put(a+ uid + "/addrs/", addrs);
            values.put(a + uid + "/coor/", coor);
            values.put(a + uid + "/accttype/", e.toString());
            mRef.updateChildren(values);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        userClassification = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        userClassification = "User";
    }


    @Override
    public void onBackPressed() {
        //TODO Display Dialouge alert
        return;
    }
}


