package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            System.out.println(getIP());
            if (validate(getIP()))
                buttonPress(view);
            else Toast.makeText(this, "Wrong IP address!", Toast.LENGTH_SHORT).show();
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

    private boolean validate(String ipAddress) {
        Pattern pattern = Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}");
        Matcher matcher = pattern.matcher(ipAddress);

        return matcher.matches();
    }
}