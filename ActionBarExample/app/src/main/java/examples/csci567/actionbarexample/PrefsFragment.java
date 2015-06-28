package examples.csci567.actionbarexample;

import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by bryandixon on 6/24/15.
 */
public class PrefsFragment extends PreferenceFragment {

    public void onResume() {
        super.onResume();
        getView().setBackgroundColor(Color.WHITE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
