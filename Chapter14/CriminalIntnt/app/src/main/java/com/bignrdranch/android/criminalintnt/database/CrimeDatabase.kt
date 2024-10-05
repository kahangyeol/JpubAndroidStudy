package com.bignrdranch.android.criminalintnt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bignrdranch.android.criminalintnt.Crime

@Database(entities = [Crime::class], version = 1)   // 이 클래스가 이 앱의 데이터베이스임을 Room에게 알려줌
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}