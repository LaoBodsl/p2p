package com.p2p.base.mapper;

import com.p2p.base.domain.SystemDictionary;
import java.util.List;

public interface SystemDictionaryMapper {

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    int updateByPrimaryKey(SystemDictionary record);
}