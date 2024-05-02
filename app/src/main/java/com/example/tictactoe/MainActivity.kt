package com.example.tictactoe

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var buttons: Array<Button>
    private var currentPlayerX = true
    private lateinit var playerTurn: TextView
    private lateinit var newGame: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button1 = findViewById<Button>(R.id.btn1)
        val button2 = findViewById<Button>(R.id.btn2)
        val button3 = findViewById<Button>(R.id.btn3)
        val button4 = findViewById<Button>(R.id.btn4)
        val button5 = findViewById<Button>(R.id.btn5)
        val button6 = findViewById<Button>(R.id.btn6)
        val button7 = findViewById<Button>(R.id.btn7)
        val button8 = findViewById<Button>(R.id.btn8)
        val button9 = findViewById<Button>(R.id.btn9)
        buttons = arrayOf(button1, button2, button3, button4, button5, button6, button7, button8, button9)
        currentPlayerX = true
        newGame(buttons)
        newGame = findViewById(R.id.btnNewGame)
        newGame.setOnClickListener{
            newGame(buttons)
            playerTurn.text = "Player X's Turn"
        }
        playerTurn = findViewById(R.id.txtPlayerTurn)
        for(button in buttons){
            button.setOnClickListener {
                playGame(button)
            }
        }
    }

    private fun newGame(buttons : Array<Button> ) {
        for (button in buttons) {
            button.setText("")
        }

        currentPlayerX = true
    }

    fun playGame(buttonSelected: Button) {
        if (buttonSelected.text.equals("")) {
            if (currentPlayerX) {
                buttonSelected.text = "X"
                currentPlayerX = false
                playerTurn.text = "Player O's Turn"
            } else {
                buttonSelected.text = "O"
                currentPlayerX = true
                playerTurn.text = "Player X's Turn"
            }
        }
    }
}