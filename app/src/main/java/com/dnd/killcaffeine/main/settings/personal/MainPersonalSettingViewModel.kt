/*
 * Created by Lee Oh Hyoung on 2019-08-31..
 */
package com.dnd.killcaffeine.main.settings.personal

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.SharedPreferenceKey
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.Personal
import com.dnd.killcaffeine.model.data.PersonalBodyType
import com.dnd.killcaffeine.utils.SingleLiveEvent
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPersonalSettingViewModel(private val mSharedPref: SharedPreferences) : BaseViewModel() {

    companion object {

        private fun ADULT_RECOMMEND(weight: Int): Int = weight * 6
        const val PREGNANT_RECOMMEND: Int = 200
        const val TEEN_AND_OLD_RECOMMEND: Int = 100
    }

    private val _insertPersonalLiveData = SingleLiveEvent<Any>()
    val insertPersonalLiveData: LiveData<Any> get() = _insertPersonalLiveData

    private val _savedPersonalLiveData = MutableLiveData<Personal>()
    val savedPersonalLiveData: LiveData<Personal> get() = _savedPersonalLiveData

    val radioButtonIdList = arrayListOf(
        R.id.activity_settings_personal_adult_button,
        R.id.activity_settings_personal_pregnant,
        R.id.activity_settings_personal_teen_and_old
    )

    private var personalWeight: Int = 0

    fun createPersonalInfo(radioButtonId: Int, weight: Int) {
        val bodyType = when(radioButtonId){
            radioButtonIdList[0] -> PersonalBodyType.ADULT
            radioButtonIdList[1] -> PersonalBodyType.PREGNANT
            radioButtonIdList[2] -> PersonalBodyType.TEEN_OLD
            else -> PersonalBodyType.ADULT
        }

        val personal = Personal(bodyType, weight, recommendIntakeCalculation(radioButtonId, weight))
        Logger.d("저장할 마이 카페인 : $personal")

        putPersonalInfo(personal)

    }

    private fun putPersonalInfo(personal: Personal){
        addDisposable(Observable.just(mSharedPref.edit().putString(SharedPreferenceKey.PUT_PERSONAL_INFO, Gson().toJson(personal)).commit())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertPersonalLiveData.call()

                Logger.d("personal_caffeine 저장 완료")

            }, {
                Logger.d(it.message)
                showSnackbar("마이 카페인 설정이 실패했습니다.\n잠시후에 다시 시도해주세요")
            }))
    }

    fun getPersonalInfo() {
        addDisposable(Observable.just(mSharedPref.getString(SharedPreferenceKey.PUT_PERSONAL_INFO, ""))
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe({ personal ->
                if(!personal.isNullOrEmpty()){
                    val savedPersonalInfo = Gson().fromJson(personal, Personal::class.java)
                    Logger.d("불러온 personal : $savedPersonalInfo")

                    _savedPersonalLiveData.postValue(savedPersonalInfo)
                }

            }, {

            }))
    }

    fun recommendIntakeCalculation(radioButtonId: Int, weight: Int): Int{
        return when(radioButtonId){
            radioButtonIdList[0] -> ADULT_RECOMMEND(weight = weight)
            radioButtonIdList[1] -> PREGNANT_RECOMMEND
            radioButtonIdList[2] -> TEEN_AND_OLD_RECOMMEND
            else -> ADULT_RECOMMEND(weight = weight)
        }
    }

    fun setPersonalWeight(weight: Int){
        personalWeight = weight
    }

    fun getPersonalWeight() = personalWeight

    fun checkPersonalInfo(bodyType: Int, weight: Int): Boolean{
        return if(bodyType != radioButtonIdList[0] && bodyType != radioButtonIdList[1] && bodyType != radioButtonIdList[2]){
            showSnackbar("신체구분 선택이 올바르지 않습니다.")
            false

        } else if(weight == 0){
            showSnackbar("체중이 0kg 입니다. 다시 입력주세요")
            false

        } else {
            true
        }
    }
}