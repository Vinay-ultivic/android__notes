package com.ultivic.savenotes.presentation.ui.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.ultivic.savenotes.R
import com.ultivic.savenotes.data.rerpository.AlarmSchedulerRepositoryImpl
import com.ultivic.savenotes.domain.model.AlarmItem
import com.ultivic.savenotes.domain.model.NoteDetailsModel
import com.ultivic.savenotes.domain.repository.AlarmSchedulerRepository
import com.ultivic.savenotes.presentation.ui.ui_elements.ComposeButton
import com.ultivic.savenotes.presentation.ui.ui_elements.ComposeTextField
import com.ultivic.savenotes.presentation.viewmodel.MyViewModel
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateDetail(
    navController: NavHostController,
    viewModel: MyViewModel,
    selectedItem: NoteDetailsModel?
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var secondText by remember {
        mutableStateOf("")
    }
    var messageText by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    var alarmScheduler: AlarmSchedulerRepository = AlarmSchedulerRepositoryImpl(context = context)
    var alarmItem: AlarmItem? = null


    // Declaring integer values
    // for year, month and day
    var mYear: Int
    var mMonth: Int
    var mDay: Int

    // Initializing a Calendar
    var mCalendar = Calendar.getInstance()

    // Fetching current year, month and day
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    Log.e("mmm", mDay.toString())

    var mmYear: Int = 0
    var mmMonth: Int = 0
    var mmDay: Int = 0

    mCalendar.time = Date()
    var mDate = remember { mutableStateOf("") }

    var mDatePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            mmYear = mYear
            mmMonth = mMonth
            mmDay = mDayOfMonth
            Log.e("nnn", mDate.value + " sdsd=" + mmYear)
        }, mYear, mMonth, mDay
    )

    var mHour = mCalendar[Calendar.HOUR_OF_DAY]
    var mMinute = mCalendar[Calendar.MINUTE]
    var mmTime = remember { mutableStateOf("") }

    var mmhour: Int = 0
    var mmMinutes: Int = 0
    var mTimePickerDialog = TimePickerDialog(

        context,
        { _, mHour: Int, mMinute: Int ->
            mmTime.value = "$mHour:$mMinute"
            mmhour = mHour
            mmMinutes = mMinute

        }, mHour, mMinute, false
    )

    var selectedCalendar = Calendar.getInstance()
//    selectedCalendar.set(mmYear, mmMonth, mmDay, mmhour, mmMinutes)// Set selected date and time

    Log.e(
        "selected calender",
        mmYear.toString() + " month" + mmMonth + "day" + mmDay + "hour" + mmhour + "minute" + mmMinutes
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Update Details",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            background = Color.Transparent,
                        )
                    )
                },
                backgroundColor = Color(0xFF6650a4),
                contentColor = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(horizontal = 20.dp)
        ) {
            Row(

                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                title = ComposeTextField(title = viewModel.a!!.titleName, fontSize = 18)
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                description =
                    ComposeTextField(title = viewModel.a!!.titleDescription, fontSize = 18)
            }

            Spacer(modifier = Modifier.size(2.dp))

            //Calender icon
            IconButton(
                onClick = {
                    mDatePickerDialog.show()
                },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    Color.Green,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Open Date Picker",
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.size(5.dp))

            //calender text
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(5.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
            ) {
                // Displaying the mDate value in the Text
                Text(
                    text = "Selected Date: ${mDate.value}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.background(
                        Color.White
                    )
                )
                Log.e("mmmm", mDate.value)
            }

            Spacer(modifier = Modifier.size(2.dp))

            IconButton(
                onClick = {
                    mTimePickerDialog.show()
                },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                colors = IconButtonDefaults.iconButtonColors(
                    Color.Green,
                    contentColor = Color.Black
                )
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Open Time Picker",
                    tint = Color.Black
                )
            }

            Spacer(modifier = Modifier.size(5.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(5.dp)
                    .clip(
                        RoundedCornerShape(20.dp)
                    )
            ) {
                Text(
                    text = "Selected Time: ${mmTime.value}",
                    fontSize = 16.sp,
                    modifier = Modifier.background(
                        Color.White
                    )
                )
                Log.e("bvbv", mmTime.value)
            }
            // Display selected time
            Row {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        var currentCalendar = Calendar.getInstance()
                        var currentMillis = currentCalendar.timeInMillis
                        selectedCalendar.set(
                            mmYear,
                            mmMonth,
                            mmDay,
                            mmhour,
                            mmMinutes
                        )// Set selected date and time

                        Log.e("fffaa", mmYear.toString())


                        var selectedMillis = selectedCalendar.timeInMillis
                        Log.e("selected", selectedMillis.toString())

                        var differenceInMillis = selectedMillis - currentMillis
                        Log.e("differenceInMillis", differenceInMillis.toString())

                        var differenceInSeconds = differenceInMillis / 1000

                        Log.e("differenceInSeconds", differenceInSeconds.toString())

                        Log.e(
                            "ddddd",
                            "selected= " + selectedMillis + "current = " + currentMillis + "differenvce=" + differenceInMillis
                        )
                        alarmItem = AlarmItem(
                            alarmTime = LocalDateTime.now().plusSeconds(
                                differenceInSeconds
                            ),
                            message = messageText
                        )
                        Toast.makeText(context, "Reminder set done", Toast.LENGTH_SHORT).show()
                        alarmItem?.let(alarmScheduler::schedule)
                        differenceInSeconds = 0
                        selectedCalendar.clear()
                        differenceInMillis = 0
                        secondText = ""
                        messageText = ""
                        Log.e("fff", "dfdf")

                    }) {
                        Text(text = "Schedule")

                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = {
                        alarmItem?.let(alarmScheduler::cancel)
                    }) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                ComposeButton(onClick = {
                    val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                    val currentDateAndTime: String = sdf.format(Date())
                    val updatedFruits =
                        NoteDetailsModel(
                            title, description, currentDateAndTime, id = viewModel.a!!.id
                        )
                    viewModel.update(updatedFruits)

                    navController.popBackStack()

                }, buttonText = stringResource(id = R.string.update_details))
            }
        }
    }
}