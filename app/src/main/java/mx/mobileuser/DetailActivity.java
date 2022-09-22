package mx.mobileuser;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import mx.mobileuser.R;


public class DetailActivity extends AppCompatActivity {
    private String phone;
    private String img;
    private String direction;
    Button btnRegreso;

    private ImageView imgUser;
    private TextView phoneUser;
    private TextView directionUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        directionUser = findViewById(R.id.txtDirectionDetail);
        phoneUser = findViewById(R.id.txtPhoneDetail);
        imgUser = findViewById(R.id.imgUserDetail);
        btnRegreso = findViewById(R.id.btnRegreso);
        btnRegreso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onBackPressed();
            }
        });

        Bundle extras = getIntent().getExtras();

        if(extras == null) Toast.makeText(this, "adios", Toast.LENGTH_LONG).show();

        phone = extras.getString("phone") != "" ? extras.getString("phone") : "Sin numero";
        img = extras.getString("img");
        direction = extras.getString("direction") != "" ? extras.getString("direction")
                    : "Sin Direccion";

        Glide.with(this).load(img)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imgUser);

        phoneUser.setText(phone);
        directionUser.setText(direction);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }



}
