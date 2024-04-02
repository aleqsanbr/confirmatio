package com.aleqsanbr.confirmatio.screens.HelpNowMethods

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aleqsanbr.compose.md_theme_dark_inversePrimary
import com.aleqsanbr.compose.md_theme_dark_onSecondaryContainer
import com.aleqsanbr.compose.md_theme_dark_surfaceVariant
import com.aleqsanbr.confirmatio.R
import com.aleqsanbr.confirmatio.SubTitle
import com.aleqsanbr.confirmatio.Title
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Timer
import java.util.TimerTask

@Composable
fun Meditation() {
    val context = LocalContext.current
    val viewModel: MeditationViewModel = viewModel()
    val isPlaying by viewModel.isPlaying.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Title("Медитация")
            SubTitle("Выберите трек для прослушивания и наслаждайтесь моментом")
            Spacer(modifier = Modifier.padding(10.dp).size(8.dp))

            TrackList(
                trackList = listOf(
                    Track("Birds & Piano", "InnerTune", R.raw.birds_piano),
                    Track("Rain", "Unknown Artist", R.raw.rain),
                    Track("Sleep", "NaturesEye", R.raw.sleep),
                    Track("Stress Relief", "PianoAmor", R.raw.stress_relief_piano),
                    Track("Binaural Meditation 432 Hz", "Mapamusic", R.raw.binaural_meditation),
                ),
                onItemClick = { track ->
                    if (isPlaying) {
                        viewModel.togglePlayPause()
                    }
                    viewModel.playTrack(context, track)
                }
            )
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    val icon = if (isPlaying) Icons.Filled.Pause else Icons.Filled.PlayArrow
                    val buttonText = if (isPlaying) "Pause" else "Play"
                    IconButton(
                        icon = icon,
                        contentDescription = buttonText,
                        onClick = { viewModel.togglePlayPause() },
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    val progress by viewModel.progress.collectAsState()
                    SeekBar(progress = progress, onProgressChanged = { viewModel.seekTo(it) })
                }
            }
        }
    }
}

@Composable
fun TrackList(trackList: List<Track>, onItemClick: (Track) -> Unit) {
    LazyColumn(
        modifier = Modifier.height(380.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(trackList.size) { index ->
            val track = trackList[index]
            Card(
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(track) }
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Filled.MusicNote,
                        contentDescription = "Music Icon",
                        modifier = Modifier.size(50.dp),
                        tint = md_theme_dark_onSecondaryContainer
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(text = track.name, style = MaterialTheme.typography.h6)
                        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                            Text(text = track.artist, style = MaterialTheme.typography.body2)
                        }
                    }
                }

            }
        }
    }
}


@Composable
fun IconButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(64.dp)
            .clip(CircleShape)
            .background(color = md_theme_dark_inversePrimary)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier
                .size(36.dp)
                .padding(4.dp)
        )
    }
}

class MeditationViewModel : ViewModel() {
    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying
    private var mediaPlayer: MediaPlayer? = null
    private val _progress = MutableStateFlow(0f)
    val progress: StateFlow<Float> = _progress
    private var timer: Timer? = null

    fun playTrack(context: Context, track: Track) {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(context, track.resId).apply {
            start()
            setOnCompletionListener {
                it.release()
                _isPlaying.value = false
                timer?.cancel()
            }
        }
        _isPlaying.value = true
        startUpdatingProgress()
    }

    private fun startUpdatingProgress() {
        timer = Timer().apply {
            scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    mediaPlayer?.let { player ->
                        val progress = player.currentPosition.toFloat() / player.duration
                        _progress.value = progress
                    }
                }
            }, 0, 10)
        }
    }

    fun togglePlayPause() {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
                _isPlaying.value = false
                timer?.cancel()
            } else {
                player.start()
                _isPlaying.value = true
                startUpdatingProgress()
            }
        }
    }

    fun seekTo(progress: Float) {
        mediaPlayer?.let { player ->
            val duration = player.duration
            val seekTo = (duration * progress).toInt()
            if (seekTo < duration) {
                player.seekTo(seekTo)
            } else {
                player.seekTo(duration)
                _isPlaying.value = false
                timer?.cancel()
            }
        }
    }
}


@Composable
fun SeekBar(progress: Float, onProgressChanged: (Float) -> Unit) {
    Slider(
        value = progress,
        onValueChange = onProgressChanged,
        modifier = Modifier.fillMaxWidth(),
        colors = SliderDefaults.colors(
            thumbColor = md_theme_dark_inversePrimary,
            activeTrackColor = md_theme_dark_inversePrimary,
            inactiveTrackColor = md_theme_dark_surfaceVariant
        )
    )
}

data class Track(val name: String, val artist: String, val resId: Int)