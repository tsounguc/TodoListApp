package mail.gvsu.edu.todolistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> itemsEntered;
    private EditText editText;
    private Button button;
//    private ListView listView;
    private RecyclerView recyclerView;
    private DividerItemDecoration dividerItemDecoration;

//    private ArrayAdapter<String> arrayAdapter;
    RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        editText = (EditText)findViewById(R.id.item_edit_text);
        button = (Button)findViewById(R.id.add_btn);
//        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);

//        // Read data and store in itemsEntered
//        itemsEntered = FileHelper.readData(this);
//        recyclerAdapter = new RecyclerAdapter(itemsEntered, this);

        //        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(recyclerAdapter);
        initRecyclerView();
        button.setOnClickListener(this);

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Read data and store in itemsEntered
        itemsEntered = FileHelper.readData(this);
        recyclerAdapter = new RecyclerAdapter(itemsEntered, this);
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
//                    arrayAdapter.add(itemEntered);
                    itemsEntered.add(itemEntered);
                    FileHelper.writeData(itemsEntered, this);
                    recyclerAdapter.notifyDataSetChanged();
                    editText.setText("");

                    Toast.makeText(this, "Item Added", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        itemsEntered.remove(position);
        //recyclerAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            itemsEntered.remove(viewHolder.getAdapterPosition());
            FileHelper.writeData(itemsEntered, recyclerView.getContext());
            recyclerAdapter.notifyDataSetChanged();

        }
    };
}
