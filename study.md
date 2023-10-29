리소스를 코드에서 사용하려면 리소스 ID가 필요 20P

지정한 리소스 ID는 앱 빌드 할 때마다 안드로이드 빌드도구가 R.class에 자동으로 생성한다
ex)R.layout.main_activity, R.string.app_name    -> R클래스의 내부 클래스인 layout 안에 정수형 상수로 되어있음 (ResId = Int형)

가로방향 레이웃 생성하기 76P 
Android Resource File 선택 후 orientation >> 선택 후 landspace
루트요소 필드에 Frame Layout

isFinish 88P
isFinish = true  -> 사용자가 액티비티를 끝냄(백버튼, 오버뷰에서 삭제)
         = flase -> 화면 회전에 따른 구성변경으로 시스템이 현재 액티비티 인스턴스를 소멸시킴

Bundle 96P
사용예시) onCreate(savedInstanceState: **Bundle**)
문자열 키와 쌍을 이루는 값을 가지는 구조체
"key",value

SIS(Saved Instance State) 저장된 인스턴스 상태 96P

  
