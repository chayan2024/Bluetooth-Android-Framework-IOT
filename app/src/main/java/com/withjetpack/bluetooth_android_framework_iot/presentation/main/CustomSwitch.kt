package com.withjetpack.bluetooth_android_framework_iot.presentation.main
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Switch
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

    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(30.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = labelText,
            color = Color.Blue
        )
        Switch(
            checked = switchState,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.align(Alignment.Top)
        )
    }

}