package adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.develop.gestionpagosservicios.feature.R;

import java.util.List;

import Entities.Movie;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<Movie> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView total, year, month, customer;
        public CheckBox pay;

        public MyViewHolder(View view) {
            super(view);
            total = (TextView) view.findViewById(R.id.total);
            customer = (TextView) view.findViewById(R.id.customer);
            month = (TextView) view.findViewById(R.id.month);
            year = (TextView) view.findViewById(R.id.year);
            pay = (CheckBox) view.findViewById(R.id.pay);
        }
    }


    public MoviesAdapter(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.content_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Movie movie = moviesList.get(position);
        holder.total.setText(movie.getTitle());
        holder.customer.setText(movie.getCustomer());
        holder.month.setText(movie.getGenre());
        holder.year.setText(movie.getYear());
        holder.pay.setChecked(movie.getPay());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
