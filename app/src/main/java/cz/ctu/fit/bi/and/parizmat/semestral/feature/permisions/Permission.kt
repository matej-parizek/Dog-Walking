package cz.ctu.fit.bi.and.parizmat.semestral.feature.permisions

import android.Manifest
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import cz.ctu.fit.bi.and.parizmat.semestral.R

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permission(
    rationale: String,
    content: @Composable () -> Unit = {},
) {
    val permissions: MutableList<String> = mutableListOf()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        permissions += Manifest.permission.POST_NOTIFICATIONS
    }
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        permissions += Manifest.permission.ACTIVITY_RECOGNITION
    }
    permissions+=Manifest.permission.INTERNET

    val permissionState = rememberMultiplePermissionsState(permissions)
    if (permissionState.allPermissionsGranted) {
        content()
    } else {
        if (permissionState.shouldShowRationale) {
            AlertDialog(
                onDismissRequest = { },
                title = { Text(text = stringResource(id = R.string.permission_request)) },
                text = { Text(rationale) },
                confirmButton = {
                    Button(
                        onClick = { permissionState.launchMultiplePermissionRequest() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = stringResource(id = R.string.ok)
                        )
                    }
                },
            )
        } else {
            LaunchedEffect(Unit) {
                permissionState.launchMultiplePermissionRequest()
            }
        }
    }
}