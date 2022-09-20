package mx.mobileuser.adaptador;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import mx.mobileuser.modelo.DetailActivity;
import mx.mobileuser.R;
import mx.mobileuser.modelo.Result;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UsersHolder> {
    private final List<Result> results;
    private final Context _context;

    public UserAdapter(Context mContext, List<Result> results){
        this.results = results;
        this._context = mContext;
    }



    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(_context).inflate(R.layout.user_item, parent, false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        String direction =results.get(position).getLocation().getCountry() + " "
                + results.get(position).getLocation().getCity() + " "
                + results.get(position).getLocation().getStreet().getName() + " "
                + results.get(position).getLocation().getStreet().getNumber();

        holder._email.setText(results.get(position).getEmail()) ;
        holder._name.setText(results.get(position).getName().getFirst() + " " + results.get(position).getName().getLast());
        holder._genre.setText(results.get(position).getGender());
        Glide.with(_context).load(results.get(position).getPicture().getThumbnail())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder._user);

        holder._detail.setOnClickListener(v -> {
            Intent intent = new Intent(_context, DetailActivity.class);
            intent.putExtra("img", results.get(position).getPicture().getLarge());
            intent.putExtra("direction", direction);
            intent.putExtra("phone", results.get(position).getPhone());
            _context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public static class UsersHolder extends RecyclerView.ViewHolder{
        TextView _email;
        TextView _name;
        TextView _genre;
        Button _detail;
        ImageView _user;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            _email = itemView.findViewById(R.id.txtEmal);
            _detail = itemView.findViewById(R.id.btnDetail);
            _name  = itemView.findViewById(R.id.txtName);
            _genre = itemView.findViewById(R.id.txtGenre);
            _user = itemView.findViewById(R.id.imgUser);
            _detail = itemView.findViewById(R.id.btnDetail);
        }
    }
}
