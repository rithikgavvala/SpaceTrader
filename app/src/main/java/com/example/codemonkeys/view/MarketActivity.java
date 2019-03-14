package com.example.codemonkeys.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.codemonkeys.R;
import com.example.codemonkeys.viewmodel.TransactionViewModel;


public class MarketActivity extends AppCompatActivity {

    /** a key for passing data */
    public static final String STUDENT_DATA = "STUDENT_DATA";
    /** an int for the request code */
    private static final int EDIT_REQUEST = 5;
    /** our data model */
    private TransactionViewModel viewModel;
    /** an adapter for the recycler view */
    private SellAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sell_activity);

        /*
         Set up our recycler view by grabbing the layout for a single item
         */
        RecyclerView recyclerView = findViewById(R.id.sell_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        // Setup the adapter for this recycler view
        adapter = new SellAdapter();
        recyclerView.setAdapter(adapter);

        //grab our view model instance
        viewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);

        Log.d("APP", viewModel.getStudents().toString());

    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setStudentList(viewModel.getStudents());

        adapter.setOnStudentClickListener(new StudentAdapter.OnStudentClickListener() {
            @Override
            public void onStudentClicked(Student student) {
                Intent intent = new Intent(ViewAllStudentsActivity.this, EditStudentActivity.class);
                intent.putExtra(STUDENT_DATA, student);
                startActivityForResult(intent, EDIT_REQUEST);
            }
        });
    }

}
