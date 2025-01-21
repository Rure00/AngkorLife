package com.rure.angkorlife.presentation.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.rure.angkorlife.data.entity.CandidateProfile
import com.rure.angkorlife.presentation.component.VoteConfirmDialog
import com.rure.angkorlife.presentation.screen.page.HomeCountPage
import com.rure.angkorlife.presentation.screen.page.VoteGuidePage
import com.rure.angkorlife.presentation.screen.page.VotePage
import com.rure.angkorlife.presentation.state.ProfileData
import com.rure.angkorlife.presentation.viewmodel.MainViewModel
import com.rure.angkorlife.ui.theme.TransparentBlack

@Composable
fun HomeScreen(
    toProfileScreen: (ProfileData) -> Unit
) {

    val showDialog = remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    //Log.d("HomeScreen", listState.firstVisibleItemScrollOffset.toString())
    val dialogOffset = remember {
        derivedStateOf {

            listState.firstVisibleItemScrollOffset
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        item {
            Column {
                HomeCountPage()
                VoteGuidePage()
                VotePage(
                    openDialog = { showDialog.value = true },
                    toProfileScreen = toProfileScreen,
                )
            }
        }
    }

    if(showDialog.value) {
        VoteConfirmDialog {
            showDialog.value = false
        }
    }
}