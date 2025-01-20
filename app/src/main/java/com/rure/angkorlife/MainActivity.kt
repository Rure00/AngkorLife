package com.rure.angkorlife

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.rure.angkorlife.domain.repository.RetrofitRepository
import com.rure.angkorlife.domain.repository.RetrofitResult
import com.rure.angkorlife.presentation.navigation.Destination
import com.rure.angkorlife.presentation.navigation.MainNavRoute
import com.rure.angkorlife.presentation.navigation.mainNavGraph
import com.rure.angkorlife.presentation.viewmodel.MainViewModel
import com.rure.angkorlife.ui.theme.AngkorLifeTheme
import com.rure.angkorlife.ui.theme.BackgroundBlack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AngkorLifeTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .imePadding()
                ) {
                    MainPage()
                }
            }
        }
    }
}

@Composable
fun MainPage() {
    val navController = rememberNavController()
    val screen: MutableState<Destination> = remember {
        mutableStateOf(Destination.Home)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = BackgroundBlack,
    ) { innerPadding ->
        val pagerState = rememberPagerState(0, 0f) {
            Destination::class.nestedClasses.size
        }

        HorizontalPager(
            modifier = Modifier.padding(innerPadding),
            state = pagerState,
            userScrollEnabled = false
        ) {
            NavHost(
                navController,
                startDestination = MainNavRoute
            ) {
                mainNavGraph(navController)
            }
        }
    }
}