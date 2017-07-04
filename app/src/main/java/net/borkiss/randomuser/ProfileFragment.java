package net.borkiss.randomuser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.borkiss.randomuser.data.model.User;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class ProfileFragment extends Fragment {

    private static final String ARG_USER = "user";

    private User user;

    public static ProfileFragment newInstance(User user) {

        Bundle args = new Bundle();

        ProfileFragment fragment = new ProfileFragment();
        args.putSerializable(ARG_USER, user);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = (User) getArguments().getSerializable(ARG_USER);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView gender = (TextView) view.findViewById(R.id.tvTitle);
        gender.setText(user.getTitle());

        TextView name = (TextView) view.findViewById(R.id.tvName);
        final String userName = user.getFirstName() +
                (user.getLastName() != null && user.getLastName().length() > 0
                        ? " " + user.getLastName() : "");
        name.setText(userName);

        TextView street = (TextView) view.findViewById(R.id.tvStreet);
        street.setText(user.getStreet());

        TextView city = (TextView) view.findViewById(R.id.tvCity);
        city.setText(user.getCity());

        TextView state = (TextView) view.findViewById(R.id.tvState);
        state.setText(user.getState());

        TextView postcode = (TextView) view.findViewById(R.id.tvPostcode);
        postcode.setText(user.getPostcode());

        TextView email = (TextView) view.findViewById(R.id.tvEmail);
        email.setText(user.getEmail());

        TextView dateOfBirth = (TextView) view.findViewById(R.id.tvDateOfBirth);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy", Locale.ENGLISH);
        dateOfBirth.setText(dateFormat.format(user.getDateOfBirth()));

        TextView phone = (TextView) view.findViewById(R.id.tvPhone);
        phone.setText(user.getPhone());

        TextView cellPhone = (TextView) view.findViewById(R.id.tvCellPhone);
        cellPhone.setText(user.getCell());

        TextView nationality = (TextView) view.findViewById(R.id.tvNationality);
        nationality.setText(user.getNationality());

        return view;
    }
}
