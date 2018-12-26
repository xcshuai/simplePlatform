package com.revert.platform.user.mapper;

import com.revert.platform.common.annotation.MasterMySqlDao;
import com.revert.platform.common.base.mapper.BaseMapper;
import com.revert.platform.user.model.UserModel;

@MasterMySqlDao
public interface UserMapper extends BaseMapper<UserModel> {

}