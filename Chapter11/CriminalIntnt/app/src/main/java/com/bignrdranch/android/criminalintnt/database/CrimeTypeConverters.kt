package com.bignrdranch.android.criminalintnt.database

import androidx.room.TypeConverters
import java.util.Date
import java.util.UUID

class CrimeTypeConverters {
    // 각 타입에 대해서 TypeConverters 이노테이션 2개가 필요함
    // 데이터베이스에 데이터를 저장하기 위해 타입을 변환하는 함수
    // 데이터베이스로부터 읽은 데이터를 우리가 원하는 타입으로 변환하는 함수
    // 처음 두개 함수는 Date 타입 변환, 그다음 두개 함수는 UUID 타입 변환을 처리한다.

    @TypeConverters
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverters
    fun toDate(millisSinceEpoch: Long?): Date? {
        return millisSinceEpoch?.let {
            Date(it)
        }
    }

    @TypeConverters
    fun toUUID(uuid: String?): UUID {
        return UUID.fromString(uuid)
    }

    @TypeConverters
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
}