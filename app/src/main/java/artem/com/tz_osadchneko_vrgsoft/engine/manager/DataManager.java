package artem.com.tz_osadchneko_vrgsoft.engine.manager;

import android.util.Log;

import artem.com.tz_osadchneko_vrgsoft.common.BaseConstants;
import artem.com.tz_osadchneko_vrgsoft.engine.network.RetrofitClient;
import artem.com.tz_osadchneko_vrgsoft.engine.service.ApiService;
import artem.com.tz_osadchneko_vrgsoft.engine.service.ResponseCallback;
import artem.com.tz_osadchneko_vrgsoft.model.Reply;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Negotiator between response and ArticleViewModel
 */

public class DataManager {

    private ResponseCallback responseCallback;

    public static DataManager newInstance(ResponseCallback callback) {

        return new DataManager(callback);

    }

    private DataManager(ResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
    }

    public void getResponse() {

        ApiService apiService = RetrofitClient.newInstance().create(ApiService.class);
        Call<Reply> call = apiService.getResponseAsJson();
        call.enqueue(new Callback<Reply>() {
            @Override
            public void onResponse(Call<Reply> call, Response<Reply> response) {

                if (response.body() != null) {
                    responseCallback.onResponse(response.body().getData().getChildren());
                }

            }

            @Override
            public void onFailure(Call<Reply> call, Throwable t) {
                Log.e(BaseConstants.TAG, t.toString());
            }
        });
    }

}
