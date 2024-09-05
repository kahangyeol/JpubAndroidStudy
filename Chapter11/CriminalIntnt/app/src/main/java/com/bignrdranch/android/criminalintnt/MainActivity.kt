
package com.bignrdranch.android.criminalintnt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.UUID

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        //Fragment Manager 로부터 CrimeFragment 를 가져오려면 컨테이너 뷰 id로 Manager에 요청 해야함.

        if(currentFragment == null) {
            val fragment = /*CrimeFragment()*/ CrimeListFragment.newInstance()
            supportFragmentManager      // 프래그먼트 트랜잭션을 생성하고 commit 함
                .beginTransaction()
                .add(R.id.fragment_container, fragment) // 컨테이너뷰 에 val fragment 를 add
                .commit()
        }
    }

    override fun onCrimeSelected(crimeId: UUID) {
        val fragment = CrimeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}