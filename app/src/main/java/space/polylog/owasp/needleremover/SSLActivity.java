package space.polylog.owasp.needleremover;

import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import needleremover.R;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class SSLActivity extends AppCompatActivity implements DownloadCallback{
    private static final String TAG = "needleREmover.TLS";

    // Keep a reference to the NetworkFragment, which owns the AsyncTask object
    // that is used to execute network ops.
    private NetworkFragment mNetworkFragment;

    // Boolean telling us whether a download is in progress, so we don't trigger overlapping
    // downloads with consecutive button clicks.
    private boolean mDownloading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssl);
    }

    public void onGetClick(View view) {
        EditText urlView = (EditText) findViewById(R.id.url);
        Editable urlViewText = urlView.getText();
        String data = downloadURL(urlViewText.toString());

        TextView downloadContentView = (TextView) findViewById(R.id.contentView);
        downloadContentView.setText(data);
    }

    private String downloadURL(String address) {
        String responseContent;
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(address).build();
            Response response = client.newCall(request).execute();

            responseContent = response.body().string();

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
            responseContent = "An error occured. Please check the logs.";
        }
        return responseContent;
    }

    @Override
    public void updateFromDownload(String result) {
        // Update your UI here based on result of download.
    }

    @Override
    public NetworkInfo getActiveNetworkInfo() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    public void onProgressUpdate(int progressCode, int percentComplete) {
        switch(progressCode) {
            // You can add UI behavior for progress updates here.
            case Progress.ERROR:
            ...
                break;
            case Progress.CONNECT_SUCCESS:
            ...
                break;
            case Progress.GET_INPUT_STREAM_SUCCESS:
            ...
                break;
            case Progress.PROCESS_INPUT_STREAM_IN_PROGRESS:
            ...
                break;
            case Progress.PROCESS_INPUT_STREAM_SUCCESS:
            ...
                break;
        }
    }

    @Override
    public void finishDownloading() {
        mDownloading = false;
        if (mNetworkFragment != null) {
            mNetworkFragment.cancelDownload();
        }
    }

}