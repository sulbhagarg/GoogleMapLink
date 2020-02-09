package com.example.myapplication.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class PlaceApi {

    public ArrayList<String> autoComplete(String input){
        ArrayList<String> arrayList=new ArrayList();
        HttpURLConnection connection = null;
        StringBuilder jsonresult = new StringBuilder();
        try{
            StringBuilder sb =new StringBuilder("https://maps.googleapis.com/maps/api/place/autocomplete/json?");
            sb.append("input"+input);
            sb.append("&key=AIzaSyDY-f2jgi9P7W2gpC--DudSIhVu4Ni3w9g");
            URL url = new URL(sb.toString());


            connection=(HttpURLConnection)url.openConnection();
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            int read;
            char[] buff=new char[1024];
            while((read=inputStreamReader.read(buff))!=-1)
            {
                jsonresult.append(buff,0,read);
            }
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(connection!=null)
            {
                connection.disconnect();
            }
        }

        try{
            JSONObject jsonObject = new JSONObject(jsonresult.toString());
            System.out.println(jsonObject+"  OBJ");
            JSONArray prediction = jsonObject.getJSONArray("predictions");
            System.out.println(prediction.length()+" len");
            for(int i=0;i<prediction.length();i++)
            {
                arrayList.add(prediction.getJSONObject(i).getString("description"));
            }
        }
        catch(JSONException e){
            e.printStackTrace();
        }

            System.out.println(input);
            System.out.println(arrayList);
        return arrayList;
    }
}
