package com.test.health.ui.adapter;

import android.support.v4.app.Fragment;

import com.test.health.fragmentNavigator.FragmentNavigatorAdapter;
import com.test.health.ui.fragment.FirstFragment;
import com.test.health.ui.fragment.HospitalFragment;
import com.test.health.ui.fragment.HotFragment;
import com.test.health.ui.fragment.MineFragment;
import com.test.health.ui.fragment.ReportFragment;


public class MainFragmentAdapter implements FragmentNavigatorAdapter {
    /**
     * 第一次根据传入的位置建立新的Fragment
     *
     * @param position
     * @return
     */
    @Override
    public Fragment onCreateFragment(int position) {
        switch (position) {
            case 0:
                return FirstFragment.newInstance();
            case 1:
                return HospitalFragment.newInstance();
            case 2:
                return HotFragment.newInstance();
            case 3:
                return ReportFragment.newInstance();
            case 4:
                return MineFragment.newInstance();
        }
        return FirstFragment.newInstance();
    }

    /**
     * 根据传入的位置获得对应的类名
     *
     * @param position
     * @return
     */
    @Override
    public String getTag(int position) {
        switch (position) {
            case 0:
                return FirstFragment.TAG;
            case 1:
                return HospitalFragment.TAG;
            case 2:
                return HotFragment.TAG;
            case 3:
                return ReportFragment.TAG;
            case 4:
                return MineFragment.TAG;
        }
        return FirstFragment.TAG;
    }

    /**
     * 返回Fragment总数
     *
     * @return
     */
    @Override
    public int getCount() {
        return 5;
    }
}