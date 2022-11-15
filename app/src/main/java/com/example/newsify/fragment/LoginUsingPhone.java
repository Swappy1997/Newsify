package com.example.newsify.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


import com.example.newsify.R;
import com.example.newsify.activity.MainActivity;
import com.example.newsify.roomdatabase.UserDatabase;
public class LoginUsingPhone extends Fragment {
    View view;
    LinearLayout clickhere;
    AppCompatButton loginBtn;
    EditText mobileno, enterPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login_using_phone, container, false);
        clickhere = view.findViewById(R.id.donthaveaccountly);
        loginBtn = view.findViewById(R.id.login);
        mobileno = view.findViewById(R.id.phoneno);
        enterPassword = view.findViewById(R.id.enterpassword);

        clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setClickhere();
            }
        });
        setLoginBtn();
        return view;

    }

    private void setLoginBtn() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setlogin();
            }
        });
    }

    public void setClickhere() {
        UserRegistrationFragment signUpUsingPhoneFragment = new UserRegistrationFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, signUpUsingPhoneFragment).addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    private void loginSet(Boolean b) {

        SharedPreferences pref = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("flag", b);
        editor.apply();

    }

    public void setlogin() {
        String enterphone = mobileno.getText().toString();
        String enterPass = enterPassword.getText().toString();
        UserDatabase userDatabase = UserDatabase.getDb(getContext());
        if (enterphone.isEmpty() || enterPass.isEmpty()) {
            Toast.makeText(getContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
        } else {
            if (userDatabase.userDao().login(enterphone, enterPass)) {
                startActivity(new Intent(getContext(), MainActivity.class));
                loginSet(true);
            } else {
                Toast.makeText(getContext(), "Mobile Number and Password Mismatch", Toast.LENGTH_SHORT).show();
            }
        }
    }
}