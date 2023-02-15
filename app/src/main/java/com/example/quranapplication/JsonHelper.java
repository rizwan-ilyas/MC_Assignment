package com.example.quranapplication;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

public class JsonHelper {
    String FileName;
    JSONArray arr;
    Context context;

    public JsonHelper(String fileName, Context context) {
        FileName = fileName;
        this.context = context;
        try {
            arr = new JSONArray(getJsonFromAssets());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("except", "getJsonFromAssets: " + e.getMessage());
        }
    }

    public int getSurahStart(int surahNumber)
    {
        for (int i = 0; i < arr.length(); i++)
        {
            try {
                if (arr.getJSONObject(i).getInt("surah_number") == surahNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("except", "getJsonFromAssets: " + e.getMessage());

            }
        }
        return -1;
    }

    public int getSurahEnd(int surahNumber)
    {
        for (int i = arr.length() - 1; i >= 0; i--)
        {
            try {
                if (arr.getJSONObject(i).getInt("surah_number") == surahNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("except", "getJsonFromAssets: " + e.getMessage());
            }
        }
        return -1;
    }

    public int getParaStart(int ParaNumber)
    {
        for (int i = 0; i < arr.length(); i++)
        {
            try {
                if (arr.getJSONObject(i).getInt("juz") == ParaNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("except", "getJsonFromAssets: " + e.getMessage());
            }
        }
        return -1;
    }

    public int getParaEnd(int paraNumber)
    {
        for (int i = arr.length() - 1; i >= 0; i--)
        {
            try {
                if (arr.getJSONObject(i).getInt("juz") == paraNumber)
                {
                    return i;
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("except", "getJsonFromAssets: " + e.getMessage());
            }
        }
        return -1;
    }

    public JSONArray getArray() {
        return arr;
    }
    public int getArrayLength() { return arr.length(); }

    String getJsonFromAssets() {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(FileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("except", "getJsonFromAssets: " + e.getMessage());
            return null;
        }
        return jsonString;
    }

}
