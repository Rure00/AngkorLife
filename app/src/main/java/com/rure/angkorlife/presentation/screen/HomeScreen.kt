package com.rure.angkorlife.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rure.angkorlife.data.entity.CandidateProfile
import com.rure.angkorlife.presentation.screen.page.HomeCountPage
import com.rure.angkorlife.presentation.screen.page.VoteGuidePage
import com.rure.angkorlife.presentation.screen.page.VotePage
import com.rure.angkorlife.presentation.state.ProfileData

@Composable
fun HomeScreen(
    toProfileScreen: (ProfileData) -> Unit
) {

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        state = listState
    ) {
        item {
            Column {
                HomeCountPage()
                VoteGuidePage()
                VotePage(toProfileScreen)
            }
        }
    }
}