package com.example.util;

import com.example.util.impl.BusinessDayUtilImpl;
import com.example.util.impl.BusinessHourUtilImpl;
import com.example.util.impl.NormalDayUtilImpl;
import com.example.util.impl.NormalHourUtilImpl;
import com.example.util.spec.BusinessDayUtil;
import com.example.util.spec.BusinessHourUtil;
import com.example.util.spec.NormalDayUtil;
import com.example.util.spec.NormalHourUtil;

public class BizScheduleClient {

    public static BusinessDayUtil getBusinessDayUtil(){
        return new BusinessDayUtilImpl();
    }

    public static BusinessHourUtil getBusinessHourUtil(){
        return new BusinessHourUtilImpl();
    }

    public static NormalDayUtil getNormalDayUtil(){
        return new NormalDayUtilImpl();
    }

    public static NormalHourUtil getNormalHourUtil(){
        return new NormalHourUtilImpl();
    }
}
