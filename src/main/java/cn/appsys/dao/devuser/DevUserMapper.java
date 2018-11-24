package cn.appsys.dao.devuser;

import cn.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserMapper {
    /**
     * 通过userCode获取User
     *
     * @param
     * @return
     * @throws Exception
     */
    public DevUser getLoginUser(@Param("devCode") String devCode) throws Exception;
}
