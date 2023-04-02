package fun5i.app.progrezcloudapi;


import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import fun5i.app.progrezcloudmodule.Model.*;
import fun5i.app.progrezcloudmodule.ProgrezCloudApi;

public class MainActivity extends AppCompatActivity {

    private CheckBox checkBox;
    private ProgrezCloudApi progrezCloudApi;
    private EditText eUserKey, eUsername, ePassword, eProject, eFields;
    private Button execute, login;
    private TextView result;

    private void gui(){
        checkBox = findViewById(R.id.checkbox_login);
        eUserKey = findViewById(R.id.eUserkey);
        eUsername = findViewById(R.id.eusername);
        eFields = findViewById(R.id.efields);
        ePassword = findViewById(R.id.epass);
        eProject = findViewById(R.id.eproject);
        execute = findViewById(R.id.execute);
        login = findViewById(R.id.login);
        result = findViewById(R.id.result);

        eUserKey.setText("YN7MLHFVZG5DDR62SCIYPH4BHWHU180PKW0U35ESB9UDQQ48RCW5IP5PBC829YGJ");
        /*eUsername.setText("username");
        ePassword.setText("Create a Progrez Cloud Account");*/
        eProject.setText("r7tu88x2kkcsqveqk3ssn1jlqq15p3d6");

        eFields.setText("task_name");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gui();

        progrezCloudApi = new ProgrezCloudApi();

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    eUserKey.setVisibility(View.VISIBLE);
                    eUsername.setVisibility(View.GONE);
                    ePassword.setVisibility(View.GONE);
                    /**/
                }else{
                    eUserKey.setVisibility(View.GONE);
                    eUsername.setVisibility(View.VISIBLE);
                    ePassword.setVisibility(View.VISIBLE);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    progrezCloudApi.login((int errno, String errmsg, PCLoginModel account) -> {
                        if (errno > 0){
                            result.setText("Error: "+errmsg);
                        }else{
                            result.setText(formatString(new Gson().toJson(account)));
                        }
                    },eUserKey.getText().toString());
                }else{
                    progrezCloudApi.login((int errno, String errmsg, PCLoginModel account) -> {
                        if (errno > 0){
                            result.setText("Error: "+errmsg);
                        }else{
                            result.setText(formatString(new Gson().toJson(account)));
                        }
                    },eUsername.getText().toString(), ePassword.getText().toString());
                }
            }
        });

        execute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkBox.isChecked()){
                    progrezCloudApi.login((int errno, String errmsg, PCLoginModel account) -> {
                        if (errno > 0){
                            result.setText("Error: "+errmsg);
                        }else{
                            progrezCloudApi.setProject(account, (int errno2, String errmsg2, String body) -> {
                                if (errno2 > 0){
                                    result.setText("Error: "+errmsg2);
                                }else{
                                    result.setText(formatString(body));
                                }
                            }, eProject.getText().toString(), eFields.getText().toString());
                        }
                    },eUserKey.getText().toString());
                }else{
                    progrezCloudApi.login((int errno, String errmsg, PCLoginModel account) -> {
                        if (errno > 0){
                            result.setText("Error: "+errmsg);
                        }else{

                            progrezCloudApi.setProject(account, (int errno2, String errmsg2, String body) -> {
                                if (errno2 > 0){
                                    result.setText("Error: "+errmsg2);
                                }else{
                                    result.setText(formatString(body));
                                }
                            }, eProject.getText().toString(), eFields.getText().toString());
                        }
                    },eUsername.getText().toString(), ePassword.getText().toString());
                }
            }
        });
    }

    public static String formatString(String text){

        StringBuilder json = new StringBuilder();
        String indentString = "";

        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            switch (letter) {
                case '{':
                case '[':
                    json.append("\n" + indentString + letter + "\n");
                    indentString = indentString + "\t";
                    json.append(indentString);
                    break;
                case '}':
                case ']':
                    indentString = indentString.replaceFirst("\t", "");
                    json.append("\n" + indentString + letter);
                    break;
                case ',':
                    json.append(letter + "\n" + indentString);
                    break;

                default:
                    json.append(letter);
                    break;
            }
        }

        return json.toString();
    }




}