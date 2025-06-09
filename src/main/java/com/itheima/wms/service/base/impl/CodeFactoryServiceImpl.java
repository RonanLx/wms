package com.itheima.wms.service.base.impl;

import com.itheima.wms.mapper.base.CodeFactoryMapper;
import com.itheima.wms.model.entity.base.CodeFactory;
import com.itheima.wms.service.base.CodeFactoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CodeFactoryServiceImpl implements CodeFactoryService {

    @Autowired
    private CodeFactoryMapper codeFactoryMapper;

    @Override
    @Transactional
    public String getNextCodeByType(String type) {
        CodeFactory entity = codeFactoryMapper.getByType(type);
        if (entity == null) {
            entity = new CodeFactory();
            entity.setType(type);
            entity.setRemark(type);
            entity.setContent(String.valueOf(10001));
            codeFactoryMapper.save(entity);
        } else {
            entity.setContent(String.valueOf(Integer.parseInt(entity.getContent()) + 1));
            codeFactoryMapper.updateById(entity);
        }
        return entity.getType() + entity.getContent();
    }
}
