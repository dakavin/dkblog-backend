package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dakkk.dkblog.common.domain.dos.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author mikey
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2024-04-22 15:50:22
* @Entity com.dakkk.dkblog.common.domain.dos.UserDO
*/
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 根据用户名获取用户
     */
    default UserDO findByUsernameUserDo(String username){
        LambdaQueryWrapper<UserDO> lqw = new LambdaQueryWrapper();
        lqw.eq(UserDO::getUsername,username);
        return selectOne(lqw);
    }
}




