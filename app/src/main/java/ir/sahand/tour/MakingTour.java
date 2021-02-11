package ir.sahand.tour;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alirezaafkar.sundatepicker.DatePicker;
import com.alirezaafkar.sundatepicker.interfaces.DateSetListener;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import ir.sahand.tour.model.TourResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakingTour extends AppCompatActivity implements DateSetListener {
    private static final int REQUEST_CODE_CONTENT = 1000;
    private EditText tour_name;
    private EditText tour_location;
    private EditText tour_number;
    private EditText tour_cost;
    private TextView tour_date;
    private TextView tour_return_date;
    private EditText tour_details;
    private EditText tour_description;
    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;
    private ImageView img5;
    private ImageView img6;
    private Button btn;
    private Uri imageUri;
    private ImageView[] IMG = {img1, img2, img3, img4, img5, img6};
    private String selected_date;
    private String selected_return_date;
    private ArrayList<Uri> tourImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_making_tour);

        tour_name = (EditText) findViewById(R.id.making_tour_activity_name);
        tour_location = (EditText) findViewById(R.id.making_tour_activity_location);
        tour_number = (EditText) findViewById(R.id.making_tour_activity_number);
        tour_cost = (EditText) findViewById(R.id.making_tour_activity_cost);
        tour_date = (TextView) findViewById(R.id.making_tour_activity_date);
        tour_return_date = (TextView) findViewById(R.id.making_tour_activity_return_date);
        tour_details = (EditText) findViewById(R.id.making_tour_activity_details);
        tour_description = (EditText) findViewById(R.id.making_tour_activity_description);
        img1 = (ImageView) findViewById(R.id.making_tour_activity_image1);
        IMG[0] = img1;
        img2 = (ImageView) findViewById(R.id.making_tour_activity_image2);
        IMG[1] = img2;
        img3 = (ImageView) findViewById(R.id.making_tour_activity_image3);
        IMG[2] = img3;
        img4 = (ImageView) findViewById(R.id.making_tour_activity_image4);
        IMG[3] = img4;
        img5 = (ImageView) findViewById(R.id.making_tour_activity_image5);
        IMG[4] = img5;
        img6 = (ImageView) findViewById(R.id.making_tour_activity_image6);
        IMG[5] = img6;
        btn = (Button) findViewById(R.id.making_tour_activity_button);

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

        tour_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePicker.Builder()
                        .id(R.id.making_tour_activity_date)
                        .build(MakingTour.this)
                        .show(getSupportFragmentManager(), "");
            }
        });
        tour_return_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePicker.Builder()
                        .id(R.id.making_tour_activity_return_date)
                        .build(MakingTour.this)
                        .show(getSupportFragmentManager(), "");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tour_name.getText().toString().trim();
                String location = tour_location.getText().toString().trim();
                String number = tour_number.getText().toString().trim();
                String cost = tour_cost.getText().toString().trim();
                String details = tour_details.getText().toString().trim();
                String description = tour_description.getText().toString().trim();
                if (name.length() == 0) {
                    tour_name.requestFocus();
                    tour_name.setError("نام تور نمیتواند خالی باشد !");
                } else if (location.length() == 0) {
                    tour_location.requestFocus();
                    tour_location.setError("مکان تور نمیتواند خالی باشد !");
                } else if (number.length() == 0) {
                    tour_number.requestFocus();
                    tour_number.setError("ظرفیت تور نمیتواند خالی باشد !");
                } else if (cost.length() == 0) {
                    tour_cost.requestFocus();
                    tour_cost.setError("قیمت تور نمیتواند خالی باشد !");
                } else if (description.length() == 0) {
                    tour_description.requestFocus();
                    tour_description.setError("مشخصات تور نمیتواند خالی باشد !");
                } else if (details.length() == 0) {
                    tour_details.requestFocus();
                    tour_details.setError("جزئیات تور نمیتواند خالی باشد !");
                } else if (3 < 2 && selected_date.length() == 0) {
                    tour_date.requestFocus();
                    tour_date.setError("ظرفیت تور نمیتواند خالی باشد !");
                } else if (3 < 2 && selected_return_date.length() == 0) {
                    tour_return_date.requestFocus();
                    tour_return_date.setError("ظرفیت تور نمیتواند خالی باشد !");
                } else {
                    passDataToBackEnd(name, location, number, selected_date, selected_return_date, cost, details, description);
                }
            }
        });

        requestPermission();
    }

    private void passDataToBackEnd(String name, String location, String number, String selected_date, String selected_return_date, String cost, String details, String description) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("name", name);
        builder.addFormDataPart("location", location);
        builder.addFormDataPart("capacity", number);
        builder.addFormDataPart("cost", cost);
        builder.addFormDataPart("details", details);
        builder.addFormDataPart("description", description);
        builder.addFormDataPart("date", selected_date);
        builder.addFormDataPart("return_date", selected_return_date);
        //builder.addFormDataPart("capacity", "4222");

        MultipartBody.Part[] imageParts = new MultipartBody.Part[tourImages.size()];
        for (int i = 0; i < tourImages.size(); i++) {
            try {
                InputStream is = getContentResolver().openInputStream(tourImages.get(i));

                byte[] bytes = new byte[is.available()];
                while (is.read(bytes) != -1) ;

                byte[] array = new byte[7]; // length is bounded by 7
                builder.addFormDataPart(
                        "tour_image[]",
                        (new Random().nextInt()) + ".jpg",
                        RequestBody.create(
                                MediaType.parse("multipart/form-data"),
                                bytes
                        )
                );
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<TourResponse> call = apiInterface.createTour(builder.build());

        call.enqueue(new Callback<TourResponse>() {
            @Override
            public void onResponse(Call<TourResponse> call, final Response<TourResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().isError()) {
                        Toast.makeText(MakingTour.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        return;
                    }

                    finish();
                } else {
                    Toast.makeText(MakingTour.this, "Making Tours Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TourResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    Log.d("user error", t.getLocalizedMessage());
                    Toast.makeText(MakingTour.this, "My Tours Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getImage() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, REQUEST_CODE_CONTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONTENT) {
            if (resultCode == RESULT_OK) {
                tourImages.add(data.getData());
                try {
                    for (int i = 0; i < 6; i++) {
                        if (i >= tourImages.size()) break;
                        IMG[i].setImageURI(tourImages.get(i));
                    }
                } catch (Exception e) {
                    Log.e("E/IMAGE", e.getStackTrace().toString());
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDateSet(int id, @Nullable Calendar calendar, int day, int month, int year) {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd H:m:s");
        Date selectedDate = calendar.getTime();
        String dateForBackend = dateTimeFormatter.format(selectedDate);

        if (id == R.id.making_tour_activity_date) {
            tour_date.setText(Utils.convertTimestampToHumanReadableString(dateForBackend));
            selected_date = dateForBackend;
            if (selected_date.length() == 0) {
                tour_date.requestFocus();
                tour_date.setError("ظرفیت تور نمیتواند خالی باشد !");
            }
        } else {
            tour_return_date.setText(Utils.convertTimestampToHumanReadableString(dateForBackend));
            selected_return_date = dateForBackend;
            if (selected_return_date.length() == 0) {
                tour_return_date.requestFocus();
                tour_return_date.setError("ظرفیت تور نمیتواند خالی باشد !");
            }
        }

        System.out.println(dateForBackend);
//        if (id == making_tour_activity_date) {
//            mStartDate.setDate(day, month, year);
//            tour_date.setText(mStartDate.getDate());
//        } else {
//            mEndDate.setDate(day, month, year);
//            tour_date.setText(mEndDate.getDate());
//        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 201 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 201);
        }
    }
}
