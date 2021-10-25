package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Button btnAdd;
    EditText inputLabel;

    public void onClick(View arg0) {
        String label = inputLabel.getText().toString();
        if (label.trim().length() > 0) {
            DatabaseHandler db = new
                    DatabaseHandler(getApplicationContext());
            db.insertLabel(label);
            // making input filed text to blank
            inputLabel.setText("");
            // Hiding the keyboard
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(inputLabel.getWindowToken(), 0);
            // loading spinner with newly added data
            loadSpinnerData();
        } else {
            Toast.makeText(getApplicationContext(), "Please enter label name",
                    Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.spinner);
        btnAdd = findViewById(R.id.btn_add);
        inputLabel = findViewById(R.id.input_label);
        spinner.setOnItemSelectedListener(this);
        // Loading spinner data from database
        loadSpinnerData();
    }

    private void loadSpinnerData() {
        DatabaseHandler db = new
                DatabaseHandler(getApplicationContext());
        List<String> labels = db.getAllLabels();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                labels);
        // Drop down layout style - list view with radio button

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,  Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}