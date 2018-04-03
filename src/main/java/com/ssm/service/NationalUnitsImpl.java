package com.ssm.service;

import com.ssm.Dao.INationalDao;
import com.ssm.Dao.NationalDaompl;
import com.ssm.model.NationalUnits;

public class NationalUnitsImpl implements INationalUnits {

    private INationalDao unitsDao;

    public NationalUnitsImpl(){
        //unitsDao=new NationalDaompl();
    }

    public void addNationalUnits(NationalUnits units) {
       // unitsDao.addNationalUnits(units);
        //unitsDao.addModel(units);
    }
}
