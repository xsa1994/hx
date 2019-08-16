package com.hx.mapper;

import com.hx.domain.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {

    int insertHxAccount(Account object);

    int updateHxAccount(Account object);

    Account selectByName(@Param("accountName") String accountName);

    Integer deleteByIds(@Param("ids") List<Long> ids);

    List<Account> pageQueryAll(@Param("start") int start, @Param("limit") int limit);

    Integer countAll();

    Account selectById(@Param("id") Long id);
}