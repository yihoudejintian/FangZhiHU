package com.tongqinyuan.fangzhihu;


/**
 * This specifies the contract between the view and the presenter.
 * Created by liyanxi on 11/14/17.
 * Copyright (c) 2017 www.zhengshijr.com. All rights reserved.
 */

public interface HomeContact {

    interface View {
        void requestDataSuccess(Object homeDetailEntity);
    }

    interface Presenter  {
        void getHomeData();

    }
}
