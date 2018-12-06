package com.fc.test.mapper.auto;

import com.fc.test.model.auto.Tuser;

public interface TuserDao extends TuserMapper {

    Tuser isExistAccount(String accout);

}
