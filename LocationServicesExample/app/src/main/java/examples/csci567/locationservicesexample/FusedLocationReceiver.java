package examples.csci567.locationservicesexample;

import android.location.Location;

public abstract class FusedLocationReceiver {
    public abstract void onLocationChanged(Location location);
}
