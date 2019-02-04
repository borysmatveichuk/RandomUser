package net.borkiss.randomuser;

import com.google.android.material.snackbar.Snackbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import net.borkiss.randomuser.adapter.UsersAdapter;
import net.borkiss.randomuser.data.UserDataSource;
import net.borkiss.randomuser.data.UserRepository;
import net.borkiss.randomuser.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserRepository userRepository;
    private RecyclerView recyclerView;
    private UsersAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRepository = UserRepository.getInstance(this);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UsersAdapter(new ArrayList<User>(), this);
        recyclerView.setAdapter(adapter);

        RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                loadUsers();
            }
        });

        loadUsers();
    }

    private void loadUsers() {
        userRepository.getAllUsers(new UserDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                adapter.update(users);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onDataNotAvailable() {
                swipeRefreshLayout.setRefreshing(false);
                showNoDateMessage();
            }
        });
    }

    private void setupToolbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        }
    }

    private void showNoDateMessage() {
        Snackbar.make(recyclerView, R.string.noDataInfo, Snackbar.LENGTH_SHORT).show();
    }
}
