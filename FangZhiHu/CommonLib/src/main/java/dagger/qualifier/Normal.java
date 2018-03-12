package dagger.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by tongqinyuan on 2017/12/2.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Normal {
}
