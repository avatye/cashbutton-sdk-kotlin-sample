package com.avatye.aos.cashscreen

import android.app.Application
import com.avatye.sdk.cashbutton.CashButtonConfig

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        /** 발급받은 서비스 키가 없다면 테스트 키로 적용해주세요.
         *
         *  캐시버튼 테스트 키
         *  appID = "91e4b7f81a6511ea813d0a4916b2361a", appSecret = "aafc702323bf6a214"
         *
         *  캐시버튼 채널링 테스트 키
         *  appID = "69f43822b64d46d6bc01ff6d282f754d", appSecret = "077b11dc468f4bd3"
         *
         *  채널링의 경우 CashButtonChannelingActivity에서 phoneNumber를 반드시 설정해주셔야 하며,
         *  서비스 변경 시 설치된 앱 삭제 후 새로 설치해주세요.
         * */
        CashButtonConfig.initWithScreen(application = this, appID = "69f43822b64d46d6bc01ff6d282f754d", appSecret = "077b11dc468f4bd3", "193953382236994")

        /** 커스텀 캐시버튼 사용시에만 설정해주세요 */
//        CashButtonConfig.useCustomCashButton = true

        /** Java로 작성된 코드로 테스트를 원하시는 경우 설정해주세요 */
//        MainActivity.startJavaSample = true
    }
}