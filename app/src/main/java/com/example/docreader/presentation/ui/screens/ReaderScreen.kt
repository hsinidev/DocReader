package com.example.docreader.presentation.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.docreader.domain.model.ReaderTheme
import com.example.docreader.presentation.viewmodel.DocReaderViewModel
import com.example.docreader.ui.theme.ExecutiveRoyalNavy
import com.example.docreader.ui.theme.ComfortingTeal

@Composable
fun ReaderScreen(viewModel: DocReaderViewModel = hiltViewModel()) {
    val activeDoc by viewModel.activeFile.collectAsState()
    val theme by viewModel.readerTheme.collectAsState()
    
    var pageNum by remember { mutableIntStateOf(1) }
    var points = remember { mutableStateListOf<Offset>() }

    val bgThemeColor = when (theme) {
        ReaderTheme.DAY_PAPER -> Color(0xFFFCFBF9)
        ReaderTheme.SEPIA_WARM -> Color(0xFFF4ECD8)
        ReaderTheme.NIGHT_DARK -> Color(0xFF0F172A)
        else -> MaterialTheme.colorScheme.background
    }

    val textThemeColor = when (theme) {
        ReaderTheme.NIGHT_DARK -> Color(0xFFF8FAFC)
        else -> Color(0xFF0F172A)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(bgThemeColor)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = activeDoc?.name ?: "No Document Active",
                fontWeight = FontWeight.Bold,
                color = textThemeColor,
                fontSize = 18.sp,
                modifier = Modifier.weight(1f)
            )
            
            // Reading Theme Toggles
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf(ReaderTheme.DAY_PAPER, ReaderTheme.SEPIA_WARM, ReaderTheme.NIGHT_DARK).forEach { t ->
                    TextButton(
                        onClick = { viewModel.setReaderTheme(t) },
                        modifier = Modifier.size(36.dp)
                    ) {
                        Text(
                            text = when(t) {
                                ReaderTheme.DAY_PAPER -> "D"
                                ReaderTheme.SEPIA_WARM -> "S"
                                ReaderTheme.NIGHT_DARK -> "N"
                                else -> "A"
                            },
                            color = textThemeColor,
                            fontWeight = if (theme == t) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }

        // Active paper layout viewport
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(if (theme == ReaderTheme.NIGHT_DARK) Color(0xFF1E293B) else Color.White)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        points.add(change.position)
                    }
                }
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                Text(
                    text = "Executive Document Content (Page $pageNum)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = textThemeColor
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "This report contains the audited quarterly summaries for the corporate operations workspace. All calculations have been structured to ensure compliance with scoped storage, and the offline-first persistence engines. Note that data-sync is executed in the background through prioritized WorkManager schedulers.",
                    color = textThemeColor.copy(alpha = 0.8f),
                    lineHeight = 22.sp
                )
            }

            // Annotation rendering canvas
            Canvas(modifier = Modifier.fillMaxSize()) {
                if (points.isNotEmpty()) {
                    val path = Path()
                    path.moveTo(points.first().x, points.first().y)
                    for (i in 1 until points.size) {
                        path.lineTo(points[i].x, points[i].y)
                    }
                    drawPath(
                        path = path,
                        color = ComfortingTeal,
                        style = Stroke(width = 4.dp.toPx())
                    )
                }
            }
        }

        // Navigation Footer
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { if (pageNum > 1) { pageNum--; points.clear() } },
                colors = IconButtonDefaults.iconButtonColors(contentColor = textThemeColor)
            ) {
                Icon(Icons.Default.NavigateBefore, contentDescription = null)
            }
            Text("Page $pageNum / ${activeDoc?.totalPages ?: 12}", color = textThemeColor, fontWeight = FontWeight.SemiBold)
            IconButton(
                onClick = { if (pageNum < (activeDoc?.totalPages ?: 12)) { pageNum++; points.clear() } },
                colors = IconButtonDefaults.iconButtonColors(contentColor = textThemeColor)
            ) {
                Icon(Icons.Default.NavigateNext, contentDescription = null)
            }
        }
    }
}
