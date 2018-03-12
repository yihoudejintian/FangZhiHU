package qj.ccmtjf.com.lib;

import javax.inject.Inject;

import io.reactivex.Flowable;
import qj.ccmtjf.com.lib.api.ApiResponse;
import qj.ccmtjf.com.lib.api.LoanService;
import qj.ccmtjf.com.lib.entity.ListData;

/**
 * Created by tongqinyuan on 2018/3/8.
 */

public class HttpDataSourceImpl implements HttpDataSource{

    private LoanService mLoanService;

    @Inject
    public HttpDataSourceImpl(LoanService LoanService) {
        this.mLoanService = LoanService;
    }


    public void setLoanService(LoanService LoanService) {
        this.mLoanService = LoanService;

    }

    @Override
    public Flowable<ApiResponse<ListData>> getInfo(int page) {
        return mLoanService.getListInfo(page);
    }
}
