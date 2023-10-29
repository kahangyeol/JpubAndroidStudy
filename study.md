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





