package com.bignrdranch.android.criminalintnt

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment

class CrimeFragment: Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

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
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        dateButton.apply {
            text = crime.title.toString()
            isEnabled = false
        }
        /* 속성을 연속으로 설정할 수 있는 함수 apply
            dateButton.setText(crime.title.toString());
            dateButton.setEnabled(false); 와 같음*/

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

        solvedCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                crime.isSolved = isChecked
            }
        }
    }
}
