package projects.in.projectstructure.utils;
 
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by prime on 16-12-2017.
 */

public class NetworkHelper {
    private static NetworkHelper networkHelper;

    public static NetworkHelper getInstance() {
        if (networkHelper == null)
            networkHelper = new NetworkHelper();

        return networkHelper;
    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }
}
