package qj.ccmtjf.com.lib;

import io.reactivex.Flowable;
import qj.ccmtjf.com.lib.api.ApiResponse;
import qj.ccmtjf.com.lib.entity.ListData;

/**
 * Created by tongqinyuan on 2018/3/8.
 */

public interface HttpDataSource {


    Flowable<ApiResponse<ListData>> getInfo(int page);
}
