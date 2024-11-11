package com.bignrdranch.android.criminalintnt

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
// Crime 은 테이블이 되고 각 열은 id, title, date, isSolved 이 된다.
data class Crime(@PrimaryKey val id: UUID = UUID.randomUUID(),  // 임의의 UUID(128bit 고유값) 값을 갖는 고유 ID 생성
                 var title: String = "",
                 var date: Date = Date(),           // Date 기본 생성자 호출 = 현재 일자로 초기화.
                 var isSolved: Boolean = false,     // 생성 시 문제가 해결 되지 않았으므로 false 로 시작 (문제 해결 유무)
                 var suspect: String = "") {
    val photoFileName get() =  "IMG_$id.jpg"
}
