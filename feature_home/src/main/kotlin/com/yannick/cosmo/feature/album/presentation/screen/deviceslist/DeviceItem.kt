package com.yannick.cosmo.feature.album.presentation.screen.deviceslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.yannick.cosmo.base.presentation.compose.composable.TextTitleMedium
import com.yannick.cosmo.base.presentation.compose.theme.AndroidCosmoAppTheme
import com.yannick.cosmo.feature.album.domain.model.DeviceUiModel

@Composable
fun DeviceItem(
    deviceUiModel: DeviceUiModel,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    ElevatedCard(
        modifier = modifier
            .padding(horizontal = 12.dp, vertical = 5.dp)
            .clickable {
                onClick(deviceUiModel.id)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .padding(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
            ) {
                TextTitleMedium(
                    text = deviceUiModel.model ?: "-",
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = deviceUiModel.serial ?: "-",
                    fontStyle = FontStyle.Italic,
                    color = Color.Gray,
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = deviceUiModel.lightMode ?: "-",
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${deviceUiModel.firmwareVersion ?: "-"} : ${deviceUiModel.installationMode ?: "-"}",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Preview
@Composable
fun ContactUiModelItemPreview() {
    AndroidCosmoAppTheme {
        DeviceItem(
            deviceUiModel = DeviceUiModel(
                brakeLight = null,
                firmwareVersion = null,
                installationMode = null,
                lightAuto = null,
                lightMode = null,
                lightValue = null,
                macAddress = null,
                model = null,
                product = null,
                serial = "epicurei",
                0
            ),
            modifier = Modifier.fillMaxWidth(),
            {},
        )
    }
}
