package com.bignrdranch.android.criminalintnt;

import androidx.lifecycle.ViewModel

public class CrimeListViewModel : ViewModel(){
    val crimes = mutableListOf<Crime>() // Array List 생성, 저장 요소값 변경은 mutableList

    init {      // 테스트 값 cirmes 에 100개의 값을 집어넣음
        for(i in 0 until 100) {
            val crime = Crime()
            crime.title = "Crime #$i"
            crime.isSolved = i % 2 == 0
            crimes += crime
        }
    }
}
