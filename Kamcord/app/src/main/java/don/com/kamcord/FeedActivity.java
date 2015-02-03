package don.com.kamcord;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import don.com.adapter.videolistadapter;
import don.com.pojo.videoClass;

/*
    1. JSON Element: https://www.jsoneditoronline.org/

 */


public class FeedActivity extends ActionBarActivity {

    private String kamUrl = "https://www.kamcord.com/app/v2/videos/feed/?feed_id=0";
//    private OkHttpClient okClient = new OkHttpClient();
    private String logString;

    videolistadapter vAdapter;
    ArrayList<videoClass> list = new ArrayList<videoClass>();

    @InjectView(R.id.video_list)
    ListView videoListView;
    @InjectView(R.id.pb)
    ProgressBar mProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        ButterKnife.inject(this);

        if(!isNetworkOnline()) {
            Log.d("Network is not ", "available.");
        } else {
            Log.d("Network is ", "available.");
        }

        new jsonAsyn().execute("");

        videoListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("position clicked:", Integer.toString(position));
                Intent intent = new Intent(getApplicationContext(), VideoWebviewActivity.class);
                intent.putExtra("videoURL", list.get(position).getVideo_url());
                startActivity(intent);
                overridePendingTransition(R.anim.bottom_top, android.R.anim.fade_out);
            }
        });

    }

    public class jsonAsyn extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            try {

//                logString = new OkHttpHelper().run(kamUrl);
                HttpGet httpget = new HttpGet(kamUrl);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httpget);

                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jsonObject = new JSONObject(data);
                    JSONObject jsonObjectResponse = jsonObject.getJSONObject("response");
                    JSONArray  jsonArray = jsonObjectResponse.getJSONArray("video_list");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        JSONObject thumbObj = object.getJSONObject("thumbnails");

                        videoClass videoItem = new videoClass();
                        videoItem.setUserName("by " + object.getString("username"));
                        videoItem.setTitle(object.getString("title"));
                        videoItem.setThumbnails(thumbObj.getString("REGULAR"));
                        videoItem.setVideo_url(object.getString("video_site_watch_page"));

                        list.add(videoItem);
                    }

                }

                return true;

            } catch (IOException e) {
                Log.d("Kamcord JSON"," Failed ");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return false;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result){
            mProgressbar.setVisibility(View.GONE);

            vAdapter = new videolistadapter(getApplicationContext(), R.layout.single_item, list);
            videoListView.setAdapter(vAdapter);
        }
    }

//    public class OkHttpHelper {
//        String run(String url) throws IOException {
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//            Response response = okClient.newCall(request).execute();
//            return response.body().string();
//        }
//    }

    public boolean isNetworkOnline() {
        boolean status = false;
        try{
            ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getNetworkInfo(0);
            if (netInfo != null && netInfo.getState()==NetworkInfo.State.CONNECTED) {
                status= true;
            }else {
                netInfo = cm.getNetworkInfo(1);
                if(netInfo!=null && netInfo.getState()==NetworkInfo.State.CONNECTED)
                    status= true;
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return status;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
