package com.lt2333.simplicitytools.hook.app.systemui

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.getObject
import com.github.kyuubiran.ezxhelper.utils.hookAfter
import com.lt2333.simplicitytools.util.hasEnable
import com.lt2333.simplicitytools.util.xposed.base.HookRegister

object HideBatteryIcon : HookRegister() {

    override fun init() {
        findMethod("com.android.systemui.statusbar.views.MiuiBatteryMeterView") {
            name == "updateResources"
        }.hookAfter {
            //隐藏电池图标
            hasEnable("hide_battery_icon") {
                (it.thisObject.getObject("mBatteryIconView") as ImageView).visibility = View.GONE
                if (it.thisObject.getObject("mBatteryStyle") == 1) {
                    (it.thisObject.getObject("mBatteryDigitalView") as FrameLayout).visibility =
                        View.GONE
                }
            }
            //隐藏电池内的百分比
            hasEnable("hide_battery_percentage_icon") {
                (it.thisObject.getObject("mBatteryPercentMarkView") as TextView).textSize = 0F
            }
            //隐藏电池百分号
            hasEnable("hide_battery_percentage_icon") {
                (it.thisObject.getObject("mBatteryPercentMarkView") as TextView).textSize = 0F
            }
        }

        findMethod("com.android.systemui.statusbar.views.MiuiBatteryMeterView") {
            name == "updateChargeAndText"
        }.hookAfter {
            //隐藏电池充电图标
            hasEnable("hide_battery_charging_icon") {
                (it.thisObject.getObject("mBatteryChargingInView") as ImageView).visibility =
                    View.GONE
                (it.thisObject.getObject("mBatteryChargingView") as ImageView).visibility =
                    View.GONE
            }
        }
    }
}