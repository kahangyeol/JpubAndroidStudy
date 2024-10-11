package com.bignrdranch.android.criminalintnt

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.text.Editable
import android.text.TextWatcher
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.util.Date
import java.util.UUID

private const val TAG = "CrimeFragment"
private const val ARG_CRIME_ID = "crime_id"
private const val DIALOG_DATE = "DialogDate"
private const val REQUEST_DATE = 0
private const val REQUEST_CONTACT = 1
private const val DATE_FORMAT = "yyyy년 M월 d일 H시 m분, E요일"

class CrimeFragment: Fragment(), DatePickerFragment.Callbacks {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox
    private lateinit var reportButton: Button
    private lateinit var suspectButton: Button
    private val crimeDetailViewModel: CrimeDetailViewModel by lazy {
        ViewModelProvider(this).get(CrimeDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {    // onCreate 에서 프래그먼트의 뷰를 인플레이트 하지 않는다
        super.onCreate(savedInstanceState)
        crime = Crime()
        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        crimeDetailViewModel.loadCrime(crimeId)
        //TODO: getSerializable Deprecated 됐으니까 Parcelable 사용해야됨
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
        reportButton = view.findViewById(R.id.crime_report) as Button
        suspectButton = view.findViewById(R.id.crime_suspect) as Button

        /* 속성을 연속으로 설정할 수 있는 함수 apply
            dateButton.setText(crime.title.toString());
            dateButton.setEnabled(false); 와 같음*/

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeDetailViewModel.crimeLiveData.observe(
            viewLifecycleOwner,
            Observer {
                crime?.let {
                    this.crime = crime
                    updateUI()
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(
                sequence: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
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

        dateButton.setOnClickListener {
            DatePickerFragment.newInstance(crime.date).apply {
                setTargetFragment(this@CrimeFragment, REQUEST_DATE)
                show(this@CrimeFragment.getParentFragmentManager(), DIALOG_DATE)
            }
        }

        reportButton.setOnClickListener {
            Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, getCrimeReport())
                putExtra(Intent.EXTRA_SUBJECT,
                    getString(R.string.crime_report_subject))
            }.also { intent ->
                /*startActivity(intent)*/   // 응답할 앱이 없다면 액티비티 선택기가 뜸 (chooser)
                // 아래에서 뿅 뜨면서 사용할 애플리케이션 뜨는거임
                val chooserIntent = Intent.createChooser(intent, getString(R.string.send_report))
                startActivity(chooserIntent)
            }
        }

        suspectButton.apply {
            val pickerContactIntent = Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI)

            setOnClickListener{
                //Todo: startActivityForResult 대신 Activity Result API 쓰기 (deprecated 됐음)
                startActivityForResult(pickerContactIntent, REQUEST_CONTACT)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        crimeDetailViewModel.saveCrime(crime)
        // 프래그먼트가 중단 상태가 되면 호출됨(프래그먼트 화면 전체가 안보일때)
        // 여기서는 상세내역 화면을 떠나거나(백버튼을 눌러서) 작업을 전환하면 데이터가 저장된다.
        // 여기서 하면 메모리 부족으로 안드로이드가 프로세스를 종료해도 데이터각 유실이 되지 않는다 개꿀 따라시
    }

    private fun updateUI() {
        titleField.setText(crime.title)
        dateButton.text = crime.date.toString()
//        solvedCheckBox.isChecked = crime.isSolved
        solvedCheckBox.apply {
            isChecked = crime.isSolved
            jumpDrawablesToCurrentState() // 애니메이션 생략
        }

        if(crime.suspect.isNotEmpty()){
            suspectButton.text = crime.suspect
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when{
            resultCode != Activity.RESULT_OK -> return      // 결과가 성공이 아니면 종료

            requestCode == REQUEST_CONTACT && data != null -> {
                val contactUri: Uri = data.data ?: return   // ?: 는 null 이면 return 아니면 data.data 가 contactUri에 들어감
                val queryFields = arrayOf(ContactsContract.Contacts.DISPLAY_NAME)
                val cursor = requireActivity().contentResolver.query(contactUri, queryFields, null, null, null)
                cursor?.use {
                    if(it.count == 0)
                        return

                    it.moveToFirst()
                    val suspect = it.getString(0)
                    crime.suspect = suspect
                    crimeDetailViewModel.saveCrime(crime)
                    suspectButton.text = suspect
                }
            }
        }   //TODO: 여기까지 했음 해석이나 하셈
    }

    private fun getCrimeReport(): String{
        val solvedString = if (crime.isSolved) {
            getString(R.string.crime_report_unsolved)
        } else {
            getString(R.string.crime_report_solved)
        }

        val dateString = DateFormat.format(DATE_FORMAT, crime.date).toString()
        var suspect = if(crime.suspect.isBlank()){
            getString(R.string.crime_report_no_suspect)
        } else {
            getString(R.string.crime_report_suspect, crime.suspect)
        }

        return getString(R.string.crime_report, crime.title, dateString, solvedString, suspect)
    }

    companion object {
        fun newInstance(crimeId: UUID): CrimeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }

    override fun onDateSelected(date: Date) {
        crime.date = date
        updateUI()
    }
}
