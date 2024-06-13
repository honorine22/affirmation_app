package com.example.affrimationapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affrimationapp.data.DataResource
import com.example.affrimationapp.ui.theme.AffrimationAppTheme
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AffrimationAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
//                    AffirmationApp()
                    TopicApp()
                }
            }
        }
    }
}

@Composable
fun TopicApp(){
    TopicsList(topics = DataResource().loadTopicsList())
}

@Composable
fun TopicsList(topics: List<Topic>){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = topics,
            key = {message ->
                message.courseNumber
            }
        ){topic ->
            TopicCard(topic = topic)
        }
    }
}

@Composable
fun TopicCard(topic: Topic){
    Box(modifier = Modifier.padding(16.dp, 42.dp, 0.dp, 0.dp)) {
        Card {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Image(
                    painter = painterResource(id = topic.imageResource),
                    contentDescription = stringResource(id = topic.stringResourceId),
                    modifier = Modifier
                        .width(75.dp)
                        .height(75.dp)
                )
                Column(modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 0.dp)) {
                    Text(
                        text = stringResource(id = topic.stringResourceId),
                        modifier = Modifier.padding(4.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Row(horizontalArrangement = Arrangement.Start, modifier = Modifier.padding(8.dp,8.dp,0.dp,0.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_grain),
                            contentDescription = "connections",
                            modifier = Modifier.background(color = Color.Black)
                        )
                        Text(
                            text = "${topic.courseNumber}",
                            modifier = Modifier.padding(4.dp,0.dp,8.dp,0.dp),
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun AffirmationApp() {
        AffirmationList(affirmationList = DataResource().loadAffirmations() )
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier){
    Card(modifier = modifier) {
        Column(Modifier.padding(0.dp,36.dp,0.dp,0.dp)) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Text(text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>){
    LazyColumn(modifier = Modifier) {
        items(affirmationList){affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationAppPreview() {
    AffrimationAppTheme {
        AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1), modifier = Modifier)
    }
}