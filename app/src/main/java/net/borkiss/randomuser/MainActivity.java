package net.borkiss.randomuser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import net.borkiss.randomuser.data.UserDataSource;
import net.borkiss.randomuser.data.UserRepository;
import net.borkiss.randomuser.data.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserRepository userRepository;
    private TextView textTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRepository = UserRepository.getInstance();
        setContentView(R.layout.activity_main);
        textTest = (TextView) findViewById(R.id.textTest);

        userRepository.getAllUsers(new UserDataSource.LoadUsersCallback() {
            @Override
            public void onUsersLoaded(List<User> users) {
                final StringBuilder s = new StringBuilder();

                for (User user : users) {
                    s.append(user.getFirstName()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textTest.setText(s.toString());
                    }
                });
            }

            @Override
            public void onDataNotAvailable() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textTest.setText("Data not available");
                    }
                });
            }
        });
    }
}
