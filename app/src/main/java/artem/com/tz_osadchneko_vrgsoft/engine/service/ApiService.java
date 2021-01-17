package artem.com.tz_osadchneko_vrgsoft.engine.service;

import artem.com.tz_osadchneko_vrgsoft.common.BaseConstants;
import artem.com.tz_osadchneko_vrgsoft.model.Reply;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET(BaseConstants.KEY)
    Call<Reply> getResponseAsJson();
}
