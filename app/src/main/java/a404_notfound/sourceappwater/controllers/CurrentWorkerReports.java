package a404_notfound.sourceappwater.controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import a404_notfound.sourceappwater.R;
import a404_notfound.sourceappwater.model.ReportViewAdapter;
import a404_notfound.sourceappwater.model.ReportsHolder;

public class CurrentWorkerReports extends AppCompatActivity {

    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_users_reports);

        recyclerView = (RecyclerView) findViewById(R.id.usersReportsRecylcerView);

        recyclerView.setHasFixedSize(true);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getBaseContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        ReportViewAdapter adapter = new ReportViewAdapter(ReportsHolder.getWorkerReports());

        recyclerView.setAdapter(adapter);


    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }
}
