package com.bignrdranch.android.criminalintnt

import android.content.Context

// 싱글톤으로 생성, 앱이 실행되는 동안 하나의 인스턴스만 생성된다.
// 앱이 메모리에 있는 한 계속 존재하므로 액티비티나 프래그먼트 생명주기 상태가 변경되어도 계속 유지될 수 있음

class CrimeRepository private constructor(context: Context){

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