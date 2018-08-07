package com.proteam.bai_13_2_json_gson;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.proteam.bai_13_2_json_gson.model.PowerClinic;
import com.proteam.bai_13_2_json_gson.model.PowerSearch;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Copyright
 * Created by CuongNV31.
 */
public class MainActivity extends AppCompatActivity {
    private TextView mTvText;
    private AppCompatButton mBtnParserJson;
    private AppCompatButton mBtnParserGson;
    private AppCompatButton mBtnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvText = (TextView) findViewById(R.id.tvTestContent);
        mBtnParserJson = (AppCompatButton) findViewById(R.id.btnParserJson);
        mBtnParserGson = (AppCompatButton) findViewById(R.id.btnParserGson);
        mBtnClear = (AppCompatButton) findViewById(R.id.btnClearText);

        mBtnParserJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mTvText.setText(parseJsonByJsonObject());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        mBtnParserGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvText.setText(parseJsonByGson());
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTvText.setText("");
            }
        });

    }

    ///////////////////////////json
    private String parseJsonByJsonObject() throws JSONException {
        String s = "";
        JSONObject jsonRootObject = new JSONObject(loadJSONFromAsset(this));
        //----------------------------------------------commitClinics
        JSONArray jsonArray = jsonRootObject.optJSONArray("commitClinics");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            s += jsonObject.getString("title") + "//" + jsonObject.getString("content") + "\n";
        }
        ///---------------------------------------------powerClinics
        JSONArray jsonArray1 = jsonRootObject.optJSONArray("powerClinics");
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject jsonObject = jsonArray1.getJSONObject(i);
            s += jsonObject.getString("title") + "//" + jsonObject.getString("content") + "\n";
        }
        return s;
    }

    ///////////////////////////gson
    private String parseJsonByGson() {
        Gson gson = new Gson();
        PowerSearch powerSearch = gson.fromJson(loadJSONFromAsset(this), PowerSearch.class);
        String s = "";
        for (PowerClinic powerClinic : powerSearch.getCommitClinics()) {
            s += powerClinic.getTitle() + "//" + powerClinic.getContent() + "\n";
        }
        for (PowerClinic powerClinic : powerSearch.getPowerClinics()) {
            s += powerClinic.getTitle() + "//" + powerClinic.getContent() + "\n";
        }
        return s;
    }

    private String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("power_search_v1.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            return null;
        }
        Log.d("TABBB", json);
        return json;

    }
}
