package ir.sahand.tour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class LoginPageFragment extends Fragment {
    private Button login_btn;
    private EditText user_email;
    private EditText user_password;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page , container , false);
        login_btn = (Button) view.findViewById(R.id.login_submit_button);
        user_email=(EditText) view.findViewById(R.id.login_email_insert);
        user_password=(EditText) view.findViewById(R.id.login_password_insert);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email=user_email.getText().toString();
                final String password=user_password.getText().toString();
                loginUsers(email , password);
            }
        });
        return view;
    }

    public void loginUsers(final String email , String password){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SignupResponse> call = apiInterface.loginUser(email , password);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful() && !response.body().getError()){
                    AppPreferenceTools.getInstance(getContext().getApplicationContext()).saveUserAuthenticationInfo(response.body());
                    startActivity(new Intent( getContext() , MainActivity.class));
                } else {

                }
            }
            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Logged in failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
