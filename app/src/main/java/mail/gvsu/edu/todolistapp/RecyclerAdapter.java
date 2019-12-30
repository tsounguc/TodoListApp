package mail.gvsu.edu.todolistapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private ArrayList<String> itemsEntered;
    private Context mContext;
//    int count = 0;


    public RecyclerAdapter(ArrayList<String> itemsEntered, Context mContext) {
        this.itemsEntered = itemsEntered;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

//        Log.i(TAG, "onCreateViewHolder: ");

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_item, parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
        Date currentDate = Calendar.getInstance().getTime();
        String dateStr = dateFormat.format(currentDate);
        holder.textView.setText("" + itemsEntered.get(position));
        holder.dateTextView.setText(dateStr);
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on" + itemsEntered.get(position));
//                Toast.makeText(mContext, "Deleted " + itemsEntered.get(position), Toast.LENGTH_SHORT).show();
//                itemsEntered.remove(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsEntered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        TextView dateTextView;
        ConstraintLayout parentLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.Item);
            dateTextView = itemView.findViewById(R.id.date);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
