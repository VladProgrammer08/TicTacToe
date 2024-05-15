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
        setButtonsClickable()
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
            if (checkForWin()) {
                playerTurn.text = if (!currentPlayerX) "Player X Wins!" else "Player O Wins!"
                for (button in buttons) {
                    setButtonsUnclickable()
                }
            }
            else if (checkForDraw()) {
                playerTurn.text = "It's a Draw!"
                setButtonsUnclickable()
            }
        }
    }
    fun checkForWin(): Boolean {
        val winPositions = arrayOf(
            intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
        )

        for (pos in winPositions) {
            if (buttons[pos[0]].text.toString() != "" &&
                buttons[pos[0]].text.toString() == buttons[pos[1]].text.toString() &&
                buttons[pos[1]].text.toString() == buttons[pos[2]].text.toString()) {
                return true
            }
        }
        return false
    }

    private fun checkForDraw(): Boolean {
        for (button in buttons) {
            if (button.text == "") {
                return false
            }
        }
        return !checkForWin()
    }
    private fun setButtonsUnclickable() {
        for (button in buttons) {
            button.setOnClickListener(null)
        }
    }
    private fun setButtonsClickable() {
        for (button in buttons) {
            button.setOnClickListener {
                playGame(button)
            }
        }
    }
}