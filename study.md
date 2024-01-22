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


# Fragment
Framgnet로 앱의 UI를 관리하면 유연성이 좋아진다  
액티비티의 작업(UI 관리) 수행을 대행할 수 있는 컨트롤러 객체   ※ UI는 화면 전체 또는 일부분이 될 수 있다.  
프래그먼트 뷰는 사용자가 보면서 상호작용 하기 원하는 UI 요소들을 포함  

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

