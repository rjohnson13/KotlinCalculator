/* Author: Robert Johnson
 * Project: KotlinCalculator
 *
 * Thanks to Edvin and his sample Kotlin Calculator. I was able to take what they had
 * and add in my own additional functions, as well as transition all the FXML and CSS
 * to Kotlin and type-safe CSS.
 * https://github.com/edvin/calculator
 */

import javafx.beans.property.SimpleStringProperty
import javafx.scene.control.Button
import javafx.stage.Stage
import tornadofx.App
import tornadofx.View

import tornadofx.*

class MyApp : App(Calculator::class, Styles::class) {
    override fun start(stage: Stage) {
        stage.isResizable = false
        super.start(stage)
    }
}

class Calculator : View() {
    //SimpleStringProperties to store values for the displays
    //probably could be implemented using a Model, but I could not
    //  figure that out for the moment
    val equation = SimpleStringProperty("")
    val display = SimpleStringProperty("")

    override val root = vbox { id = "calculator"
        //label that holds the longer equation
        label(equation){ id = "equation"}

        //label that holds the current value
        label(display) { id = "display"}

        //bunch of buttons
        hbox{
            button("CE") {addClass("op")}
            button("C"){addClass("op")}
            button("⌫"){addClass("op")}     //backspace
            button("\u00F7"){addClass("op")} //divide
        }
        hbox{
            button("7")
            button("8")
            button("9")
            button("x"){addClass("op")}
        }
        hbox{
            button("4")
            button("5")
            button("6")
            button("-"){addClass("op")}
        }
        hbox{
            button("1")
            button("2")
            button("3")
            button("+"){addClass("op")}
        }
        hbox{
            button("\u00B1"){addClass("op")}    //plus minus
            button("0")
            button("."){addClass("op")}
            button("="){addClass("op")}
        }
    }

    init {
        title = "Calculator"

        //sets each buttons action to the op function
        root.lookupAll(".button").forEach { button ->
            button.setOnMouseClicked {
                op((button as Button).text)
            }
        }
    }

    //helpful flag to handle some display logic
    var newNum = true

    //stores the equation total
    var total: Long = 0
    //stores the current operation
    var curOp: String = ""

    //calculates new total, then updates both displays
    fun opAction(op: String) {
        total = if (curOp == ""){
            displayValue
        } else {
            opSwitch(curOp, total, displayValue)
        }
        equation.value += displayValue.toString() + op
        display.value = total.toString()

        curOp = op
        newNum = true
    }

    //finalizes the last operation, resets displays and values
    fun eqAction() {
        display.value = opSwitch(curOp, total, displayValue).toString()

        equation.value = ""
        curOp = ""
        total = 0
        newNum = true
    }

    fun clearEquation() {
        equation.value = ""
        display.value = ""
        curOp = ""
        total = 0
        newNum = true

    }

    fun clearDisplay() {
        display.value = ""
    }

    fun backSpace() {
        println("in backspace")
        if(display.value.length > 1){
            display.value = display.value.substring(0, display.value.length - 1)
        } else {
            display.value = ""
        }

    }

    fun decimalOp(){
        println("implement decimals")
        //todo maybe need to change everything to floats
    }

    fun plusMinus() {
        println("implement plusMinus")
        //todo add '-' sign to display. Will that read as negative with toLong()?
    }

    val displayValue: Long
        get() = when (display.value) {
            "" -> 0
            else -> display.value.toLong()
        }


    fun op(x: String) {
        if (Regex("[0-9]").matches(x)) {
            if (newNum){
                display.value = x
                newNum = false
            } else{
                display.value += x
            }
        } else {
            when(x) {
                "+" -> opAction("+")
                "-" -> opAction("-")
                "\u00F7" -> opAction( "/") //divide
                "x" -> opAction("x")
                "=" -> eqAction()

                "C" -> clearEquation()
                "CE" -> clearDisplay()
                "⌫" -> backSpace() //backspace
                "." ->  decimalOp()//todo implement decimals
                "\u00B1" -> plusMinus() //todo implement plus minus

            }
        }
    }

    fun opSwitch(op: String, x: Long, y: Long): Long{
        when(op) {
            "+" -> return add(x, y)
            "-" -> return sub(x, y)
            "/" -> return div(x, y)
            "x" -> return mul(x, y)
        }
        return 0
    }

    fun add(x: Long, y:Long): Long{
        return x+y
    }
    fun sub(x: Long, y:Long): Long{
        return x-y
    }
    fun div(x: Long, y:Long): Long{
        return x/y
    }
    fun mul(x: Long, y:Long): Long{
        return x*y
    }

}
