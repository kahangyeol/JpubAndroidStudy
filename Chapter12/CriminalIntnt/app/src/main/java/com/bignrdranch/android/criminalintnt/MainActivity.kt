
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
        val fragment = CrimeFragment.newInstance(crimeId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            /* 액티비티에 현재 호스팅된 프래그먼트를 두번째 인자로 전달된 프래그먼트로 교체하여 첫번째 인자로 전달된 프래그먼트 id를 가지게 된다.
               만일 기존 호스팅된 프래그먼트가 없으면 add로 호출했을때와 같게 새로운 프래그먼트가 추가된다*/
            .addToBackStack(null)   // 백버튼을 누르면 해당 트랜잭션이 취소됨. CrimeFragment -> CrimeListFragment 로 이동
            .commit()
    }
}