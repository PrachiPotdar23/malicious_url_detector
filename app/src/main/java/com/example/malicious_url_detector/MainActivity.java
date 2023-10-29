package com.example.malicious_url_detector;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private EditText urlEditText;
    private Button checkButton;
    private TextView resultTextView;
    private final String apiKey = "d8YVg6FA1XrgIeytXJU4GoHwahJUl9gr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        urlEditText = findViewById(R.id.urlEditText);
        checkButton = findViewById(R.id.checkButton);
        resultTextView = findViewById(R.id.resultTextView);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = urlEditText.getText().toString();

                // Check the user-entered URL
                checkURL(url);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void checkURL(final String url) {

        if (url == null || url.trim().isEmpty()) {
            resultTextView.setText("Error: URL is empty or incomplete.");
            return;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://www.ipqualityscore.com/api/json/url/" + apiKey + "/" + url)
                        .build();

                try {
                    Response response = client.newCall(request).execute();
                    if (response.isSuccessful()) {
                        // Parse the response and determine if the URL is safe or malicious.
                        String responseText = response.body().string();

                        // Handle the response on the UI thread
                        handleResult(responseText);
                    } else {
                        // Handle the case where the API request was not successful
                        handleResult("Error: Unable to check the URL.");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle exceptions here
                    handleResult("Error: An exception occurred while checking the URL.");
                }
            }
        }).start();
    }

    private void handleResult(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Gson gson = new Gson();
                    UrlCheckResponse response = gson.fromJson(result, UrlCheckResponse.class);

                    // Now you can access the fields of the response object and evaluate whether the URL is malicious or not.
                    boolean isMalicious = response.isUnsafe();
                    String domain = response.getDomain();

                    // Update the resultTextView with the evaluation result
                    if (isMalicious) {
                        resultTextView.setText("The URL " + domain + " is malicious.");
                    } else {
                        resultTextView.setText("The URL " + domain + " is safe.");
                    }
                } catch (JsonSyntaxException e) {
                    // Handle JSON parsing errors
                    resultTextView.setText("Error: JSON parsing error.");
                }
            }
        });
    }
}
