package examples.csci567.shoppinglist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Button button = (Button) rootView.findViewById(R.id.submititem);
        button.setOnClickListener(this);
        return rootView;
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.submititem){
            EditText editText = (EditText) rootView.findViewById(R.id.itemedittext);
            if(!editText.getText().toString().matches("")){
                TextView textView = (TextView) rootView.findViewById(R.id.textview);
                textView.setText(editText.getText());
            }
            Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_LONG).show();
        }
    }
}
