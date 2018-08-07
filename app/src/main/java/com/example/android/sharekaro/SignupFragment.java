package com.example.android.sharekaro;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignupFragment extends Fragment {

    Button b1;
    LinearLayout pb;
    EditText et1;
    EditText et2;
    EditText et3;
    RadioButton rb1;
    LinearLayout signLL;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_signup, container, false);
        b1 = v.findViewById(R.id.signup_button);
        pb = v.findViewById(R.id.inProcessLayout2);
        et1 = v.findViewById(R.id.signup_name_editText);
        et2 = v.findViewById(R.id.signup_contact_editText);
        et3 = v.findViewById(R.id.signup_password_editText);
        rb1 = v.findViewById(R.id.male_radioButton);
        signLL = v.findViewById(R.id.signup_LL);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = et1.getText().toString();
                String s2 = et2.getText().toString();
                String s3 = et3.getText().toString();
                String s4 = "";
                if(rb1.isChecked())
                    s4 = "Male";
                else
                    s4 = "Female";
                if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty()){
                    Toast.makeText(SignupFragment.super.getContext(), "Fill all fields to Login", Toast.LENGTH_SHORT).show();
                }else{
                    pb.setVisibility(View.VISIBLE);
                    signLL.setVisibility(View.INVISIBLE);
                    FirebaseDatabase fd = FirebaseDatabase.getInstance();
                    DatabaseReference refs = fd.getReference("users");
                    User u = new User(s2, s4, s1, s3);
                    refs.child(s2).setValue(u);
                    refs.child(s2).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            pb.setVisibility(View.INVISIBLE);
                            signLL.setVisibility(View.VISIBLE);
                            Toast.makeText(SignupFragment.super.getContext(), "New User Created", Toast.LENGTH_SHORT).show();
                            TabLayout tab = SignupFragment.super.getActivity().findViewById(R.id.tab);
                            tab.getTabAt(0).select();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            pb.setVisibility(View.INVISIBLE);
                            signLL.setVisibility(View.VISIBLE);
                            Toast.makeText(SignupFragment.super.getContext(),"Unable to create user",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        return v;
    }


}
