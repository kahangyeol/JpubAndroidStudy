# 기본 지식/ETC

## 안드로이드 생명주기 (Life Cycle) 65P
 **존재하지 않음(none)**  
   
onCreate()   onDestroy()  
  
  **중단(stopped)**  
    
onStart()   onStop()  
  
 **일시 중지(paused)**  
  
onResume()   onPause()  
  
 **실행 재개(resumed)**  

## 리소스를 코드에서 사용하려면 리소스 ID가 필요 20P
지정한 리소스 ID는 앱 빌드 할 때마다 안드로이드 빌드도구가 R.class에 자동으로 생성한다  
ex)R.layout.main_activity, R.string.app_name    -> R클래스의 내부 클래스인 layout 안에 정수형 상수로 되어있음 (ResId = Int형)  

## 가로방향 레이웃 생성하기 76P  
Android Resource File 선택 후 orientation >> 선택 후 landspace  
루트요소 필드에 Frame Layout  
  
## isFinish 88P  
isFinish = true  -> 사용자가 액티비티를 끝냄(백버튼, 오버뷰에서 삭제)  
         = flase -> 화면 회전에 따른 구성변경으로 시스템이 현재 액티비티 인스턴스를 소멸시킴  
  
## Bundle 96P  
사용예시) onCreate(savedInstanceState: **Bundle**)  
문자열 키와 쌍을 이루는 값을 가지는 구조체  
"key",value  

## SIS(Saved Instance State) 저장된 인스턴스 상태 96P
액티비티가 소멸될 때 UI상태를 보존해 액티비티 재구성에 사용  
Android OS 가 액티비티 외부에 저장하는 데이터  
Activity.onSavedInstanceState(Bundle)을 오버라이드해 사용가능  
액티비티 중단 상태 시 Activity.onSavedInstanceState(Bundle)호출  

## Data class 42P
주로 데이터를 갖는 클래스  
업무에 관련된것, 프로그램에서 필요해서 생성한것, 등등  
비지니스 로직 처리 함수보다 주로 데이터를 저장하는 속성을 갖는다  

클래스 인스턴스 끼리 속성의 값을 비교하거나(equals() 함수),  
인스턴스를 컬렉션(ex. HashMap)에 저장할 때 사용할 키 값(Hash code) 을 생성하는 기능,  
속성값을 문자열로 출력하는 toString() 함수 기능이 필요하여 **코틀린에서는 Data class 라는개념을 만들었음**  
  
data 키워드를 지정하면 위의 기능들을 처리해주는 함수들을 해당 클래스에 맞게 코틀린 컴파일러가 자동으로 생성해준다.  

## Intent.putExtra 137P
key, value 값을 가지며 Intent.putExtra(name: String, value: 자료형)  
intent.get'value 자료형'Extra(name:String, value: 자료형)  
ex) intent.getBooleanExtra(name:String, value: Boolean)  
  
## object
java의 new static 키워드를 대신함, 클래스 정보 적재와 객체선언이 동시에 이루어진다  
클래스 A를 정의함과 동시에 해당 인스텐스 생성 예제코드)  
object A{  
  var baseURL: String = "www.naver.com"  
  fun printBaseURL {  
     println("baseURL is $baseURL)  
  {  
}  
객체 내 static{} 에서 new 를 이용한 객체 생성과 변수할당이 이루어짐  

## companion object
new 를 이용한 객체 생성, 변수할당이 객체의 외부, 클래스의 내부에서 진행  

## startActivityForResult 141P
startActivityForResult(Intent, Int)  
두번째 매개변수는 요청코드로 사용자가 정의한 정수다  
요청코드는 자식 액티비티에 전달되었다가 부모 액티비티가 다시 돌려받으며  
부모 액티비티가 여러 타입의 자식 액티비티들을 시작시킬때  
어떤 자식 액티비티가 결과를 돌려주는 것인지 알고자 할 때도 사용  

## setResult 142P
setResult(resultCode: Int)  
setResult(resultCode: Int, data: Intent)  
결과코드(ResultCode)는 사전 정의된 두개의 상수, 즉  
Activity.RESULT_OK(정수 -1), Activity.RESULT_CANCELED(정수 0) 중 하나다  
RESULT_FIRST_USER(정수 1) 상수도 사용할 수 있다.  

## UUID
UUID(Universally Unique Identifier, 128bit 고유한 값)    
안드로이드 프레임워크에 포함된 유틸리티 클래스 고유한 ID 값을 쉽게 생성하는 방법을 제공  

## RecyclerView
ReyclerView가 생성된 후에는 바로 LayoutManager를 설정 해야하며   
RecyclerView는 항목들을 화면에 위치시키는 일을 직접 하지 않고 LayoutManager가 한다   
따라서 LayoutManager 가 항목들을 화면에 위치시키고 스크롤 동작도 정의함  

## ViewHolder 206P
RecyclerView는 항목 View가 ViewHolder 인스턴스에 포함되어 있다고 간주함
ViewHolder는 항목 View 의 참조(때로는 항목의 특정 위젯)를 갖는다

## Adapter 209P
RecyclerView는 ViewHolder를 생성하지 않고 이 일을 어댑터에게 요청함
Adapter의 역할
- 필요한 ViewHodler 인스턴스 생성
- 모델계층의 데이터를 ViewHolder들과 바인딩
RecyclerView의 역할
- 새로운 ViewHolder 인스턴스 생성을 어댑터에게 요청
- 지정된 위치의 데이터 항목에 ViewHolder를 바인딩하도록 어댑터에게 요청한다.

### RecyclerView, Adpater, ViewHolder
RecyclerView (리사이클러뷰):  
- 목록 형태의 데이터를 효율적으로 표시하고 스크롤 할 수 있게 해주는 위젯입니다.
- 화면에 보이는 아이템만 로드하여 메모리를 효율적으로 관리하며 스크롤 성능을 향상시킵니다.  
  
ViewHolder (뷰홀더):  
- RecyclerView에서 각 아이템의 뷰들을 담당하는 객체입니다.  
- 재활용이 가능한 뷰들을 보유하고, 필요한 경우 새로운 데이터로 업데이트됩니다.  
- findViewById 등의 비싼 작업을 최소화하여 성능을 향상시킵니다.

onBindViewHolder :
RecyclerView에서 데이터와 ViewHolder를 결합하는 역할을 합니다.  
이 메서드는 RecyclerView가 데이터를 표시할 때 호출되며, 각 아이템의 데이터를 ViewHolder에 바인딩합니다.
1. 인자로 전달된 ViewHolder 객체를 사용하여 레이아웃의 뷰들을 참조합니다.  
2. 해당 포지션에 해당하는 데이터를 얻어옵니다.  
3. 뷰들에 데이터를 설정하거나, 이벤트 핸들러를 설정합니다.
4. ViewHolder가 가지고 있는 뷰를 업데이트합니다.
5. 각각의 아이템에 이벤트를 넣고싶을땐 onBindViewHolder에 넣어야 합니다.  
5.1. ViewHolder에서 init{} 블럭을 사용해 itmeView.setOnClickListener 를 지정해도 되지만  
     ViewHolder의 역할을 약간 벗어나는 경향이 있습니다.  
     하지만 코드는 간결해지고 관리하기 쉬워집니다.  
  
ex)  
override fun onBindViewHolder(holder: CrimeHolder, position: Int) {  //CrimeHolder = 1번  
     val crime = crimes[position]  // postion 2번  
     holder.bind(crime)   // bind 함수는 ViewHolder 에서 만든 업데이트 하는 함수임, 4번  
     holder.itemView.setOnClickListener{  
     // 아이템은 ViewHolder에서 관리하므로 Holder에서 itemView 함수를 써서 아이템을 가져온 후 리스너 등록 5번, 3번               
   }  
 }   
  
RecyclerView가 목록을 표시한다면 onBindViewHolder() 메서드에서는 해당 포지션의 데이터를 가져와서 뷰에 설정합니다.  
이를 통해 RecyclerView는 스크롤될 때 마다 각 아이템을 표시할 때마다 호출되어 뷰가 적절하게 업데이트되게 됩니다.  
  
Adapter (어댑터):  
- 데이터와 뷰 간의 다리 역할을 하는 객체입니다.
- RecyclerView에 표시될 데이터를 제공하고, 각 아이템의 뷰를 생성합니다.
- ViewHolder를 생성하고 뷰에 데이터를 바인딩하는 역할을 합니다.
- 필요에 따라 데이터 세트가 변경될 때 RecyclerView에 알리고 새로운 데이터를 로드합니다.  
  
간단히 말하면, RecyclerView는 화면에 목록을 표시하고, ViewHolder는 각 아이템의 뷰를 관리하며, Adapter는 데이터를 가져와서 뷰에 바인딩하여 RecyclerView에 제공  

Adapter는 데이터를 가져와서 ViewHolder에게 각각의 아이템에 해당하는 뷰를 생성, 그 뷰에 데이터를 바인딩하는 역할을 합니다.  
ViewHolder는 재활용 가능한 뷰를 담당하면서, 이 뷰에 대한 업데이트나 데이터 바인딩을 수행합니다.  

# Fragment
Framgnet로 앱의 UI를 관리하면 유연성이 좋아진다  
액티비티의 작업(UI 관리) 수행을 대행할 수 있는 컨트롤러 객체   ※ UI는 화면 전체 또는 일부분이 될 수 있다.  
프래그먼트 뷰는 사용자가 보면서 상호작용 하기 원하는 UI 요소들을 포함  

## 생명주기
액티비티 생명주기와 유사함.  
중단(stopped), 일시중지(pause), 실행 재개(resumed) 상태를 가짐
액티비티의 생명주기와 다른 점은 생명 주기 함수를 안드로이드 운영체제가 아닌 FragmentManager가 호출한다.

## UI 프래그먼트
말그대로 UI 를 관리하는 프래그먼트  
레이아웃 파일로부터 인플레이트(inflate) 되는 자신의 뷰를 갖는다.  

## 프래그먼트 매니저(Fragment Manager)
프래그먼트 리스트와 프래그먼트 트랜잭션의 백스택(back stack)을 처리한다.  
프래그먼트의 뷰를 액티비티의 뷰 계층에 추가, 프래그먼트의 생명 주기를 주도하는 책임을 갖는다.  
프래그먼트 트랜잭션의 백 스택을 유지 관리, 따라서 프래그먼트 트랜잭션이 다수의 오퍼레이션을 포함한다면  
해당 트랜잭션이 백스택에서 제거될 때 이 오퍼래이션들이 역으로 실행된다.  
그러므로 다수의 프래그먼트 오퍼레이션들을 하나의 트랜잭션으로 묶으면 UI상태를 더욱 잘 제어할 수 있다.

## 프래그먼트 트랜잭션(Fragment Transaction)
프래그먼트 리스트에 프래그먼트를 추가(add), 삭제(remove), 첨부9attach, 분리(detach), 변경(replace) 하는데 사용  
여러개의 오퍼레이션(트랜잭션으로 실행되는 각 함수 코드)을 묶어서 수행할 수 있다.  
ex) 다수의 프래그먼트를 동시에 서로 다른 컨테이너에 추가.  

컨테이너 뷰의 resource id를 사용하여 UI 프래그먼트를 식별함

