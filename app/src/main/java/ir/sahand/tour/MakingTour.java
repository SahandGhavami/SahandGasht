package ir.sahand.tour;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class MakingTour extends AppCompatActivity {
    private static final int REQUEST_CODE_CONTENT = 1000;
    private EditText tour_name;
    private EditText tour_location;
    private EditText tour_number;
    private EditText tour_cost;
    private EditText tour_date;
    private EditText tour_details;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private Button btn;
    private Uri imageUri;
    private ImageView[] IMG = {img1 , img2 , img3 , img4 , img5 , img6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_tour);

        tour_name = (EditText) findViewById (R.id.making_tour_activity_name);
        tour_location = (EditText) findViewById (R.id.making_tour_activity_location);
        tour_number = (EditText) findViewById (R.id.making_tour_activity_number);
        tour_cost = (EditText) findViewById (R.id.making_tour_activity_cost);
        tour_date = (EditText) findViewById (R.id.making_tour_activity_date);
        tour_details = (EditText) findViewById (R.id.making_tour_activity_details);
        img1 = (ImageView) findViewById (R.id.making_tour_activity_image1);
        IMG[0] = img1;
        img2 = (ImageView) findViewById (R.id.making_tour_activity_image2);
        IMG[1] = img2;
        img3 = (ImageView) findViewById (R.id.making_tour_activity_image3);
        IMG[2] = img3;
        img4 = (ImageView) findViewById (R.id.making_tour_activity_image4);
        IMG[3] = img4;
        img5 = (ImageView) findViewById (R.id.making_tour_activity_image5);
        IMG[4] = img5;
        img6 = (ImageView) findViewById (R.id.making_tour_activity_image6);
        IMG[5] = img6;
        btn = (Button) findViewById (R.id.making_tour_activity_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tour_name.getText().toString().trim();
                String location = tour_location.getText().toString().trim();
                String number = tour_number.getText().toString().trim();
                String cost = tour_cost.getText().toString().trim();
                String date = tour_date.getText().toString().trim();
                String details = tour_details.getText().toString().trim();
            }
        });
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImage();
            }
        });
    }
    public void getImage(){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("Image/*");
        startActivityForResult( intent , REQUEST_CODE_CONTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONTENT){
            if (resultCode == RESULT_OK){
                imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap( this.getContentResolver() , imageUri);
                    for (int i=0 ; i<6 ; i++){
                        if (IMG[i] == null){
                            IMG[i].setImageBitmap(bitmap);
                        }else{
                            IMG[i+1].setImageBitmap(bitmap);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
