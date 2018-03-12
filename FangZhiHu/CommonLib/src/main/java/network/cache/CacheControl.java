package network.cache;



import java.io.Serializable;

import qj.ccmtjf.com.lib.api.ApiResponse;


/**
 * <pre>
 * 缓存控制接口
 * Created by zhuyuanbao on 2016/12/6.
 * Copyright (c) 2016 www.zhengshijr.com. All rights reserved.
 * </pre>
 */
public interface CacheControl {
    /**
     * 缓存失效
     *
     * @return
     */
    boolean isExpired();

    /**
     * @return 缓存key
     */
    String getCacheKey();

    /**
     * 是否有效缓存key
     *
     * @param key
     * @return
     */
    boolean isValidCacheKey(String key);

    /**
     * 开始读取缓存
     *
     * @param key
     * @param apiResponse
     * @return 是否开启读取缓存任务
     */
    boolean startReadCache(String key, ApiResponse apiResponse);

    /**
     * 开始保存缓存
     *
     * @param key
     * @param entity
     */
    void startSaveCache(String key, Serializable entity);

    /**
     * 读取缓存成功
     *
     * @param key
     * @param entity
     * @param apiResponse
     */
    void readedCache(String key, Serializable entity, ApiResponse apiResponse);

}
