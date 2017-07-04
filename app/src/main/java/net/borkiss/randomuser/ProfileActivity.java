package net.borkiss.randomuser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import net.borkiss.randomuser.data.model.User;

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
                .asBitmap().centerCrop()
                .placeholder(R.mipmap.ic_icon)
                .into(new BitmapImageViewTarget(icon) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        icon.setImageDrawable(circularBitmapDrawable);
                    }
                });

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
