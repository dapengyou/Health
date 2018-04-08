package com.test.health.MockData;

import android.widget.SimpleCursorTreeAdapter;

import com.test.health.bean.FirstAdvertisingBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by lady_zhou on 2018/1/22.
 */

public class MockData {
    public static final String[] titles = {"听肺癌患者家属倾述:人工智能如何守护爱与健康", "关于“体检报告”，这大概是最科学的一篇文章了",
            "没有时间来管理健康？健康对于企业家成了奢侈品么？"};
    public static final String[] pics = {"http://pic122.nipic.com/file/20170219/5108620_131659075759_2.jpg", "http://pic125.nipic.com/file/20170401/24978725_164736285000_2.jpg", "http://pic126.nipic.com/file/20170404/9054345_112106862000_2.jpg", "http://pic125.nipic.com/file/20170401/24978725_162029935000_2.jpg", "http://pic125.nipic.com/file/20170401/24978725_162029889000_2.jpg", "http://pic125.nipic.com/file/20170401/24978725_163256459000_2.jpg", "http://pic125.nipic.com/file/20170401/18648048_102324880038_2.jpg", "http://pic125.nipic.com/file/20170328/24978725_161922155000_2.jpg", "http://pic125.nipic.com/file/20170328/24978725_162512485000_2.jpg", "http://pic125.nipic.com/file/20170328/24978725_170855369000_2.jpg"};

    private static Random random = new Random();

    /**
     * 随机标题
     *
     * @return
     */
    private static String getRndNick() {
        return titles[random.nextInt(titles.length)];
    }

    /**
     * 随机图片
     *
     * @return
     */
    private static String getRndPic() {
        return pics[random.nextInt(pics.length)];
    }

    /**
     * 随机过去时
     *
     * @return
     */
    private static long getRndTime() {
        return System.currentTimeMillis() - random.nextInt(10) * 24 * 60 * 60 * 1000;
    }

    public static List<FirstAdvertisingBean> getDatas(int count) {
        List<FirstAdvertisingBean> datas = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < count; i++) {
            FirstAdvertisingBean firstAdvertisingBean = new FirstAdvertisingBean();
            firstAdvertisingBean.setImageUrl(getRndPic());
            firstAdvertisingBean.setTitle(getRndNick());
            firstAdvertisingBean.setTime(sdf.format(getRndTime()));
            datas.add(firstAdvertisingBean);
        }
        return datas;
    }


}
