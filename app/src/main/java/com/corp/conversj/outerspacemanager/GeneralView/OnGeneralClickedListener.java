package com.corp.conversj.outerspacemanager.GeneralView;

import com.corp.conversj.outerspacemanager.DB.Attack;
import com.corp.conversj.outerspacemanager.Model.Report;

/**
 * Created by mac15 on 20/03/2017.
 */

public interface OnGeneralClickedListener {
    void onAttackClicked(Attack attack);
    void onReportClicked(Report report);
}
