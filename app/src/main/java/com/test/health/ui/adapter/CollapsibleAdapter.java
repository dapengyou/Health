package com.test.health.ui.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.test.health.R;
import com.test.health.bean.HospitalAnalysisBean;
import com.test.health.bean.ValueBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lady_zhou on 2018/5/7.
 */

public class CollapsibleAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {
    public static final int TYPE_HEAD = 0;
    public static final int TYPE_BODY = 1;
    private List<HospitalAnalysisBean> mValueBeans;
    private HospitalAnalysisBean hospitalAnalysisBean;
    private List<ValueBean> serverList = new ArrayList<>();//服务
    private ArrayList<ValueBean> dayList = new ArrayList<>();//营业日
    private ArrayList<ValueBean> facilityList = new ArrayList<>();//服务设备
    private int position = -1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CollapsibleAdapter(List<MultiItemEntity> data, List<HospitalAnalysisBean> beans) {
        super(data);
        addItemType(TYPE_HEAD, R.layout.item_title);//头标题
        addItemType(TYPE_BODY, R.layout.item_recycle);//内容显示
        this.mValueBeans = beans;
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case TYPE_HEAD:
               final HospitalAnalysisBean hospitalAnalysisBean = (HospitalAnalysisBean) item;

                helper.setText(R.id.tv_name, hospitalAnalysisBean.getKey());
                helper.setImageResource(R.id.iv_image, hospitalAnalysisBean.isExpanded() ? R.mipmap.dive_up : R.mipmap.dive_down);
                helper.setVisible(R.id.iv_image, true);

                helper.addOnClickListener(R.id.iv_image);

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        position = pos;
                        //判断是否伸缩
                        if (hospitalAnalysisBean.isExpanded()) {
                            collapse(pos, false);
                        } else {
                            expand(pos, false);
                        }
                    }
                });
                break;
            case TYPE_BODY:
                helper.setNestView(R.id.item_recycle); // u can set nestview id

                final RecyclerView recyclerView = helper.getView(R.id.rv_list);
                recyclerView.setLayoutManager(new GridLayoutManager(helper.getConvertView().getContext(), 3));
                recyclerView.setHasFixedSize(true);

//                int space = mContext.getResources().getDimensionPixelSize(R.dimen.dp_10);
//                recyclerView.addItemDecoration(new SpaceItemDecoration(space, 3));
                for (int i = 0; i < mValueBeans.size(); i++) {
                    if (i == position) {
                        NestAdapter nestAdapter = new NestAdapter(mValueBeans.get(position).getValueBean());
                        nestAdapter.setOnItemChildClickListener(this);
                        nestAdapter.setOnItemClickListener(this);
                        recyclerView.setAdapter(nestAdapter);
                    }
                }

                break;
        }
    }


    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

        return false;
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Toast.makeText(mContext, "嵌套RecycleView item 收到: " + "点击了第 " + position + " 一次", Toast.LENGTH_SHORT).show();

    }
}
