package artem.com.tz_osadchneko_vrgsoft.engine.network;

import artem.com.tz_osadchneko_vrgsoft.common.BaseConstants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static Retrofit newInstance(){

        return new Retrofit.Builder()
                .baseUrl(BaseConstants.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
