package com.p2p.base.service.Impl;

import com.p2p.base.domain.Account;
import com.p2p.base.mapper.AccountMapper;
import com.p2p.base.service.IAcountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IAcountServiceImpl implements IAcountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void update(Account account) {
        int ret = this.accountMapper.updateByPrimaryKey(account);
        if (ret == 0) {
            throw new RuntimeException("乐观锁失败,Account+:"+account.getId());
        }
    }

    @Override
    public void add(Account account) {
        this.accountMapper.insert(account);
    }

    @Override
    public Account get(long id) {
        return this.accountMapper.selectByPrimaryKey(id);
    }

}
