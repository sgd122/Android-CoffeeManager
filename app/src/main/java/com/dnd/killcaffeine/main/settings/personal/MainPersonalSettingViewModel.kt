/*
 * Created by Lee Oh Hyoung on 2019-08-31..
 */
package com.dnd.killcaffeine.main.settings.personal

import androidx.lifecycle.LiveData
import com.dnd.killcaffeine.R
import com.dnd.killcaffeine.base.BaseViewModel
import com.dnd.killcaffeine.model.data.room.personal.Personal
import com.dnd.killcaffeine.model.data.room.personal.PersonalDatabase
import com.dnd.killcaffeine.utils.SingleLiveEvent
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainPersonalSettingViewModel(private val mPersonalDatabase: PersonalDatabase) : BaseViewModel() {

    private val _insertPersonalLiveData = SingleLiveEvent<Any>()
    val insertPersonalLiveData: LiveData<Any> get() = _insertPersonalLiveData

    private val radioButtonIdList = arrayListOf(
        R.id.activity_settings_personal_adult_button,
        R.id.activity_settings_personal_pregnant,
        R.id.activity_settings_personal_teen_and_old
    )

    private var personalWeight: Int = 0

    fun createPersonalInfo(radioButtonId: Int, weight: Int) {
        val bodyType = when(radioButtonId){
            radioButtonIdList[0] -> "성인"
            radioButtonIdList[1] -> "임산부"
            radioButtonIdList[2] -> "청소년/노인"
            else -> "성인"
        }

        val personal = Personal(0, bodyType, weight, recommendIntakeCalculation(radioButtonId, weight))
        Logger.d("저장할 마이 카페인 : $personal")

        insertPersonalInfo(personal)

    }

    private fun insertPersonalInfo(personal: Personal){
        addDisposable(mPersonalDatabase.mPersonDao.insertPersonalInfo(personal)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _insertPersonalLiveData.call()

            }, {
                Logger.d(it.message)
                showSnackbar("마이 카페인 설정이 실패했습니다.\n잠시후에 다시 시도해주세요")
            }))
    }

    fun recommendIntakeCalculation(radioButtonId: Int, weight: Int): Int{
        return when(radioButtonId){
            radioButtonIdList[0] -> 400
            radioButtonIdList[1] -> 300
            radioButtonIdList[2] -> (weight * 2)
            else -> 400
        }
    }

    fun setPersonalWeight(weight: Int){
        personalWeight = weight
    }

    fun getPersonalWeight() = personalWeight

    fun checkPersonalInfo(bodyType: Int, weight: Int): Boolean{
        Logger.d("bodyType = $bodyType")

        radioButtonIdList.forEachIndexed { index, i ->
            Logger.d("radioButton[$index] = $i")
        }

        if(bodyType != radioButtonIdList[0] && bodyType != radioButtonIdList[1] && bodyType != radioButtonIdList[2]){
            showSnackbar("신체구분 선택이 올바르지 않습니다.")
            return false
        }

        else if(weight == 0){
            showSnackbar("체중이 0kg 입니다. 다시 입력주세요")
            return false
        }

        else {
            return true
        }
    }
}