package com.ultivic.savenotes.domain.model

import java.time.LocalDateTime

data class AlarmItem(val alarmTime : LocalDateTime,
                     val message : String)
