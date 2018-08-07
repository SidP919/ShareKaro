package com.example.android.sharekaro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginFragment extends Fragment {

    private EditText et1;
    private EditText et2;
    private Button b;
    LinearLayout pb;
    LinearLayout loginLL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        et1 = v.findViewById(R.id.login_contact_editText);
        et2 = v.findViewById(R.id.login_password_editText);
        b = v.findViewById(R.id.login_button);
        pb = v.findViewById(R.id.inProcessLayout2);
        loginLL = v.findViewById(R.id.login_LL);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s21 = et1.getText().toString();
                final String s22 = et2.getText().toString();
                if(s21.isEmpty() || s22.isEmpty()){
                    Toast.makeText(LoginFragment.super.getContext(), "Fill all fields to Login", Toast.LENGTH_SHORT).show();
                }else{
                    pb.setVisibility(View.VISIBLE);
                    loginLL.setVisibility(View.INVISIBLE);
                    FirebaseDatabase fd = FirebaseDatabase.getInstance();
                    DatabaseReference refs = fd.getReference("users");
                    refs.child(s21).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            pb.setVisibility(View.INVISIBLE);
                            loginLL.setVisibility(View.VISIBLE);
                            User u = dataSnapshot.getValue(User.class);
                            String pass = u != null ? u.getPwd() : null;
                            if(pass.equals(s22)){
                                Toast.makeText(LoginFragment.super.getContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                                Intent intn = new Intent(LoginFragment.super.getActivity(),SharingActivity.class);
                                startActivity(intn);
                            }else{
                                Toast.makeText(LoginFragment.super.getContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            pb.setVisibility(View.INVISIBLE);
                            loginLL.setVisibility(View.VISIBLE);
                            Toast.makeText(new SignupLoginActivity(), "Login Failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        return v;
    }
}
