package mx.mobileuser;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import mx.mobileuser.R;
import  mx.mobileuser.adaptador.UserAdapter;
import  mx.mobileuser.datos.UserClient;
import  mx.mobileuser.modelo.Result;
import  mx.mobileuser.modelo.UserResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RadioButton rbFemale;
    private RadioButton rbMale;
    private RadioButton rbOutFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recyclerView);
        rbFemale = findViewById(R.id.rbFemale);
        rbMale = findViewById(R.id.rbMale);
        rbOutFilter = findViewById(R.id.rbOutFilter);

        OnClicks();

        getUsers();
    }

    private void OnClicks() {
        rbFemale.setOnClickListener(v -> {
            rbOutFilter.setVisibility(View.VISIBLE);
            getUserGenre("female");
        });

        rbMale.setOnClickListener(v -> {
            getUserGenre("male");
            rbOutFilter.setVisibility(View.VISIBLE);
        });

        rbOutFilter.setOnClickListener(v -> {
            rbOutFilter.setVisibility(View.GONE);
            getUsers();
        });
    }

    private void getUsers() {
        Call<UserResponse> apiCall = UserClient.getInstance().getUserService().getUsers();
        apiCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                setAdapter(userResponse.getResults());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e("UserResponse", t.toString());
            }
        });
    }

    private void setAdapter(List<Result> results) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserAdapter usersAdapter = new UserAdapter(this, results);
        recyclerView.setAdapter(usersAdapter);

    }

    public void getUserGenre(String genre) {
        Call<UserResponse> getUsersFilter = UserClient.getInstance().getUserService().getFilterUser(genre);
        getUsersFilter.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                setAdapter(userResponse.getResults());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
