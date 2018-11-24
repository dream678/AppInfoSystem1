package cn.appsys.dao.datadictionary;

import cn.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataDictionaryMapper {

    public List<DataDictionary> getDataDictionaryList(@Param("typeCode") String typeCode) throws Exception;
}
