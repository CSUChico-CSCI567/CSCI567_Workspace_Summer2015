package examples.csci567.locationservicesexample;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String TAG = "LocationServicesExample";

    private View rootView;

    private static FusedLocationService fusedLocationService;

    public MainActivityFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        fusedLocationService = new FusedLocationService(getActivity().getBaseContext(), new FusedLocationReceiver() {

            @Override
            public void onLocationChanged(Location location) {
                Log.i(TAG, "I'm the receiver, let's do my job!");
                //if(location.getAccuracy()<= FusedLocationService.MINIMUM_ACCURACY) {
                    // Called when a new location is found by the network location provider.
                    TextView lattext = (TextView) rootView.findViewById(R.id.latitude);
                    TextView lontext = (TextView) rootView.findViewById(R.id.longitude);
                    TextView accuracytext = (TextView) rootView.findViewById(R.id.accuracy);

                    String lat = "Latitude:  " + ((Double) location.getLatitude()).toString();
                    String lon = "Longitude: " + ((Double) location.getLongitude()).toString();
                    String acc = "Accuracy:  " + ((Float) location.getAccuracy()).toString();

                    lattext.setText(lat);
                    lontext.setText(lon);
                    accuracytext.setText(acc);
                //}
            }
        });

        return rootView;
    }


}
