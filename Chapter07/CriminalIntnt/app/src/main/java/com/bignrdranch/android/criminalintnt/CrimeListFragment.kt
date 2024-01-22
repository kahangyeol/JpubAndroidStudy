package com.bignrdranch.android.criminalintnt

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

private const val TAG = "CrimeListFragment"
class CrimeListFragment: Fragment() {

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
        /* ViewModelProvider(this): CrimeListViewModel 인스턴스와 연관된 ViewModelProvider 인스턴스를 생성하고 반환,
           get:                     CrimeListViewModel 인스턴스를 반환

           이것의 참조를 crimeListViewModel이 가지게됨 따라서 CrimeListFragment 가 뷰모델 CrimeListViewModel 와 연결됨*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
        // CrimeListViewModel 의 crimes 리스트에 저장되어 있는 count
    }

    companion object {
        fun newInstance(): CrimeListFragment {  // 호출 시 CrimeListFragment의 인스턴스 생성
            return CrimeListFragment()
        }
    }
}