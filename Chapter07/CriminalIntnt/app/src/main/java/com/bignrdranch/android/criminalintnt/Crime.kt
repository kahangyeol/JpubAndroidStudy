package com.bignrdranch.android.criminalintnt

import java.util.*

data class Crime(val id: UUID = UUID.randomUUID(),  // 임의의 UUID(128bit 고유값) 값을 갖는 고유 ID 생성
                 var title: String = "",
                 var date: Date = Date(),           // Date 기본 생성자 호출 = 현재 일자로 초기화.
                 var isSolved: Boolean = false)     // 생성 시 문제가 해결 되지 않았으므로 false 로 시작
