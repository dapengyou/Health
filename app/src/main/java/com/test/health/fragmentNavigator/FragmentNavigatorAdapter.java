package com.test.health.fragmentNavigator;

import android.support.v4.app.Fragment;

/**
 * Created by Robin on 2017/3/30.
 */

public interface FragmentNavigatorAdapter {

    Fragment onCreateFragment(int position);

    String getTag(int position);

    int getCount();
}
