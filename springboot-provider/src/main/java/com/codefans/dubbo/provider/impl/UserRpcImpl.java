package com.codefans.dubbo.provider.impl;

import com.codefans.rpc.UserRpc;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @Author: codefans
 * @Date: 2022-07-30 18:06
 */
@DubboService
public class UserRpcImpl implements UserRpc {

    private Logger log = LoggerFactory.getLogger(UserRpcImpl.class);
    @Override
    public String getUserName(long id) {
        log.info("UserRpcImpl.getUserName(), id={}, time={}", id, new Date());
        return "hello user[" + id + "]";
    }
}
