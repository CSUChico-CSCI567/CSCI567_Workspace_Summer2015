package examples.csci567.shoppinglist;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
        TextView textView = (TextView) rootView.findViewById(R.id.textview);
        String contents = "";
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/test.txt");
            FileReader filereader = new FileReader(file);
            BufferedReader rbuf = new BufferedReader(filereader);
            while (rbuf.ready()) {
                contents += rbuf.readLine() + "\n";
            }
        }
        catch(Exception e){
            Log.e("ShoppingList-MAF: d", e.toString());
            contents="";
        }
        if ( contents == ""){
            textView.setText("No Items");
        }
        else{
            textView.setText(contents);
        }
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
                String text = textView.getText().toString() + "\n" + editText.getText().toString();
                textView.setText(text);
                try {
                    File file = new File(Environment.getExternalStorageDirectory().getPath() + "/test.txt");
                    FileWriter filewriter = new FileWriter(file);
                    BufferedWriter wbuf = new BufferedWriter(filewriter);
                    wbuf.write(editText.getText().toString()+"\n");
                    wbuf.close();
                }
                catch (Exception e){
                    Log.e("ShoppingList-MAF: ", e.toString());
                }
                Toast.makeText(getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_LONG).show();
        }
    }
}
