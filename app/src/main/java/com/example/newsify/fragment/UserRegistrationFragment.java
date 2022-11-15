package com.example.newsify.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Database;

import com.example.newsify.R;
import com.example.newsify.activity.MainActivity;
import com.example.newsify.roomdatabase.UserDatabase;
import com.example.newsify.roomdatabase.UserEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class UserRegistrationFragment extends Fragment {

    View view;
    LinearLayout goToLogin;
    EditText name, mobileNo, password;
    RadioGroup gender;
    Context mcontext;
    Button signup;
    String userGender;
    public static boolean isAllowed = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_registration, container, false);
        name = view.findViewById(R.id.name);
        mcontext = getContext();
        mobileNo = view.findViewById(R.id.mobileno);
        signup = view.findViewById(R.id.signup);
        password = view.findViewById(R.id.createpassword);
        gender = view.findViewById(R.id.gender);
        goToLogin = view.findViewById(R.id.gotologinly);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickhere();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterUser();
            }
        });
        return view;
    }

    public void setClickhere() {
        LoginUsingPhone signUpUsingPhoneFragment = new LoginUsingPhone();
        // signUpUsingPhoneFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, signUpUsingPhoneFragment).addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void enterUser() {
        String userName = name.getText().toString();
        String userMn = mobileNo.getText().toString();
        String userPassword = password.getText().toString();
        int selectedId = gender.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) view.findViewById(selectedId);

        UserDatabase userDatabase = UserDatabase.getDb(mcontext);

        if (userMn.length() < 10 || userName.isEmpty() || userPassword.isEmpty()) {
            Toast.makeText(mcontext, "Enter valid Data", Toast.LENGTH_SHORT).show();
        } else if (userDatabase.userDao().is_taken(userMn)) {
            Toast.makeText(mcontext, "Already Registred", Toast.LENGTH_SHORT).show();
        } else {
            userDatabase.userDao().enteruser(new UserEntity(userPassword, userMn, userName, radioButton.getText().toString()));
            Toast.makeText(mcontext, "User registred", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getContext(), MainActivity.class));
            loginSet(true);
        }

    }

    private void loginSet(Boolean b) {

        SharedPreferences pref = mcontext.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("flag", b);
        editor.apply();

    }

    private boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Patterns.PHONE.matcher(phoneNumber).matches();
        }
        return false;
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }
}