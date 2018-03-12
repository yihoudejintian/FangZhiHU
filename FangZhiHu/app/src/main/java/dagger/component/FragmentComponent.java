package dagger.component;

import dagger.Component;
import dagger.scope.FragmentScope;

/**
 * Created by tongqinyuan on 2018/3/8.
 */
@FragmentScope
@Component(dependencies = AppComponent.class)
public interface FragmentComponent {


}
