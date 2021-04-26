package edu.birzeit.assignment2.runners;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import edu.birzeit.assignment2.R;
import edu.birzeit.assignment2.domain.BasicInformation;
import edu.birzeit.assignment2.domain.Experiences;

public class SecondActivity extends AppCompatActivity {

    private static final String FLAG2 = "FLAG2";
    private static final String EXP = "EXP";
    private Boolean flag = false;

    private EditText edtEdu;
    private EditText edtWork;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    @Override
    protected void onPause(){
        super.onPause();
        Gson gson = new Gson();
        Experiences experiences = new Experiences(edtEdu.getText().toString(), edtWork.getText().toString());
        String gsonString = gson.toJson(experiences);
        editor.putString(EXP,gsonString);
        flag = true;
        editor.putBoolean(FLAG2,flag);
        editor.commit();

    }

    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG2, false);

        if (flag) {
            String exp = prefs.getString(EXP, null);
            Gson gson = new Gson();
            if (exp != null) {
                Experiences experiences = gson.fromJson(exp, Experiences.class);
                edtEdu.setText(experiences.getEducation());
                edtWork.setText(experiences.getWork());
            }
        }
    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        edtEdu = findViewById(R.id.edtEdu);
        edtWork = findViewById(R.id.edtWork);
    }

    public void btnPrev_onClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}