package bobo.getpostdemo;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("ALL")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")

public class MainActivity extends Activity {
    double[][] lat = { {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0 ,0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0 ,0}};
    double[] lon = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    //double lat= 0;
    //double lon = 0;
    private TextView txtResult = null;
    private TextView txtStatus = null;
    private TextView [] history = new TextView[10];
    private TextView [] repaired = new TextView[10];

    private SwipeRefreshLayout laySwipe;

    Button go_page_2;
    //ListView history_list;
    //String[] historys = {"1.", "2.", "3.", "4.", "5.", "6.", "7.", "8.", "9.", "10.", "11.", "12.", "13.", "14.", "15."};
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        go_page_2 = (Button)findViewById(R.id.to_page_2);
        go_page_2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                //new一個intent物件，並指定Activity切換的class
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MapActivity.class);
                //new一個Bundle物件，並將要傳遞的資料傳入
                Bundle bundle = new Bundle();
                bundle.putDouble("lat", lat[0][0]);
                bundle.putDouble("lon", lon[0]);
                //將Bundle object傳給intent
                intent.putExtras(bundle);
                //切換Activity
                startActivity(intent);
                MainActivity.this.finish();
            }
        });

        /*history_list = (ListView) findViewById(R.id.history_list);

        //建立ArrayAdapter  設定ListView的資料來源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, historys);
        history_list.setAdapter(adapter);*/

        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        txtResult = (TextView) findViewById(R.id.txtResult);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        history[0] = (TextView) findViewById(R.id.history1);
        history[1] = (TextView) findViewById(R.id.history2);
        history[2] = (TextView) findViewById(R.id.history3);
        history[3] = (TextView) findViewById(R.id.history4);
        history[4] = (TextView) findViewById(R.id.history5);
        history[5] = (TextView) findViewById(R.id.history6);
        history[6] = (TextView) findViewById(R.id.history7);
        history[7] = (TextView) findViewById(R.id.history8);
        history[8] = (TextView) findViewById(R.id.history9);
        history[9] = (TextView) findViewById(R.id.history10);
        repaired[0] = (TextView) findViewById(R.id.repaired1);
        repaired[1] = (TextView) findViewById(R.id.repaired2);
        repaired[2] = (TextView) findViewById(R.id.repaired3);
        repaired[3] = (TextView) findViewById(R.id.repaired4);
        repaired[4] = (TextView) findViewById(R.id.repaired5);
        repaired[5] = (TextView) findViewById(R.id.repaired6);
        repaired[6] = (TextView) findViewById(R.id.repaired7);
        repaired[7] = (TextView) findViewById(R.id.repaired8);
        repaired[8] = (TextView) findViewById(R.id.repaired9);
        repaired[9] = (TextView) findViewById(R.id.repaired10);

        // http get method 1
        makeGetRequest();

        // http get method 2
        initView();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        System.out.println(".........................");
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://api.mediatek.com/mcs/v2/devices/DBTbxRgn/datachannels/2/datapoints?limit=10"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }

    private void makeGetRequest() {
        //初始化經緯度array
        for(int i =0; i<10 ; i++){
            for(int j = 0; j<2; j++) {
                lat[i][j] = 0;
            }
            lon[i] = 0;
        }

        String resultString = null;

        String httpLink = "https://api.mediatek.com/mcs/v2/devices/DBTbxRgn/datachannels/2/datapoints?limit=10";

        System.out.println("httpLink :" + httpLink);

        // Create a new HttpClient
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(httpLink);
        HttpResponse response;

        try {
            // Execute HTTP Post Request
            response = httpClient.execute(httpGet);

            int responseCode = response.getStatusLine().getStatusCode();

            System.out.println("responseCode :" + responseCode);

            if (responseCode == 200) {
                // get response string, JSON format data
                resultString = EntityUtils.toString(response.getEntity());
            } else {
                resultString = null;
                System.out.println("response not ok, status :" + responseCode);
            }

        } catch (Exception e) {
            System.out.println("HttpPost Exception : " + e.toString());

        } finally {
            httpClient.getConnectionManager().shutdown();
        }

        System.out.println("resultString :" + resultString);

        /*if(resultString.length() > 150) {
            resultString = "Http get method 1 : Google Data -> " + resultString.substring(0, 149);
        }*/

        try {
            JSONObject object = (JSONObject) new JSONTokener(resultString).nextValue();
            //String apiV = object.getString("apiVersion");
            JSONArray array = object.getJSONArray("dataChannels");
            JSONObject obj = array.getJSONObject(0);
            //String chnID = obj.getString("dataChnId");
            JSONArray dataPoints = obj.getJSONArray("dataPoints");
            //JSONObject obj1 = dataPoints.getJSONObject(1);
            //JSONObject values = obj1.getJSONObject("values");
            //lat[0]= values.getDouble("latitude");
            //lon[0]= values.getDouble("longitude");
            int count = 0;
            int r_count = 0;
            String [] formattedDate = new String[10];
            for(int i = 0; i<10; i++)
            {
                JSONObject obj1 = dataPoints.getJSONObject(i);
                JSONObject values = obj1.getJSONObject("values");
                //double date = obj1.getDouble("recordedAt");
                lat[i][0] = values.getDouble("latitude");
                lat[i][1] = obj1.getDouble("recordedAt");
                lon[i] = values.getDouble("longitude");
                System.out.println(lat[i][0]+"　　"+lon[i]);

                String s = String.valueOf(lat[i][1]);
                // remove all . (dots) from the String
                String str = s.replace(".", "");
                str = str.substring(0, 10);
                //System.out.println("data : "+ str);
                //Convert the string back to long
                long date_unix = Long.parseLong(str);
                System.out.println("data : "+ date_unix);

                Date date = new Date(date_unix*1000L); // *1000 is to convert seconds to milliseconds
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
                sdf.setTimeZone(TimeZone.getDefault()); // give a timezone reference for formating (see comment at the bottom
                formattedDate[i] = sdf.format(date);
                System.out.println(formattedDate[i]);

                if(lat[i][0] != 0)
                {
                    history[count].setText(lat[i][0] + "," + lon[i] + "\n"+ formattedDate[i]);
                    count++;
                }
                else
                {
                    repaired[r_count].setText("已修好" + "\n" + formattedDate[i]);
                    r_count++;
                }

            }

        } catch (JSONException e){
            e.printStackTrace();
        }

        if(lat[0][0] != 0) {
            txtResult.setText("經度 : " + lat[0][0] + "　　" + "緯度 : "+ lon[0]);
            txtStatus.setText("目前狀態：水管異常");
            txtStatus.setTextColor(Color.RED);
            go_page_2.setVisibility(View.VISIBLE);
        }
        else {
            txtResult.setText("");
            txtStatus.setText("目前狀態：水管正常");
            txtStatus.setTextColor(Color.BLUE);
            go_page_2.setVisibility(View.GONE);
        }

    }

    /*private void loadPage() {

        Thread t1 = new Thread(r1);
        t1.start();

    }
*/
    private void initView() {

        laySwipe = (SwipeRefreshLayout) findViewById(R.id.laySwipe);
        laySwipe.setOnRefreshListener(onSwipeToRefresh);
        laySwipe.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_blue_light,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
        ListView lstData = (ListView) findViewById(R.id.lstData);
        //lstData.setAdapter(getAdapter());
        lstData.setOnScrollListener(onListScroll);

        /*Button btnLoad = (Button) findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                loadPage();
            }

        });*/
    }

    private OnRefreshListener onSwipeToRefresh = new OnRefreshListener() {
        @Override
        public void onRefresh() {
            laySwipe.setRefreshing(true);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    laySwipe.setRefreshing(false);
                    Toast.makeText(getApplicationContext(), "Refresh done!", Toast.LENGTH_SHORT).show();
                }
            }, 3000);
        }
    };
    /*private ArrayAdapter<String> getAdapter(){
        //fake data
        String[] data = new String[20];
        int len = data.length;
        for (int i = 0; i < len; i++) {
            data[i] = "";
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , data);
        return adapter;
    }*/
    private OnScrollListener onListScroll = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
        }
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (firstVisibleItem == 0) {
                laySwipe.setEnabled(true);
            }else{
                laySwipe.setEnabled(false);
            }
        }
    };

}