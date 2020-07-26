package com.jayshreegopalapps.motivationalquotes;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DownloadImageTask extends AsyncTask<String, Void, String> {
    private updateArraylist updateArraylist;
    int reqAmount;
    public DownloadImageTask(int reqAmount, updateArraylist updateArraylist) {
        this.updateArraylist = updateArraylist;
        this.reqAmount = reqAmount;
    }

    @Override
    protected String doInBackground(String... strings) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(strings[0]).build();
        try {
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                try {
                    return response.body().string();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            String rowCountString = jsonObject.getString("rowCount");
            int rowCountInt = Integer.parseInt(rowCountString);
            JSONArray array = jsonObject.getJSONArray("response");
            String reason = jsonObject.getString("reason");
            if(reason.equals("")) {
                updateArraylist.update(rowCountInt, array, reqAmount);
            }
            else{
                updateArraylist.errorFetching(reason);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        QuotesModel model = new QuotesModel();
//        try {
//            JSONObject jsonString = new JSONObject(s);
//            model.quoteText = jsonString.get("text").toString();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        updateArraylist.update(model, i);
    }
    public interface updateArraylist {
        void update(int model, JSONArray position, int reqAmount);
        void errorFetching(String reason);
    }
}
