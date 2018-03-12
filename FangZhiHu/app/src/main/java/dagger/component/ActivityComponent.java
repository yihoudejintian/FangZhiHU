package dagger.component;

import com.tongqinyuan.fangzhihu.MainActivity;

import dagger.Component;
import dagger.module.ActivityModule;
import dagger.scope.ActivityScope;

/**
 * Created by tongqinyuan on 2018/3/8.
 */
@ActivityScope
@Component(dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(MainActivity activity);

}
