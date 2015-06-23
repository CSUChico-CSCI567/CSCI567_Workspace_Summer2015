package examples.csci567.androidlifecycle;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String TAG = "ALC-Fragment1 ";

    @InjectView(R.id.editbox) EditText editbox1;
    @InjectView(R.id.textview) TextView textview;

    @OnClick(R.id.button) void submit() {
        textview.setText(editbox1.getText());
    }

    boolean isVert;

    public MainActivityFragment() {
    }

    protected void onAttach(){
        Log.d(TAG, "onAttach");
    }

    protected void onCreate(){
        Log.d(TAG, "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, rootView);
        return rootView;
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG, "onResume");
        //Find out screen orientation
        Display display = ((WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();
        switch (orientation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                isVert = true;
                Log.d(TAG, "Vertical");
                break;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                isVert=false;
                Log.d(TAG, "Landscape");
                break;
        }

        SharedPreferences prefs = getActivity().getSharedPreferences(
                "examples.csci567.androidlifecycle", Context.MODE_PRIVATE);
        Resources res = getResources();
        String textviewtext = "examples.csci567.androidlifecycle.textviewtext";
        String preftext = prefs.getString(textviewtext, res.getString(R.string.textviewdefault));
        textview.setText(preftext);
    }

    public void onPause(){
        super.onPause();
        Log.d(TAG, "onPause");
        Display display = ((WindowManager) getActivity().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();
        switch (orientation) {
            case Surface.ROTATION_0:
            case Surface.ROTATION_180:
                if(!isVert){
                    //Log.d(TAG,textview.getText().toString());
                    SharedPreferences prefs = getActivity().getSharedPreferences(
                            "examples.csci567.androidlifecycle", Context.MODE_PRIVATE);
                    String textviewtext = "examples.csci567.androidlifecycle.textviewtext";
                    prefs.edit().putString(textviewtext,textview.getText().toString()).apply();
                }
                else{
                    //Log.d(TAG,textview.getText().toString());
                    SharedPreferences prefs = getActivity().getSharedPreferences(
                            "examples.csci567.androidlifecycle", Context.MODE_PRIVATE);
                    Resources res = getResources();
                    String textviewtext = "examples.csci567.androidlifecycle.textviewtext";
                    prefs.edit().putString(textviewtext,res.getString(R.string.textviewdefault)).apply();

                }
                break;
            case Surface.ROTATION_90:
            case Surface.ROTATION_270:
                Log.d(TAG, "Landscape");
                if(isVert){
                    //Log.d(TAG,textview.getText().toString());
                    SharedPreferences prefs = getActivity().getSharedPreferences(
                            "examples.csci567.androidlifecycle", Context.MODE_PRIVATE);
                    String textviewtext = "examples.csci567.androidlifecycle.textviewtext";
                    prefs.edit().putString(textviewtext,textview.getText().toString()).apply();
                }
                else{
                    //Log.d(TAG,textview.getText().toString());
                    SharedPreferences prefs = getActivity().getSharedPreferences(
                            "examples.csci567.androidlifecycle", Context.MODE_PRIVATE);
                    Resources res = getResources();
                    String textviewtext = "examples.csci567.androidlifecycle.textviewtext";
                    prefs.edit().putString(textviewtext,res.getString(R.string.textviewdefault)).apply();

                }
                break;
        }
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG, "onStop");
    }

    public void onDestroyView(){
        super.onDestroyView();
        Log.d(TAG, "onDestroyView");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void onDetach(){
        super.onDetach();
        Log.d(TAG, "onDetach");
    }
}
