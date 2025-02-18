package com.rure.angkorlife.presentation.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rure.angkorlife.R
import com.rure.angkorlife.presentation.MainActivity
import com.rure.angkorlife.presentation.component.RoundButton
import com.rure.angkorlife.presentation.component.LoginTextField
import com.rure.angkorlife.presentation.viewmodel.MainViewModel
import com.rure.angkorlife.ui.theme.BackgroundBlack
import com.rure.angkorlife.ui.theme.ButtonBlue
import com.rure.angkorlife.ui.theme.TextGray
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    toHomeScreen: () -> Unit,
    mainViewModel: MainViewModel = viewModel(LocalContext.current as MainActivity)
) {
    val tag = "LoginScreen"

    val idState = remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)

    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }


    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
    ) {
        Image(
            painter = painterResource(R.drawable.home_top),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )

        Column(
            modifier = Modifier.fillMaxWidth().background(color = Color.Black).padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            LoginTextField(idState.value) { idState.value = it }

            Spacer(modifier = Modifier.height(24.dp))

            val buttonColor = if(idState.value.isEmpty()) TextGray
                            else ButtonBlue
            RoundButton(
                label = stringResource(R.string.login),
                color = buttonColor,
                modifier = Modifier.fillMaxWidth()
            ) {
                if(idState.value.isEmpty()) return@RoundButton

                mainViewModel.login(idState.value)
                toHomeScreen()
            }

        }

        Spacer(modifier = Modifier.fillMaxWidth().background(color = Color.Black).weight(1f))

        Image(
            painter = painterResource(R.drawable.home_bottom),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }

}