package com.bignrdranch.android.criminalintnt;

import androidx.lifecycle.ViewModel

public class CrimeListViewModel : ViewModel(){
    private val crimeRepository = CrimeRepository.get()
    val crimeListLiveData = crimeRepository.getCrimes()
}
