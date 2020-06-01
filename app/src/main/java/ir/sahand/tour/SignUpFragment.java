package ir.sahand.tour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ir.sahand.tour.model.SignupResponse;
import ir.sahand.tour.webService.APIClient;
import ir.sahand.tour.webService.APIInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpFragment extends Fragment {
    private Button signup_btn;
    private EditText signup_email;
    private EditText signup_password;
    private EditText signup_name;
    private Button backbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_page , container , false);
        signup_btn = (Button) view.findViewById (R.id.signup_submit_button);
        signup_email = (EditText) view.findViewById (R.id.signup_email_insert);
        signup_password = (EditText) view.findViewById (R.id.signup_password_insert);
        signup_name = (EditText) view.findViewById (R.id.signup_name_insert);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_email = signup_email.getText().toString().trim();
                final String user_password = signup_password.getText().toString().trim();
                final String user_name = signup_name.getText().toString().trim();
                registerUsers(user_name , user_password , user_email);
            }
        });

        return view;
    }

    public void registerUsers(String name , String password , String email){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SignupResponse> call = apiInterface.registerUser(name, password, email);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful()){
                    SharedPreferencesHelper.set("token", response.body().getToken());
                    startActivity(new Intent( getContext() , MainActivity.class));
                    Toast.makeText(getContext(), "added!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}