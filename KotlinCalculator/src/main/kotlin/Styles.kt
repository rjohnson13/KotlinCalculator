import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import tornadofx.*

class Styles: Stylesheet() {

    companion object {
        val calculator by cssid()
        val equation by cssid()
        val display by cssid()
        val button by cssclass()
        val op by cssclass()
    }


    init {
        root {
            fontSize = 18.px
        }

        calculator {
            alignment = Pos.CENTER_RIGHT
            backgroundColor += c("494b4e")
        }
        equation {
            fontFamily = "monospace"
            alignment = Pos.BOTTOM_RIGHT
            padding = CssBox(20.px,5.px,5.px,5.px)
            fontSize = 15.px
            textFill = c("#b0b6b1")
        }

        display {
            fontFamily = "monospace"
            alignment = Pos.BOTTOM_RIGHT
            padding = CssBox(20.px,5.px,5.px,5.px)
            fontSize = 40.px
            textFill = c("e8e8e8")
        }

        button {
            prefWidth = 60.px
            prefHeight = 50.px
            backgroundColor += Color.LIGHTGRAY
            backgroundInsets = multi(CssBox(0.px, 0.px, 0.px, 0.px))
            backgroundRadius = multi(CssBox(0.px, 0.px, 0.px, 0.px))
            borderColor += CssBox(c("979797"),c("979797"),c("979797"),c("979797"))
            borderWidth = multi(CssBox(0.5.px,0.5.px,0.5.px,0.5.px))
        }

        button and armed {
            backgroundColor += c("ff9d42")
        }

        button and op{
            backgroundColor += c("ff9d42")
        }

        button and op and armed {
            backgroundColor += c("bf6937")
        }

    }
}