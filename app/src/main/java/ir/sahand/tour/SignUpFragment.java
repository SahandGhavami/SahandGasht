package ir.sahand.tour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private EditText signup_lname;
    private EditText signup_phone_number;
    private TextView sigup_to_login;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup_page, container, false);
        signup_btn = (Button) view.findViewById(R.id.signup_submit_button);
        signup_email = (EditText) view.findViewById(R.id.signup_email_insert);
        signup_password = (EditText) view.findViewById(R.id.signup_password_insert);
        signup_name = (EditText) view.findViewById(R.id.signup_name_insert);
        signup_lname = (EditText) view.findViewById(R.id.signup_lname_insert);
        signup_phone_number = (EditText) view.findViewById(R.id.signup_phone_number_insert);
        sigup_to_login = (TextView) view.findViewById(R.id.signup_to_login);

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user_email = signup_email.getText().toString().trim();
                final String user_password = signup_password.getText().toString().trim();
                final String user_name = signup_name.getText().toString().trim();
                final String user_lname = signup_lname.getText().toString().trim();
                final String user_phone_number = signup_phone_number.getText().toString().trim();
                if (isEmailValid(user_email)) {
                    registerUsers(user_name, user_password, user_email, user_lname, user_phone_number);
                } else {
                    signup_email.setError("ایمیل صحیح وارد کنید!");
                }
            }
        });

        sigup_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPageFragment loginPageFragment = new LoginPageFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.fragment_holder, loginPageFragment);
                transaction.commit();
            }
        });
        return view;
    }

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void registerUsers(String name, String password, String email, String lname, String phone_number) {
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SignupResponse> call = apiInterface.registerUser(name, password, email, lname, phone_number);
        call.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (response.isSuccessful() && !response.body().getError()) {
                    //SharedPreferencesHelper.set("token", response.body().getToken());
                    AppPreferenceTools.getInstance(getContext().getApplicationContext())
                            .saveUserAuthenticationInfo(response.body());
                    startActivity(new Intent(getContext(), MainActivity.class));
                } else {
                    Toast.makeText(getContext(), "This email has signed up before!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
