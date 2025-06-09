package com.itheima.wms.mapper.base;

import com.itheima.wms.model.entity.base.CodeFactory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CodeFactoryMapper {

    @Select("select * from wms_code_factory where type=#{type}")
    CodeFactory getByType(String type);

    void updateById(CodeFactory entity);

    void save(CodeFactory entity);
}
