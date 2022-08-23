package com.codefans.springboot.service.impl;

import com.codefans.rpc.UserRpc;
import com.codefans.springboot.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * @Author: codefans
 * @Date: 2022-07-31 12:02
 */
@Service
public class UserServiceImpl implements UserService {

    @DubboReference
//    @Reference
    private UserRpc userRpc;

    @Override
    public String queryUser() {
        String userStr = "";
        String userRpcResult = userRpc.getUserName(1002L);
        userStr = "UserServiceImpl.queryUser," + userRpcResult;
        return userStr;
    }

}
