package com.github.uryyyyyyy.bizCalendarUtil;

import com.github.uryyyyyyy.bizCalendarUtil.impl.BusinessDayUtilImpl;
import com.github.uryyyyyyy.bizCalendarUtil.impl.BusinessHourUtilImpl;
import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessDayUtil;
import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessHourUtil;

public class BizScheduleClient {

    public static BusinessDayUtil getBusinessDayUtil(){
        return new BusinessDayUtilImpl();
    }

    public static BusinessHourUtil getBusinessHourUtil(){
        return new BusinessHourUtilImpl();
    }

    //ImplUtil.calcCron(cronPattern, from, to);
}
