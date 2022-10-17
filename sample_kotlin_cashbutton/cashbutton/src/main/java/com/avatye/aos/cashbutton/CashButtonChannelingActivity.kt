package com.avatye.aos.cashbutton

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.avatye.sdk.cashbutton.CashButtonConfig
import com.avatye.sdk.cashbutton.IButtonCallback
import com.avatye.sdk.cashbutton.core.entity.cashmore.AvatyeUserData
import com.avatye.sdk.cashbutton.core.widget.FloatingButtonLayout

class CashButtonChannelingActivity : AppCompatActivity() {

    /** 캐시버튼 채널링 테스트 시 본인 번호 설정 */
    private val phoneNumber = ""

    private var floatingButtonLayout: FloatingButtonLayout? = null

    private lateinit var sharedPref: SharedPreferences

    private val isShowButton
        get() = sharedPref.getBoolean(getString(R.string.saved__key_is_show_button), true)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cash_button_channeling)
        sharedPref =
            getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE)

        initCashButtonChanneling()

        findViewById<Button>(R.id.GoToSetting).setOnClickListener {
            startActivity(Intent(this@CashButtonChannelingActivity, SettingActivity::class.java))
        }

        findViewById<Button>(R.id.CustomCashButton).setOnClickListener {
            CashButtonConfig.start(this)
        }
    }

    /** 캐시버튼 채널링 기본설정 */
    private fun initCashButtonChanneling() {
        val userData = AvatyeUserData(
            userID = "USERID_$phoneNumber", phoneNumber = phoneNumber, nickname = phoneNumber
        )

        CashButtonConfig.userData = userData

        if (isShowButton) {
            showFloatingButton()
        }
    }

    /**
     * 채널링 서비스 이용 시 별도의 진입 점을 이용하지 않고
     * 기본 제공되는 버튼 진입점을 사용시 start를 호출하지 않고 show를 호출한다.
     * */
    private fun showFloatingButton() {
        CashButtonConfig.show(activity = this@CashButtonChannelingActivity,
            object : IButtonCallback {
                override fun onSuccess(view: FloatingButtonLayout?) {
                    floatingButtonLayout = view
                }
            })
    }

    override fun onResume() {
        super.onResume()
        if (!isShowButton) {
            /**
             * 채널링 서비스에서 CashButtonConfig.show 사용시 별도로 hide 시키는 기능
             * */
            floatingButtonLayout?.let {
                CashButtonConfig.hide(this)
            }
        } else {
            showFloatingButton()
        }
    }
}