package com.rure.angkorlife.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rure.angkorlife.R
import com.rure.angkorlife.presentation.navigation.Destination
import com.rure.angkorlife.ui.theme.Typography
import com.rure.angkorlife.ui.theme.White

@Composable
fun AppTopBar(navController: NavController, screen: Destination) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .wrapContentHeight()
            .background(White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Log.d("AppTopBar", "destination: ${screen.route}")
        Log.d("AppTopBar", "screen ShowBackBtn: ${screen.showBackButton}")
        if(screen.showBackButton) {
            Image(
                painter = painterResource(R.drawable.back_arrow),
                contentDescription = null,
                modifier = Modifier.size(24.dp).offset(x = (16).dp).clickable {
                    navController.popBackStack()
                }
            )
        } else {
            Spacer(modifier = Modifier.size(24.dp).offset(x = (16).dp))
        }

        Text(
            text = stringResource(R.string.main_title),
            color = Color.Black,
            fontWeight = FontWeight.W500,
            fontSize = 18.sp,
            lineHeight = 26.sp,
            modifier = Modifier.padding(vertical = 15.dp)
        )

        Image(
            painter = painterResource(R.drawable.x_btn),
            contentDescription = null,
            modifier = Modifier.size(24.dp).offset(x = (-16).dp).clickable {
                //TODO: 어디로?
            }
        )
    }
}