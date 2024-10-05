package com.bignrdranch.android.criminalintnt

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.UUID

private const val TAG = "CrimeListFragment"
class CrimeListFragment: Fragment() {


    interface Callbacks {
        fun onCrimeSelected(crimeId: UUID)
    }

    private var callbacks: Callbacks? = null

    private lateinit var crimeRecyclerView: RecyclerView
    private var adapter: CrimeAdapter? = CrimeAdapter(emptyList())

    private val crimeListViewModel: CrimeListViewModel by lazy {
        ViewModelProvider(this).get(CrimeListViewModel::class.java)
        /* ViewModelProvider(this): CrimeListViewModel 인스턴스와 연관된 ViewModelProvider 인스턴스를 생성하고 반환,
           get:                     CrimeListViewModel 인스턴스를 반환

           이것의 참조를 crimeListViewModel이 가지게됨 따라서 CrimeListFragment 가 뷰모델 CrimeListViewModel 와 연결됨*/
    }

    // Attach 는 프래그먼트가 액티비티에 붙을 때 호출됨
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 현재 액티비티의 context를 callbacks에 전달
        callbacks = context as Callbacks?
        // 전달된 context를 callbacks 속성에 지정할 때는 CrimeListFragment.Callbacks 타입으로 변환됨
        // CrimeListFragment를 호스팅하는 액티비티는 반드시CrimeListFragment.Callbacks 인터페이스를 구현해야함
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.new_crime -> {
                val crime = Crime()
                crimeListViewModel.addCrime(crime)
                callbacks?.onCrimeSelected(crime.id)
                true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_crime_lsit, container, false)

        crimeRecyclerView = view.findViewById(R.id.crime_recycler_view) as RecyclerView
        crimeRecyclerView.layoutManager = LinearLayoutManager(context)
        // LayoutManager 는 RecyclerView의 모든 항목들의 화면 위치를 처리하고 스크롤 동작도 정의
        crimeRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crimeListViewModel.crimeListLiveData.observe(
            viewLifecycleOwner,
            Observer { crimes ->
                crimes?.let {
                    Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(crimes)
                }
            })
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crim_list, menu)
    }

    private fun updateUI(crimes: List<Crime>) {
        adapter = CrimeAdapter(crimes)
        crimeRecyclerView.adapter = adapter
    }

    private inner class CrimeHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        // CrimeHoler 내부 클래스 생성 및 (view: View) 생성자로 상속한 클래스(슈퍼 클래스) ViewHolder 의 매개변수에 전달
        private lateinit var crime: Crime

        val titleTextView: TextView = itemView.findViewById(R.id.crime_title)
        val dateTextView: TextView = itemView.findViewById(R.id.crime_date)
        val solvedImageView: ImageView = itemView.findViewById(R.id.crime_solved)

        init{
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime){
            this.crime = crime
            titleTextView.text = this.crime.title
            dateTextView.text = this.crime.date.toString()
            solvedImageView.visibility = if(crime.isSolved) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        override fun onClick(view: View?) {
            callbacks?.onCrimeSelected(crime.id)
        }

    }
    private inner class CrimeAdapter(var crimes: List<Crime>) : RecyclerView.Adapter<CrimeHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val view = layoutInflater.inflate(R.layout.list_item_crime, parent, false)
            return CrimeHolder(view)
        }

        override fun getItemCount(): Int {
            return crimes.size
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = crimes[position]
            holder.bind(crime)
            // bind 함수는 계속 업데이트 시켜주는거임
            //CrimeHolder == ViewBindHolder 임
        }// onBindViewHolder는 간단하게 만들어야 전체적인 recyclerView가 매끄럽게 움직임
    }

    companion object {
        fun newInstance(): CrimeListFragment {  // 호출 시 CrimeListFragment의 인스턴스 생성
            return CrimeListFragment()
        }
    }
}
