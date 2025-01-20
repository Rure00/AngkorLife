package com.rure.angkorlife.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.rure.angkorlife.R
import com.rure.angkorlife.presentation.component.CountDownView
import kotlinx.coroutines.delay
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month

@Composable
fun HomeScreen(

) {
    val listState = rememberLazyListState()
    val goalTime = remember { LocalDateTime.of(2025, Month.FEBRUARY, 3, 0, 0) }
    val now = remember {
        mutableStateOf(LocalDateTime.now())
    }

    val duration = remember {
        derivedStateOf {
            Duration.between(now.value, goalTime)
        }
    }

    LaunchedEffect(now.value) {
        delay(1000)
        now.value = LocalDateTime.now()
    }

    LazyColumn(
        state = listState
    ) {
        item {

            Column(
                modifier = Modifier.fillMaxSize().background(color = Color.Black)
            ) {
                Image(
                    painter = painterResource(R.drawable.home_top),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(23.dp))

                Box(
                    modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(horizontal = 57.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CountDownView(duration.value)
                }

                Spacer(modifier = Modifier.height(23.dp))

                Image(
                    painter = painterResource(R.drawable.home_bottom),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )
            }

        }
    }
}