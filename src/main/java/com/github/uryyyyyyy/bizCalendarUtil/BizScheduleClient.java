package com.github.uryyyyyyy.bizCalendarUtil;

import com.github.uryyyyyyy.bizCalendarUtil.impl.BusinessDayUtilImpl;
import com.github.uryyyyyyy.bizCalendarUtil.impl.BusinessHourUtilImpl;
import com.github.uryyyyyyy.bizCalendarUtil.impl.NormalDayUtilImpl;
import com.github.uryyyyyyy.bizCalendarUtil.impl.NormalHourUtilImpl;
import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessDayUtil;
import com.github.uryyyyyyy.bizCalendarUtil.spec.BusinessHourUtil;
import com.github.uryyyyyyy.bizCalendarUtil.spec.NormalDayUtil;
import com.github.uryyyyyyy.bizCalendarUtil.spec.NormalHourUtil;

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
