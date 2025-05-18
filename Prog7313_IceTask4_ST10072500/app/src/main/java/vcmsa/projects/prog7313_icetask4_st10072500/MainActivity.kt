package vcmsa.projects.prog7313_icetask4_st10072500

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import vcmsa.projects.prog7313_icetask4_st10072500.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT = 10


    private val quizData = mutableListOf(

        mutableListOf("What is the capital of France?","Paris","Berlin","Rome","Madrid"),
        mutableListOf("Which ocean is the largest?","Pacific Ocean","Atlantic Ocean","Indian Ocean","Arctic Ocean"),
        mutableListOf("What is the longest river in the world?","Nile","Amazon","Yangtze","Mississippi"),
        mutableListOf("How many continents are there?","7","5","6","8"),
        mutableListOf("What is the smallest country in the world?","Vatican City","Monaco","Liechtenstein","Nauru"),
        mutableListOf("What country is known as the Land of the Rising Sun?","Japan","Vietnam","South Korea","China"),
        mutableListOf("Which US state is known as the Golden State?","California","Florida","Texas","Washington"),
        mutableListOf("What is the capital of Australia?","Canberra","Sydney","Melbourne","Perth"),
        mutableListOf("In which continent is India located?","Asia","North America","Europe","Africa"),
        mutableListOf("What mountain range is known as the Backbone of Europe?","Apls","Rockies","Andes","Himalayas")

    )

    private lateinit var progressSeekBar: SeekBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)


        quizData.shuffle()

        showNextQuiz()

        progressSeekBar = findViewById(R.id.progressSeekBar)
        progressSeekBar.max = QUIZ_COUNT
        progressSeekBar.progress = 0

        progressSeekBar.setOnTouchListener { _, _ ->
            true
        }

    }


    fun showNextQuiz(){

        binding.countLabel.text = getString(R.string.count_label, quizCount)


        val quiz = quizData[0]



        binding.questionLabel.text = quiz[0]

        rightAnswer = quiz[1]


        quiz.removeAt(0)


        quiz.shuffle()



        binding.answerBtn1.text = quiz[0]
        binding.answerBtn2.text = quiz[1]
        binding.answerBtn3.text = quiz[2]
        binding.answerBtn4.text = quiz[3]


        quizData.removeAt(0)

    }


    fun checkAnswer( view: View  ){

        val answerBtn: Button = findViewById(view.id)
        val btnText= answerBtn.text.toString()


        val alertTitle: String
        if (btnText==rightAnswer){

            alertTitle = "Correct "
            rightAnswerCount++

        }
        else {

            alertTitle = "Wrong"

        }


        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("Answer: $rightAnswer")
            .setPositiveButton("ok ") {dialogInterface, i->
                checkQuizCount()

            }

            .setCancelable(false)
            .show()

    }


    fun checkQuizCount(){

        progressSeekBar.progress = quizCount
        if (quizCount == QUIZ_COUNT ) {


            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)


        } else {
            quizCount++
            showNextQuiz()

        }

    }



}


/*
Title: Kotlin libraries mutableListOf
Author: Kotlin
Date: 2025
Availability: https://kotlinlang.org/api/core/kotlin-stdlib/kotlin.collections/mutable-list-of.html
*/


/*
Title: SeekBar
Author: Android Developers
Date: 13 March 2025
Availability: https://developer.android.com/reference/android/widget/SeekBar
*/
