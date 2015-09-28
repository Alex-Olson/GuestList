package com.example.angel.guestlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class GuestListActivity extends AppCompatActivity {

    EditText mEnterGuestET;
    Button mSaveGuestButton;
    TextView mGuestListTV;

    ArrayList<String> guests;

    private final static String GUEST_LIST_KEY = "guest list bundle key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest_list);

        guests = new ArrayList<>();

        mEnterGuestET = (EditText) findViewById(R.id.guest_name_edit_text);
        mGuestListTV = (TextView) findViewById(R.id.guest_list_text_view);

        if (savedInstanceState != null ){
            guests = savedInstanceState.getStringArrayList(GUEST_LIST_KEY);
        }

        if (guests == null){
            guests = new ArrayList<>();
        }

        updateGuestListTV();

        mSaveGuestButton = (Button) findViewById(R.id.save_button);

        mSaveGuestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newGuestName = mEnterGuestET.getText().toString();
                if (!newGuestName.equals("")){
                    guests.add(newGuestName);
                    mEnterGuestET.getText().clear();
                }
                GuestListActivity.this.updateGuestListTV();
            }
        });

    }

    private void updateGuestListTV(){
        String displayString = "";
        for (String guest : guests){
            displayString = displayString.concat(guest);
            displayString = displayString.concat("\n");
        }
        mGuestListTV.setText(displayString);
    }

    @Override
    protected void onSaveInstanceState (Bundle outBundle){
        outBundle.putStringArrayList(GUEST_LIST_KEY, guests);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_guest_list, menu);
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
}
