package net.borkiss.randomuser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import net.borkiss.randomuser.data.model.User;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

public class ProfileActivity extends AppCompatActivity {

    private static final String EXTRA_USER = "ProfileActivity.User";

    private User user;
    private ImageView icon;

    public static Intent newIntent(Context context, User user) {
        Intent intent = new Intent(context, ProfileActivity.class);
        intent.putExtra(EXTRA_USER, user);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = (User) getIntent().getSerializableExtra(EXTRA_USER);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupToolbar();

        icon = (ImageView) findViewById(R.id.icon);

        Glide.with(this)
                .load(user.getLargePicture())
                .apply(new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(200)))
                .into(icon);
        Fragment container = getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
        if (container == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, ProfileFragment.newInstance(user))
                    .commit();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(user.getFirstName() + " " + user.getLastName());
        }
    }
}
