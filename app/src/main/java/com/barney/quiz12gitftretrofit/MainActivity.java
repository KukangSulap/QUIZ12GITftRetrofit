package com.barney.quiz12gitftretrofit;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.barney.quiz12gitftretrofit.apiRetrofit.ApiService;
import com.barney.quiz12gitftretrofit.apiRetrofit.InstanceRetrofit;
import com.barney.quiz12gitftretrofit.model.ItemsItem;
import com.barney.quiz12gitftretrofit.model.Response;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtLokasi)
    TextView txtLokasi;
    @BindView(R.id.txtCity)
    TextView txtCity;
    @BindView(R.id.txtState)
    TextView txtState;
    @BindView(R.id.txtClock)
    TextView txtClock;
    @BindView(R.id.rvJadwalSholat)
    RecyclerView rvJadwalSholat;

    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData();
    }

    private void getData() {
        ApiService api = InstanceRetrofit.getInstance();
        Call<Response> call = api.readJadwalSholat();
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body().getStatusCode() == 1) {
                    txtCity.setText(response.body().getCity());
                    txtState.setText(response.body().getCountry());

                    List<ItemsItem> jadwalSholat = response.body().getItems();
                    txtClock.setText(jadwalSholat.get(0).getIsha());
                    rvJadwalSholat.setLayoutManager(new LinearLayoutManager( MainActivity.this));

                    adapter = new CustomAdapter(rvJadwalSholat, MainActivity.this, jadwalSholat);
                    rvJadwalSholat.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        Context context;
        List<ItemsItem> jadwalSholat = new ArrayList<>();

        public CustomAdapter(RecyclerView rcView, Context context, List<ItemsItem> jadwalSholat) {
            this.context = context;
            this.jadwalSholat = jadwalSholat;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(context).inflate(R.layout.listitem, parent, false);
            return new MyViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            holder.tvSubuh.setText(jadwalSholat.get(position).getDateFor());
            holder.tvDhuzur.setText(jadwalSholat.get(position).getDateFor());
            holder.tvAshar.setText(jadwalSholat.get(position).getDateFor());
            holder.tvMaghrib.setText(jadwalSholat.get(position).getDateFor());
            holder.tvIsya.setText(jadwalSholat.get(position).getDateFor());
            holder.tvDate.setText(jadwalSholat.get(position).getDateFor());

        }

        @Override
        public int getItemCount() {
            return jadwalSholat.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tvSubuh,tvDhuzur,tvAshar,tvMaghrib,tvIsya,tvDate;

            public MyViewHolder(View itemView) {
                super(itemView);

                tvSubuh = itemView.findViewById(R.id.tvWaktuSubuh);
                tvAshar =itemView.findViewById(R.id.tvWaktuAshar);
                tvAshar =itemView.findViewById(R.id.tvWaktuAshar);
                tvAshar =itemView.findViewById(R.id.tvWaktuAshar);
                tvAshar =itemView.findViewById(R.id.tvWaktuAshar);
            }
        }
    }
}

//buat branch 1