package com.barney.quiz12gitftretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import com.barney.quiz12gitftretrofit.apiRetrofit.ApiService;
import com.barney.quiz12gitftretrofit.apiRetrofit.InstanceRetrofit;
import com.barney.quiz12gitftretrofit.model.ItemsItem;
import com.barney.quiz12gitftretrofit.model.Response;
import retrofit2.Call;
import retrofit2.Callback;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.txtLokasi)
    TextView txtLokasi;
    @InjectView(R.id.txtCity)
    TextView txtCity;
    @InjectView(R.id.txtState)
    TextView txtState;
    @InjectView(R.id.txtClock)
    TextView txtClock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

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

                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });

    }
}
