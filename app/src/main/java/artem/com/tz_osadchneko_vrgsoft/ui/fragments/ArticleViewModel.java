package artem.com.tz_osadchneko_vrgsoft.ui.fragments;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import artem.com.tz_osadchneko_vrgsoft.common.BaseConstants;
import artem.com.tz_osadchneko_vrgsoft.engine.manager.DataManager;
import artem.com.tz_osadchneko_vrgsoft.engine.service.ResponseCallback;
import artem.com.tz_osadchneko_vrgsoft.model.Children;

public class ArticleViewModel extends ViewModel implements ResponseCallback {

    private MutableLiveData<List<Children>> data;

    public LiveData<List<Children>> getData() {
        if (data == null) {
            data = new MutableLiveData<>();
            DataManager.newInstance(this).getResponse();
            Log.e(BaseConstants.TAG, "1");
        }
        Log.e(BaseConstants.TAG, "2");
        return data;
    }

    @Override
    public void onResponse(List<Children> childrenList) {
        data.setValue(childrenList);
    }

}
