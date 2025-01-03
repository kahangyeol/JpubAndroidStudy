package com.bignrdranch.android.criminalintnt

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.bignrdranch.android.criminalintnt.database.CrimeDatabase
import com.bignrdranch.android.criminalintnt.database.migration_1_2
import java.io.File
import java.util.UUID
import java.util.concurrent.Executors

// 싱글톤으로 생성, 앱이 실행되는 동안 하나의 인스턴스만 생성된다.
// 앱이 메모리에 있는 한 계속 존재하므로 액티비티나 프래그먼트 생명주기 상태가 변경되어도 계속 유지될 수 있음

private const val DATABASE_NAME = "crime-database"

class CrimeRepository private constructor(context: Context) {

    private val database : CrimeDatabase = Room.databaseBuilder(    //CrimeDatabase의 실체 클래스 생성
        context.applicationContext,     // 데이터베이스의 Context, 여기서는 CriminalApplication의 콘텍스트 객체를 전달
        CrimeDatabase::class.java,      // Room으로 생성하고자 하는 데이터베이스 클래스
        DATABASE_NAME                   // Room으로 생성하고자 하는 데이터베이스 파일 이름
    ).addMigrations(migration_1_2).build()

    private val crimeDao = database.crimeDao()      // DAO의 엑세스된 함수를 사용하기 위함
    private val executor = Executors.newSingleThreadExecutor()
    private val filesDir = context.applicationContext.filesDir

    fun getCrimes() : LiveData<List<Crime>> = crimeDao.getCrimes()
    fun getCrime(id: UUID) : LiveData<Crime?> = crimeDao.getCrime(id)

    fun updateCrime(crime: Crime) {
        executor.execute {  // 백그라운드 스레드를 사용해 코드를 실행한다.
            crimeDao.updateCrime(crime)
        }
    }

    fun addCrime(crime: Crime){
        executor.execute {
            crimeDao.addCrime(crime)
        }
    }

    fun getPhotoFile(crime: Crime): File = File(filesDir, crime.photoFileName)

    companion object {
        private var INSTANCE: CrimeRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = CrimeRepository(context)
            }
        }

        fun get(): CrimeRepository {
            return INSTANCE ?:
            throw IllegalStateException("CrimeRepository must be initialized")
        }
    }

}