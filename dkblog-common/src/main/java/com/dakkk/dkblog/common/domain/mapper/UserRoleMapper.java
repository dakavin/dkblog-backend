package com.dakkk.dkblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dakkk.dkblog.common.domain.dos.UserRoleDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author mikey
* @description 针对表【t_user_role(用户角色表)】的数据库操作Mapper
* @createDate 2024-04-24 20:55:08
* @Entity com.dakkk.dkblog.common.domain.dos.UserRoleDO
*/
public interface UserRoleMapper extends BaseMapper<UserRoleDO> {
    /**
     * 根据用户名查询
     */
    default List<UserRoleDO> selectByUsername(String username){
        LambdaQueryWrapper<UserRoleDO> lqw = new LambdaQueryWrapper<>();
        lqw.eq(UserRoleDO::getUsername,username);
        return selectList(lqw);
    }
}




