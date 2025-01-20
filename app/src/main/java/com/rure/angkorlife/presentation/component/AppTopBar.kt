package com.rure.angkorlife.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rure.angkorlife.R
import com.rure.angkorlife.presentation.navigation.Destination
import com.rure.angkorlife.ui.theme.Typography
import com.rure.angkorlife.ui.theme.White

@Composable
fun AppTapBar(navController: NavController, screen: Destination) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(65.dp)
            .background(White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if(screen.showBackButton) {
            IconButton(onClick = { navController.navigateUp() }) {
                Image(
                    painter = painterResource(R.drawable.back_arrow),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp).padding(vertical = 16.dp, horizontal = 32.dp)
                )
            }
        }

        Text(
            text = stringResource(R.string.main_title),
            textAlign = TextAlign.Start,
            modifier = Modifier
                .weight(1f),
            color = White
        )
    }
}