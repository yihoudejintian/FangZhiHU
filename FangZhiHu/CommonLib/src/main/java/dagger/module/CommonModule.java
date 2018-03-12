package dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import qj.ccmtjf.com.lib.HttpDataSource;
import qj.ccmtjf.com.lib.HttpDataSourceImpl;
import qj.ccmtjf.com.lib.LoanApp;

/**
 * Created by tongqinyuan on 2017/12/2.
 */
@Module
public class CommonModule {

    private final LoanApp mApp;

    public CommonModule(LoanApp app) {
        this.mApp = app;
    }

    @Singleton
    @Provides
    LoanApp provideLoanApp() {
        return mApp;
    }

    @Singleton
    @Provides
    HttpDataSource provideHttpDataSource(HttpDataSourceImpl httpDataSource) {
        return httpDataSource;
    }


}
