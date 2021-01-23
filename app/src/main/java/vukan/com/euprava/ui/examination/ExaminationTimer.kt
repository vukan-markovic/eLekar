package vukan.com.euprava.ui.examination

import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.res.Resources
import android.os.Build
import android.widget.NumberPicker
import android.widget.TimePicker
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class ExaminationTimer(
    context: Context?,
    private val mTimeSetListener: OnTimeSetListener?,
    hourOfDay: Int,
    minute: Int,
    is24HourView: Boolean
) : TimePickerDialog(
    context, THEME_HOLO_LIGHT, null, hourOfDay,
    minute / TIME_PICKER_INTERVAL, is24HourView
) {

    private lateinit var mTimePicker: TimePicker

    companion object {
        private const val TIME_PICKER_INTERVAL = 30
    }

    override fun updateTime(hourOfDay: Int, minuteOfHour: Int) {
        if (Build.VERSION.SDK_INT >= 23) {
            mTimePicker.hour = hourOfDay
            mTimePicker.minute = minuteOfHour / TIME_PICKER_INTERVAL
        } else {
            mTimePicker.currentHour = hourOfDay
            mTimePicker.currentMinute = minuteOfHour / TIME_PICKER_INTERVAL
        }
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        if (Build.VERSION.SDK_INT >= 23) {
            when (which) {
                BUTTON_POSITIVE -> mTimeSetListener?.onTimeSet(
                    mTimePicker, mTimePicker.hour,
                    mTimePicker.minute * TIME_PICKER_INTERVAL
                )
                BUTTON_NEGATIVE -> cancel()
            }
        } else {
            when (which) {
                BUTTON_POSITIVE -> mTimeSetListener?.onTimeSet(
                    mTimePicker, mTimePicker.currentHour,
                    mTimePicker.currentMinute * TIME_PICKER_INTERVAL
                )
                BUTTON_NEGATIVE -> cancel()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        try {
            mTimePicker = findViewById(
                Resources.getSystem().getIdentifier(
                    "timePicker",
                    "id",
                    "android"
                )
            )

            val minuteSpinner = mTimePicker.findViewById(
                Resources.getSystem().getIdentifier(
                    "minute",
                    "id",
                    "android"
                )
            ) as NumberPicker

            val mHourSpinner = mTimePicker.findViewById(
                Resources.getSystem().getIdentifier(
                    "hour",
                    "id",
                    "android"
                )
            ) as NumberPicker

            minuteSpinner.minValue = 0
            minuteSpinner.maxValue = 60 / TIME_PICKER_INTERVAL - 1
            val displayedValues: MutableList<String> = ArrayList()
            var i = 0

            while (i < 60) {
                displayedValues.add(String.format("%02d", i))
                i += TIME_PICKER_INTERVAL
            }

            minuteSpinner.displayedValues = displayedValues.toTypedArray()
            val cal = Calendar.getInstance()
            cal.time = SchedulingExaminationFragment.dateTime.toDate()
            val day = cal.get(Calendar.DAY_OF_MONTH)

            if ((day in 1..7) || (day in 15..21)) {
                mHourSpinner.minValue = 7
                mHourSpinner.maxValue = 13
            } else if ((day in 8..14) || (day in 22..31)) {
                mHourSpinner.minValue = 13
                mHourSpinner.maxValue = 19
            }

            mTimePicker.setIs24HourView(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}