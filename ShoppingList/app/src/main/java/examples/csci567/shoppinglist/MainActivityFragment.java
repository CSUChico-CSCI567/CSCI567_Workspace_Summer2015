package examples.csci567.shoppinglist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Vector;

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
        final ListView listView1 = (ListView) rootView.findViewById(R.id.listview);
        //TextView textView = (TextView) rootView.findViewById(R.id.textview);
        List<Item> items = Item.listAll(Item.class);
        ArrayAdapter<String> adapter;
        if(items.size()>0) {
            Vector<String> itemvector = new Vector<String>();
            //String content = "";
            for (int i = 0; i < items.size(); i++) {
                itemvector.add(items.get(i).name);
            }
            String[] s = itemvector.toArray(new String[itemvector.size()]);
            adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                    android.R.layout.simple_list_item_1, s);
        }
        else{
            Vector<String> itemvector = new Vector<String>();
            itemvector.add("No Items");
            String[] s = itemvector.toArray(new String[itemvector.size()]);
            adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                    android.R.layout.simple_list_item_1, s);

        }
        listView1.setAdapter(adapter);
        listView1.setClickable(true);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, final int position, long arg3) {
                Toast.makeText(getActivity(),arg0.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
            }
        });
        listView1.setLongClickable(true);
        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View view, final int position, long arg3) {
                Toast.makeText(getActivity(),"Long Click: " + listView1.getItemAtPosition(position).toString(),Toast.LENGTH_LONG).show();
                return true;
            }
        });
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
               /* TextView textView = (TextView) rootView.findViewById(R.id.textview);
                String text = textView.getText().toString() + "\n" + editText.getText().toString();
                textView.setText(text);*/

                Item item = new Item(editText.getText().toString());
                item.save();
                ListView listView1 = (ListView) rootView.findViewById(R.id.listview);
                List<Item> items = Item.listAll(Item.class);
                ArrayAdapter<String> adapter;

                Vector<String> itemvector = new Vector<String>();
                //String content = "";
                for (int i = 0; i < items.size(); i++) {
                    itemvector.add(items.get(i).name);
                }
                String[] s = itemvector.toArray(new String[itemvector.size()]);
                adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
                        android.R.layout.simple_list_item_1, s);

                listView1.setAdapter(adapter);

                Toast.makeText(getActivity(), "Item Added", Toast.LENGTH_LONG).show();
                return;
            }
            Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_LONG).show();
        }
    }
}
