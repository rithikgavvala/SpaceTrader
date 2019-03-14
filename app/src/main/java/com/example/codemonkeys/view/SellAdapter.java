package edu.gatech.cs2340.lab3newcomponents.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codemonkeys.R;
import com.example.codemonkeys.model.TradeGood;

import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.lab3newcomponents.R;
import edu.gatech.cs2340.lab3newcomponents.entity.Student;

/**
 * Adapts the list of students in the model to be a list of graphical elements in view
 */
public class SellAdapter extends RecyclerView.Adapter<SellAdapter.ItemViewHolder> {

    /** a copy of the list of students in the model */
    //TODO call function to create sellItemsList
    private List<TradeGood> sellItemsList = new ArrayList<>();

    /** a listener for a touch event on the student */
    private OnStudentClickListener listener;

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        // hook up to the view for a single student in the system
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sell_item, parent, false);

        return new ItemViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        //bind the student data for one student
        TradeGood item = sellItemsList.get(position);

        Log.d("APP", "Binding: " + position + " " + sellItemsList.get(position));

        holder.name.setText(item.basePrice);
        holder.price.setText(student.getName());


    }

    @Override
    public int getItemCount() {
        return sellItemsList.size();
    }

    public void setTradeGoodsList(List<TradeGood> items) {
        sellItemsList = items;
        notifyDataSetChanged();
    }


    /**
     * This is a holder for the widgets associated with a single entry in the list of students
     */
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.text_item_name);
            price = itemView.findViewById(R.id.text_item_price);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onStudentClicked(sellItemsList.get(position));
                    }
                }
            });

        }
    }

    public interface OnStudentClickListener {
        void onStudentClicked(TradeGood item);
    }

    public void setOnStudentClickListener(OnStudentClickListener listener) {
        this.listener = listener;
    }
}
