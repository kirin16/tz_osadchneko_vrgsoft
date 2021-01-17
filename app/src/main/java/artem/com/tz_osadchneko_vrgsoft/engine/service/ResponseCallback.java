package artem.com.tz_osadchneko_vrgsoft.engine.service;

import java.util.List;

import artem.com.tz_osadchneko_vrgsoft.model.Children;

/**
 * Callback responsible for data transfer into @ArticleViewModel
 */

public interface ResponseCallback {

    void onResponse(List<Children> childrenList);

}
