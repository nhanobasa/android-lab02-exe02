package vn.nhantd.tranducnhan_ktra2_bai2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import vn.nhantd.tranducnhan_ktra2_bai2.model.QuyenGop;

/**
 * Created by Tran Duc Nhan on 2021-05-14
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton btfloa;
    private RecyclerView recyclerView;
    private RecycleViewAdapter adapter;
    private SQLiteObjectOpenHelper db;
    private QuyenGop quyenGop;
    private List<QuyenGop> quyenGopList;
    private List<QuyenGop> searchRs;
    private TextView txtTotal;

    private androidx.appcompat.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quyenGopList = new ArrayList<>();
        searchRs = new ArrayList<>();

        db = new SQLiteObjectOpenHelper(this);
        initView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecycleViewAdapter(MainActivity.this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        quyenGopList = db.getAll();
        adapter.setData(quyenGopList);
        recyclerView.setAdapter(adapter);
        txtTotal.setText("Total Money: " + adapter.totalMoney().toString());

        btfloa.setOnClickListener(this);
    }

    private void initView() {
        btfloa = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        txtTotal = findViewById(R.id.txtTotal);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton: {
                Intent a = new Intent(this, AddActivity.class);
                startActivity(a);
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (androidx.appcompat.widget.SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.i("test_aa", "close");
                quyenGopList = db.getAll();
                adapter.setData(quyenGopList);
                adapter.notifyDataSetChanged();
                txtTotal.setText("Total Money: " + adapter.totalMoney().toString());
                return false;
            }
        });
        searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchRs = db.search(query);
                adapter.setData(searchRs);
                adapter.notifyDataSetChanged();
                txtTotal.setText("Total Money: " + adapter.totalMoney().toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
    }
}