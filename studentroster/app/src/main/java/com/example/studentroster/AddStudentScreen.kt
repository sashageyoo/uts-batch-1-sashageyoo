package com.example.studentroster

import android.app.Activity
import android.graphics.Color
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import com.example.studentroster.data.Student

class AddStudentScreen(private val activity: Activity) {

    fun createView(): View {
        val scrollView = ScrollView(activity).apply {
            setBackgroundColor(Color.WHITE)
        }

        val mainLayout = LinearLayout(activity).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(80, 80, 80, 80)
        }

        val titleText = TextView(activity).apply {
            text = "Add Student"
            textSize = 28f
            setTextColor(Color.parseColor("#333333"))
            setPadding(0, 0, 0, 60)
            gravity = Gravity.CENTER
        }

        val studentIdEditText = EditText(activity).apply {
            hint = "Student ID"
            inputType = InputType.TYPE_CLASS_TEXT
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        val studentNameEditText = EditText(activity).apply {
            hint = "Student Name"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        val studentPhoneEditText = EditText(activity).apply {
            hint = "Student Phone"
            inputType = InputType.TYPE_CLASS_PHONE
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
        }

        val studentAddressEditText = EditText(activity).apply {
            hint = "Student Address"
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_SENTENCES
            setPadding(40, 40, 40, 40)
            textSize = 16f
            setBackgroundResource(android.R.drawable.edit_text)
            minLines = 3
        }

        val registerButton = Button(activity).apply {
            text = "Register"
            textSize = 16f
            setPadding(0, 30, 0, 30)
            setOnClickListener {
                val studentId = studentIdEditText.text.toString().trim()
                val studentName = studentNameEditText.text.toString().trim()
                val studentPhone = studentPhoneEditText.text.toString().trim()
                val studentAddress = studentAddressEditText.text.toString().trim()

                if (studentId.isEmpty() || studentName.isEmpty() ||
                    studentPhone.isEmpty() || studentAddress.isEmpty()) {
                    Toast.makeText(activity, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val student = Student(
                    id = studentId,
                    name = studentName,

                    phone = studentPhone,
                    address = studentAddress
                )

                StudentRoster.addStudent(student)

                Toast.makeText(activity, "Student registered successfully!", Toast.LENGTH_SHORT).show()

                val dashboardScreen = DashboardScreen(activity)
                activity.setContentView(dashboardScreen.createView())
            }
        }

        // Set layout params for views
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            setMargins(0, 0, 0, 30)
        }

        // Add views to layout
        mainLayout.addView(titleText)
        mainLayout.addView(studentIdEditText, layoutParams)
        mainLayout.addView(studentNameEditText, layoutParams)
        mainLayout.addView(studentPhoneEditText, layoutParams)
        mainLayout.addView(studentAddressEditText, layoutParams)
        mainLayout.addView(registerButton, layoutParams)

        scrollView.addView(mainLayout)
        return scrollView
    }
}
