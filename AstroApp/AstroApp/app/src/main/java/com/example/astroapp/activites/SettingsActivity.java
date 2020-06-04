package com.example.astroapp.activites;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.astroapp.GlobalValues;
import com.example.astroapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.freqs, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        getSupportActionBar().hide();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Settings saved", Snackbar.LENGTH_LONG).show();
                save();
            }
        });

        initFields();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

        String item = parent.getItemAtPosition(position).toString();
        item = item.replaceAll("\\D+","");
        GlobalValues.refreshFreq = Integer.parseInt(item);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        EditText editText = findViewById(R.id.latitude);
        GlobalValues.latitude = Double.parseDouble(editText.getText().toString());

        editText = findViewById(R.id.longitude);
        GlobalValues.longitude = Double.parseDouble(editText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        EditText editText = (EditText)findViewById(R.id.latitude);
        editText.setText(Double.toString(GlobalValues.latitude));

        editText = (EditText)findViewById(R.id.longitude);
        editText.setText(Double.toString(GlobalValues.longitude));

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter();
        int spinnerPosition = myAdap.getPosition(Integer.toString(GlobalValues.refreshFreq) + " min");
        spinner.setSelection(spinnerPosition);
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    void initFields(){
        EditText editText = (EditText)findViewById(R.id.latitude);
        editText.setText(Double.toString(GlobalValues.latitude));

        editText = (EditText)findViewById(R.id.longitude);
        editText.setText(Double.toString(GlobalValues.longitude));

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        ArrayAdapter myAdap = (ArrayAdapter) spinner.getAdapter();
        int spinnerPosition = myAdap.getPosition(Integer.toString(GlobalValues.refreshFreq) + " min");
        spinner.setSelection(spinnerPosition);
    }

    void save(){
        EditText editText = findViewById(R.id.latitude);
        GlobalValues.latitude = Double.parseDouble(editText.getText().toString());

        editText = findViewById(R.id.longitude);
        GlobalValues.longitude = Double.parseDouble(editText.getText().toString());
    }
}
