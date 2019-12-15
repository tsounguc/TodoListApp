package mail.gvsu.edu.todolistapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText editText;
    private Button button;
    private ListView listView;

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText)findViewById(R.id.item_edit_text);
        button = (Button)findViewById(R.id.add_btn);
        listView = (ListView)findViewById(R.id.list_item);

        // Read data and store in arrayList
        arrayList = FileHelper.readData(this);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(this);

        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_btn:
                String itemEntered = editText.getText().toString();
                if(itemEntered.isEmpty()){
                    Toast.makeText(this, "Please Entered Item", Toast.LENGTH_SHORT).show();
                }
                if(!itemEntered.isEmpty()) {
                    arrayAdapter.add(itemEntered);
                    editText.setText("");
                    FileHelper.writeData(arrayList, this);
                    Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        arrayList.remove(position);
        arrayAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }
}
