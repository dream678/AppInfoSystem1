package cn.appsys.dao.appcategory;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AppCategoryMapper {

    public List<AppCategory> getAppCategoryListByParentId(@Param("parentId") Integer parentId) throws Exception;
}
