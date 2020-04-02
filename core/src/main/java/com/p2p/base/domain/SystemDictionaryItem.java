package com.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemDictionaryItem extends BaseDomain {
    private long parentId;
    private String title;
    private int sequence;

}
