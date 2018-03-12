package dagger.component;



import javax.inject.Singleton;

import dagger.Component;
import dagger.module.CommonModule;
import dagger.module.HttpModule;
import qj.ccmtjf.com.lib.HttpDataSourceImpl;
import qj.ccmtjf.com.lib.LoanApp;
import qj.ccmtjf.com.lib.api.LoanService;
import retrofit2.Retrofit;

/**
 * Created by tongqinyuan on 2017/12/2.
 */
@Singleton
@Component(modules = {HttpModule.class, CommonModule.class})
public interface AppComponent {


    void inject(LoanApp app);
    LoanApp getContext();

    HttpDataSourceImpl getSourceImpl();

    Retrofit getRetrofit();

    LoanService getLoanService();

}
