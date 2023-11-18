package com.withjetpack.bluetooth_android_framework_iot.presentation.main
import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import com.withjetpack.bluetooth_android_framework_iot.common.CustomSwitch
import com.withjetpack.bluetooth_android_framework_iot.common.ToolBar
import com.withjetpack.bluetooth_android_framework_iot.util.REQUEST_ENABLE_BT

@SuppressLint("UnrememberedMutableState")
@Composable
fun MainScreen(navController: NavHostController) {


    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var switchState by remember { mutableStateOf(false) }

        ToolBar()

        CustomSwitch(
            switchState = switchState,
            onCheckedChange = { newState ->
                switchState = newState
            },
            labelText = "Please Turn on the Bluetooth Permission"
        )

        if (switchState) {
            // When the switch is turned on, show the ExampleScreenWithAccompanist
            ExampleScreenWithAccompanist(LocalContext.current as ComponentActivity)
        } else {
            // Add your logic here for when the switch is turned off
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("DetailScreen")
            },
            modifier = Modifier
                .height(80.dp)
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Blue, // Set the default background color
                contentColor = Color.White // Set the text color
            )
        ) {
            Text(text = "Search Device")
        }
    }

}

val permissionsList = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

    listOf(
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.BLUETOOTH_SCAN,
        Manifest.permission.BLUETOOTH_CONNECT,
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )
} else {
    listOf(
        Manifest.permission.BLUETOOTH,
        Manifest.permission.BLUETOOTH_ADMIN,
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )
}

@Composable
fun ExampleScreenWithAccompanist(activity: ComponentActivity) {

    val requestPermissionLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->

            val grantedPermissions = permissions.filter { it.value }.keys
            val deniedPermissions = permissions.filterNot { it.value }.keys

            when {
                grantedPermissions.contains(Manifest.permission.BLUETOOTH_CONNECT) -> {
                    enableBluetooth(activity)
                }
                deniedPermissions.isNotEmpty() -> {
                }
                else -> {
                }
            }
        }

    LaunchedEffect(true){
        val permissionsToRequest = permissionsList.toMutableList()
        // Add Bluetooth permission to the list of permissions to request
        permissionsToRequest.add(Manifest.permission.BLUETOOTH_CONNECT)
        requestPermissionLauncher.launch(permissionsToRequest.toTypedArray())
    }

}

fun enableBluetooth(activity: ComponentActivity) {
    val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        ?: return

    if (!bluetoothAdapter.isEnabled) {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.BLUETOOTH_CONNECT
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT)
    }
}