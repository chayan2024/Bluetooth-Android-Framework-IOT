package com.withjetpack.bluetooth_android_framework_iot.common
import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomSwitch(
    switchState: Boolean, onCheckedChange: (Boolean) -> Unit,
    labelText: String
) {
    val switchColor = if (switchState) Color(0xFF6200EE) else Color.Red

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = labelText,
            color = Color.Blue,
            modifier = Modifier
                .weight(1f) // Take up available space
                .padding(end = 8.dp) // Adjust padding as needed
        )
        Switch(
            checked = switchState,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = switchColor,
                uncheckedThumbColor = Color.Gray,
                checkedTrackColor = switchColor.copy(alpha = 0.5f),
                uncheckedTrackColor = Color.Gray.copy(alpha = 0.5f)
            ),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

