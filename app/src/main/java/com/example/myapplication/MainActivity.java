package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        EditText ip1 = findViewById(R.id.ip1);
        EditText ip2 = findViewById(R.id.ip2);
        EditText ip3 = findViewById(R.id.ip3);
        EditText ip4 = findViewById(R.id.ip4);

        button.setEnabled(false);

        ip1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setEnabled(validate(ip1, button)
                        && validate(ip2, button)
                        && validate(ip3, button)
                        && validate(ip4, button));
            }
        });

        ip2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setEnabled(validate(ip1, button)
                        && validate(ip2, button)
                        && validate(ip3, button)
                        && validate(ip4, button));
            }
        });

        ip3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setEnabled(validate(ip1, button)
                        && validate(ip2, button)
                        && validate(ip3, button)
                        && validate(ip4, button));
            }
        });

        ip4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                button.setEnabled(validate(ip1, button)
                        && validate(ip2, button)
                        && validate(ip3, button)
                        && validate(ip4, button));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonPress(view);
            }
        });
    }

    public void buttonPress(View view) {
        getIPInfo();
    }

    private String getIP() {
        EditText e1 = (EditText) findViewById(R.id.ip1);
        EditText e2 = (EditText) findViewById(R.id.ip2);
        EditText e3 = (EditText) findViewById(R.id.ip3);
        EditText e4 = (EditText) findViewById(R.id.ip4);

        return e1.getText().toString() + "." + e2.getText().toString() + "." +
                e3.getText().toString() + "." + e4.getText().toString();
    }

    private void printInfo(IPInfo info) {
        TextView textView = (TextView) findViewById(R.id.textView4);

        String s;

        if (info == null) s = "Faild";
        else {
            s = "ip: " + info.getIp() + "\n" +
                    "hostname: " + info.getHostname() + "\n" +
                    "city: " + info.getCity() + "\n" +
                    "region: " + info.getRegion() + "\n" +
                    "country: " + info.getCountry() + "\n" +
                    "loc: " + info.getLoc() + "\n" +
                    "org: " + info.getOrg() + "\n" +
                    "postal: " + info.getPostal() + "\n" +
                    "timezone: " + info.getTimezone() + "\n" +
                    "readme: " + info.getReadme();
        }
        textView.setText(s);
    }

    private void getIPInfo() {
        ApiInterface apiInterface = ServiceGenerator.createService(ApiInterface.class, getIP());

        Call<IPInfo> call = apiInterface.getIPInfo();

        call.enqueue(new Callback<IPInfo>() {
            @Override
            public void onResponse(Call<IPInfo> call, Response<IPInfo> response) {
                printInfo(response.body());
            }

            @Override
            public void onFailure(Call<IPInfo> call, Throwable t) {
                printInfo(null);
            }
        });
    }

    public boolean validate(EditText editText, Button button) {
        if (editText.getText().toString().length() == 0
                || Integer.parseInt(editText.getText().toString()) < 0
                || Integer.parseInt(editText.getText().toString()) > 255) {
            editText.setError("Bad value!");
            return false;
        }

        return true;
    }
}