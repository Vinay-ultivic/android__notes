package com.ultivic.savenotes.domain.repository

import com.ultivic.savenotes.domain.model.AlarmItem

interface AlarmSchedulerRepository {
    fun schedule(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}