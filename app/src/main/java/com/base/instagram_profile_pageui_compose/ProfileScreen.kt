package com.base.instagram_profile_pageui_compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ProfileScreen(){

    Column(
        modifier = Modifier.fillMaxSize()
    ){
     TopBar("Lionel Messi", modifier = Modifier.padding(10.dp))
     ProfileSection()
    }
}

@Composable
fun TopBar(
    name:String,
    modifier:Modifier=Modifier
){
    Row(
      verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ){
        Icon(
           imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.Black,
            modifier = modifier.size(24.dp)
        )
        Text(
           text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Icon(
            painter = painterResource(R.drawable.bell),
            contentDescription = "Bell",
            tint = Color.Black,
            modifier = modifier.size(24.dp)
        )

        Icon(
            painter = painterResource(R.drawable.dots),
            contentDescription = "Bell",
            tint = Color.Black,
            modifier = modifier.size(24.dp)
        )
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier=Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
            .padding(horizontal = 20.dp)
    ){
     RoundImage(painterResource(R.drawable.profile),modifier=Modifier.size(100.dp).weight(3f))
        Spacer(modifier = modifier.width(16.dp))
        StatSection(modifier=modifier.weight(7f))

    }
    ProfileDescription(
        displayname = "GOAT",
        description = "Playing Football makes me Feel ALive\n" +
                "Vica Barca vica Cataluniya\n" +
                "I was a kid with a Dream to be the best and i am living my dream\n"+
                "Lets just enjoy the greatest game of all time",
        url = "https://football.com",
        followedby = listOf(
            "Cristiano Ronaldo",
            "Luka Modric"
        ),
        othercount = 18
    )
    Spacer(modifier=Modifier.height(10.dp))
    ButtonsSection(modifier=Modifier.fillMaxWidth())
    Spacer(modifier=Modifier.height(25.dp))
    HighlightSection()
}

@Composable
fun RoundImage(
    image:Painter,
    modifier: Modifier=Modifier
){
    Image(
        painter = image,
        contentDescription = "Profile Image",
        contentScale = ContentScale.Crop,
        modifier = modifier.aspectRatio(1f,matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StatSection(
    modifier: Modifier=Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ){
        ProfileStat("601","Posts")
        ProfileStat("450M","Followers")
        ProfileStat("15","Following")
    }
}

@Composable
fun ProfileStat(
    numberText:String,
    text:String,
    modifier: Modifier=Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){

        Text(
            text=numberText,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Spacer(modifier=modifier.height(4.dp))
        Text(
            text=text,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun ProfileDescription(
    displayname:String,
    description:String,
    url:String,
    followedby:List<String>,
    othercount:Int,
){
    val letterspacingdata=0.5.sp
    val lineheightdata=20.sp

    Column (
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp)
    ){
        Text(
            text = displayname,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterspacingdata,
            lineHeight = lineheightdata
        )

        Text(
            text = description,
            letterSpacing = letterspacingdata,
            lineHeight = lineheightdata
        )

        Text(
            text = url,
            color=Color(0XFF3D3D91),
            letterSpacing = letterspacingdata,
            lineHeight = lineheightdata
        )

        if(followedby.isNotEmpty()){
            Text(
                text = buildAnnotatedString {
                    val boldstyle=SpanStyle(
                        color=Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    append("Followed By ")

                    followedby.forEachIndexed{index: Int, name: String ->
                        pushStyle(boldstyle)
                        append(name)
                        pop()
                        if(index < followedby.size-1){
                            append(" , ")
                        }
                    }

                    if(othercount>2){
                        append(" and ")
                        pushStyle(boldstyle)
                        append("$othercount others")
                    }

                },
                letterSpacing = letterspacingdata,
                lineHeight = lineheightdata
            )
        }


    }
}

@Composable
fun ButtonsSection(
    modifier: Modifier=Modifier
){
    val minwidth=95.dp
    val height=30.dp
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier=modifier
    ){
      ActionButtons(
          text = "Following",
          icon=Icons.Default.KeyboardArrowDown,
          modifier = Modifier.defaultMinSize(minwidth)
              .height(height)
      )
        ActionButtons(
            text = "Message",
            modifier = Modifier.defaultMinSize(minwidth)
                .height(height)
        )
        ActionButtons(
            text = "Email",
            modifier = Modifier.defaultMinSize(minwidth)
                .height(height)
        )
        ActionButtons(
            icon=Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .height(height)
        )
    }
}

@Composable
fun ActionButtons(
    modifier: Modifier=Modifier,
    text:String?=null,
    icon:ImageVector?=null
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.Black,
                shape= RoundedCornerShape(5.dp)
            )
            .padding(6.dp)
    ){

        if(text!=null){
            Text(
                text=text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        }
        if(icon!=null){
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint=Color.Black
            )
        }
    }
}


@Composable
fun HighlightSection(
    modifier: Modifier=Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ){
      HighlightItems(
          image = painterResource(R.drawable.argentia),
          text = "Argentina"
      )
        HighlightItems(
            image = painterResource(R.drawable.barcelona),
            text = "Barcelona"
        )
        HighlightItems(
            image = painterResource(R.drawable.copa),
            text = "Copa"
        )
        HighlightItems(
            image = painterResource(R.drawable.psg),
            text = "Psg"
        )
    }
}


@Composable
fun HighlightItems(
    image:Painter,
    text: String,
    modifier: Modifier=Modifier
){

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ){

        Image(
            painter = image,
            contentDescription = "highlight images",
            contentScale = ContentScale.Crop,
            modifier=modifier.size(80.dp)
                .clip(CircleShape)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = CircleShape
                )
        )
        Spacer(modifier=modifier.height(4.dp))
        Text(
            text=text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

}