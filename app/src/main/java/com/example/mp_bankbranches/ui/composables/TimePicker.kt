package com.example.mp_bankbranches.ui.composables

import android.app.TimePickerDialog
import android.widget.TimePicker
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TimePicker(
    fromHour: Float?,
    toHour: Float?,
    onFromChange: (Float) -> Unit,
    onToChange: (Float) -> Unit
) {
    val context = LocalContext.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(onClick = {
            TimePickerDialog(
                context,
                { _: TimePicker, hour: Int, minute: Int ->
                    val decimalHour = hour + (minute / 60f)
                    onFromChange(decimalHour)
                },
                fromHour?.toInt() ?: 9,
                0,
                true
            ).show()
        }) {
            Text("From: ${formatHour(fromHour)}")
        }

        Button(onClick = {
            TimePickerDialog(
                context,
                { _: TimePicker, hour: Int, minute: Int ->
                    val decimalHour = hour + (minute / 60f)
                    onToChange(decimalHour)
                },
                toHour?.toInt() ?: 17,
                0,
                true
            ).show()
        }) {
            Text("To: ${formatHour(toHour)}")
        }
    }
}

private fun formatHour(hour: Float?): String {
    return hour?.let {
        val h = it.toInt().toString().padStart(2, '0')
        val m = ((it - it.toInt()) * 60).toInt().toString().padStart(2, '0')
        "$h:$m"
    } ?: "--:--"
}
