package com.bignrdranch.android.criminalintnt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.UUID

class CrimeDetailViewModel(): ViewModel() {
    private val crimeRepository = CrimeRepository.get()
    private val crimeIdLiveData = MutableLiveData<UUID>()

    var crimeLiveData: LiveData<Crime?> =
        Transformations.switchMap(crimeIdLiveData) { crimeId -> //Transformations.switchMap는 LiveData를 반환함
            crimeRepository.getCrime(crimeId)
        }
    // crimeIdLiveData가 변경될 때마다 새로운 LiveData를 생성하고 crimeId를 가져옴
    fun loadCrime(crimeId: UUID) {
        crimeIdLiveData.value = crimeId
    }

    fun saveCrime(crime: Crime) {
        crimeRepository.updateCrime(crime)
    }
}