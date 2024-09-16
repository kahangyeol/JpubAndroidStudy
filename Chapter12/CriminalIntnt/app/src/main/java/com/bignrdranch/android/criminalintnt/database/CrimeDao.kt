package com.bignrdranch.android.criminalintnt.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.bignrdranch.android.criminalintnt.Crime
import java.util.UUID

@Dao
interface CrimeDao {

    @Query("SELECT * FROM crime")
    fun getCrimes(): LiveData<List<Crime>>

    @Query("SELECT * FROM crime WHERE id=(:id)")
    fun getCrime(id: UUID): LiveData<Crime?>
    // ? = @Nullable 을 뜻함 즉 없으면 null을 반환시켜줌 null도 반환 안시키면 nullPointException 뜰수도 있음
}