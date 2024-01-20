package com.bignrdranch.android.criminalintnt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class CrimeFragment: Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {    // onCreate 에서 프래그먼트의 뷰를 인플레이트 하지 않는다
        super.onCreate(savedInstanceState)
        crime = Crime()
    }

    override fun onCreateView(      // 뷰를 인플레이트 한다
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        // 2번째 인자는 뷰의 부모, 세번째 인자는 인프레이트된 뷰를 이 뷰의 부모에게 즉시 추가할 것인지

        titleField = view.findViewById(R.id.crime_title) as EditText

        return view
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = sequence.toString()    //Crime의 제목을 설정
            }

            override fun afterTextChanged(sequence: Editable?) {
                TODO("Not yet implemented")
            }
        }

        titleField.addTextChangedListener(titleWatcher)
    }
}
