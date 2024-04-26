package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
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

    /**
     * 修改用户密码
     */
    default int updatePasswordByUsernameInt(String username,String password){
        LambdaUpdateWrapper<UserDO> lqw = new LambdaUpdateWrapper<>();
        // 设置需要更新的字段
        lqw.set(UserDO::getUsername,username);
        lqw.set(UserDO::getPassword,password);
        // 更新条件
        lqw.eq(UserDO::getUsername,username);

        return update(null,lqw);
    }
}




