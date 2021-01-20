package vukan.com.euprava.ui.examination

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.widget.NumberPicker
import android.widget.TimePicker
import java.lang.reflect.Field

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
        private const val TIME_PICKER_INTERVAL = 15
    }

    override fun updateTime(hourOfDay: Int, minuteOfHour: Int) {
        mTimePicker.currentHour = hourOfDay
        mTimePicker.currentMinute = minuteOfHour / TIME_PICKER_INTERVAL
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        when (which) {
            BUTTON_POSITIVE -> mTimeSetListener?.onTimeSet(
                mTimePicker, mTimePicker.currentHour,
                mTimePicker.currentMinute * TIME_PICKER_INTERVAL
            )
            BUTTON_NEGATIVE -> cancel()
        }
    }

    @SuppressLint("PrivateApi")
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        try {
            val classForid = Class.forName("com.android.internal.R\$id")
            val timePickerField: Field = classForid.getField("timePicker")
            mTimePicker = findViewById(timePickerField.getInt(null))
            val minuteField: Field = classForid.getField("minute")
            val hourField: Field = classForid.getField("hour")
            val minuteSpinner = mTimePicker.findViewById(minuteField.getInt(null)) as NumberPicker
            val mHourSpinner = mTimePicker.findViewById(hourField.getInt(null)) as NumberPicker

            minuteSpinner.minValue = 0
            minuteSpinner.maxValue = 60 / TIME_PICKER_INTERVAL - 1
            val displayedValues: MutableList<String> = ArrayList()
            var i = 0

            while (i < 60) {
                displayedValues.add(String.format("%02d", i))
                i += TIME_PICKER_INTERVAL
            }

            minuteSpinner.displayedValues = displayedValues.toTypedArray()
            val day = SchedulingExaminationFragment.dateTime.toDate().day

            if ((day in 0..7) || (day in 15..21)) {
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