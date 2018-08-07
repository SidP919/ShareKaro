package com.example.android.sharekaro;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

import static android.app.Activity.RESULT_OK;



public class ShareFileFragment extends Fragment {

    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);
    //SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
    int monthNo = cal.get(Calendar.MONTH);
    //String month = month_date.format(cal.getTime());
    int year = cal.get(Calendar.YEAR);
    int hour = cal.get(Calendar.HOUR_OF_DAY);
    int minute = cal.get(Calendar.MINUTE);
    int ms = cal.get(Calendar.MILLISECOND);

    ImageView iv;
    Button b3, b2;
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_share_file, container, false);
        iv = v.findViewById(R.id.upImage);
        b2 = v.findViewById(R.id.browse_button);
        b3 = v.findViewById(R.id.upload_button);
        b2.setOnClickListener(new MyClickListener());
        b3.setOnClickListener(new MyClickListener());
        tv = v.findViewById(R.id.loading_tv);
        return v;
    }

    public String getImageName(){
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //SimpleDateFormat month_date = new SimpleDateFormat("MMMM");
        int monthNo = cal.get(Calendar.MONTH);
        //String month = month_date.format(cal.getTime());
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int ms = cal.get(Calendar.MILLISECOND);
        String s = "Image-"+day+""+monthNo+""+year+"-"+hour+""+minute+""+ms+".png";
        return s;
    }

    public class MyClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.browse_button:{
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent,"Select Pictures"),1);
                }
                    break;
                case R.id.upload_button:{
                    tv.setText("Upload Starting...");
                    FirebaseStorage fd = FirebaseStorage.getInstance();
                    StorageReference storageRef = fd.getReference();
                    StorageReference imagesRef = storageRef.child("images");
                    StorageReference sr = imagesRef.child(getImageName());

                    sr.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            tv.setText("#Share_Karo");
                            Toast.makeText(ShareFileFragment.super.getContext(), "Image Uploaded Successfully.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            tv.setText("#Share_Karo");
                            Toast.makeText(ShareFileFragment.super.getContext(), "Image Upload Failed! Try Again.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            long tb = taskSnapshot.getTotalByteCount();
                            long bt = taskSnapshot.getBytesTransferred();
                            tv.setText("Uploading... "+bt*100/tb+"%");
                        }
                    });
                }
                    break;
            }

        }
    }

    Uri filepath;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK && data!= null && data.getData()!=null){
            try{
                filepath = data.getData();
                Bitmap bp = MediaStore.Images.Media.getBitmap(ShareFileFragment.super.getActivity().getContentResolver(),filepath);
                iv.setImageBitmap(bp);
            }catch (Exception e){

            }
        }
    }
}
