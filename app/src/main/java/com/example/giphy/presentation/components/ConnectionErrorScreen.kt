package com.example.giphy.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.giphy.R

@Composable
fun ConnectionErrorScreen(
    modifier: Modifier = Modifier,
    reload: () -> Unit
) = Column(
    modifier = modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
) {
    Icon(
        modifier = Modifier.size(150.dp),
        painter = painterResource(R.drawable.outline_wifi_off_24),
        tint = MaterialTheme.colorScheme.error,
        contentDescription = null
    )

    Spacer(modifier = Modifier.height(12.dp))

    IconButton(onClick = reload) {
        Icon(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Default.Refresh,
            tint = MaterialTheme.colorScheme.onSurface,
            contentDescription = null
        )
    }
}