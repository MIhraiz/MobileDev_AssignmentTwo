package edu.birzeit.assignment2.runners;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import edu.birzeit.assignment2.R;
import edu.birzeit.assignment2.domain.BasicInformation;

public class MainActivity extends AppCompatActivity {

    private static final String FLAG = "FLAG";
    private static final String BASEINFO = "BASEINFO";
    private static final String PATH = "PATH";
    private static final int SELECT_PICTURE = 1;
    private boolean flag = false;
    private boolean imageFlag = false;
    private Uri selectedImageURI;
    private ImageView imgPersonal;
    private EditText edtName;
    private EditText edtNickname;
    private Spinner spnGender;
    private EditText edtEmail;
    private EditText edtHobby;

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupSharedPrefs();
        checkPrefs();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if(!imageFlag){
            Gson gson = new Gson();
            BasicInformation bi = new BasicInformation(edtName.getText().toString(), edtNickname.getText().toString(), (char) spnGender.getSelectedItem(), edtEmail.getText().toString(), edtHobby.getText().toString());
            String gsonString = gson.toJson(bi);
            editor.putString(BASEINFO, gsonString);
            editor.putString(PATH, selectedImageURI.toString());
            flag = true;
            editor.putBoolean(FLAG,flag);
            editor.commit();
        }
    }



    private void checkPrefs() {
        flag = prefs.getBoolean(FLAG, false);

        if (flag) {
            String basicInformation = prefs.getString(BASEINFO,null);
            String uri = prefs.getString(PATH,null);
            Gson gson = new Gson();

            if (basicInformation != null) {
                BasicInformation base = gson.fromJson(basicInformation, BasicInformation.class);
                edtName.setText(base.getName());
                edtNickname.setText(base.getNickname());
                if(base.getGender() == 'F'){
                    spnGender.setSelection(0);
                }else {
                    spnGender.setSelection(1);
                }
                edtHobby.setText(base.getHobby());
                edtEmail.setText(base.getEmail());



            } else {
                Toast.makeText(this,"Data Not Found", Toast.LENGTH_SHORT).show();
            }
            if (uri != null) {
                selectedImageURI = Uri.parse(uri);
                Picasso.get().load(selectedImageURI).noPlaceholder().centerCrop().fit()
                        .into(imgPersonal);
            }

        }

    }

    private void setupSharedPrefs() {
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }

    private void setupViews() {
        imgPersonal = findViewById(R.id.imgPersonal);
        edtName = findViewById(R.id.edtName);
        edtHobby = findViewById(R.id.edtHobby);
        edtNickname = findViewById(R.id.edtNickname);
        edtEmail = findViewById(R.id.edtEmail);
        spnGender = findViewById(R.id.spnGender);
        ArrayList<Character> gen = new ArrayList<Character>();
        gen.add('F');
        gen.add('M');
        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(this,android.R.layout.simple_spinner_item,gen);
        spnGender.setAdapter(adapter);
    }

    public void btnImage_onCLick(View view) {
        imageFlag = true;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_OPEN_DOCUMENT);
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SELECT_PICTURE){
            imageFlag = false;
        }
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageURI = data.getData();
                getContentResolver().takePersistableUriPermission(selectedImageURI, Intent.FLAG_GRANT_READ_URI_PERMISSION);

                Picasso.get().load(selectedImageURI).noPlaceholder().centerCrop().fit()
                        .into((ImageView) findViewById(R.id.imgPersonal));
            }

        }
    }


    public void btnNext_onClick(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

}