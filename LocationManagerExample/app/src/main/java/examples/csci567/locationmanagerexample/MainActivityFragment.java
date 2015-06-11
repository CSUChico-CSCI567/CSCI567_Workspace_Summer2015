package examples.csci567.locationmanagerexample;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View rootView;
    private LocationListener locationListener;
    private LocationManager locationManager;
    public static boolean set;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //http://developer.android.com/reference/android/location/Criteria.html
        Criteria coarse = new Criteria();
        //coarse.setAccuracy(Criteria.ACCURACY_COARSE);
        coarse.setAccuracy(Criteria.ACCURACY_FINE);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                TextView lattext = (TextView) rootView.findViewById(R.id.latitude);
                TextView lontext = (TextView) rootView.findViewById(R.id.longitude);
                TextView accuracytext = (TextView) rootView.findViewById(R.id.accuracy);

                String lat = "Latitude:  "+((Double) location.getLatitude()).toString();
                String lon = "Longitude: "+((Double) location.getLongitude()).toString();
                String acc = "Accuracy:  "+((Float)location.getAccuracy()).toString();

                lattext.setText(lat);
                lontext.setText(lon);
                accuracytext.setText(acc);
            }

            //public void onStatusChanged(String provider, int status, Bundle extras) {}


            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}

            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub

            }
        };
        //temporarily storing provider to check against null
        String provider = locationManager.getBestProvider(coarse, true);
        set=false;
        // Register the listener with the Location Manager to receive location updates
        if(provider != null){
            set=true;
            //Check every 60ms for location change
            //Can change time to 0 to ignore time and set min Distance to update when phone moves a
            //certain distance.
            //LocationListener is called on location change.
            locationManager.requestLocationUpdates(provider, 1000, 0, locationListener);
        }



        return rootView;
    }

    /**
     * Function to try to renable location manager if not initially set
     */
    public boolean checkLocation() {
        Criteria coarse = new Criteria();
        coarse.setAccuracy(Criteria.ACCURACY_COARSE);
        String provider = locationManager.getBestProvider(coarse, true);
        set = false;
        // Register the listener with the Location Manager to receive location updates
        if (provider != null) {
            set = true;
            locationManager.requestLocationUpdates(provider, 0, 1500, locationListener);
        }
        return set;
    }
}
