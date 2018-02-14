package com.github.sguzman.scala.js

import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.Div
import org.scalajs.dom.{Event, document}

object Main {
  implicit final class StrWrap(str: String) {
    def id[A] = document.getElementById(str).asInstanceOf[A]
  }

  implicit final class IntWrap(x: Int) {
    def isZero = x == 0
    def isNonZero = x != 0
  }

  @dom def _render(s: Vars[String], x: Var[Int]): Binding[Div] = {
    <div>
      <button disabled={x.bind.isZero} onclick={_: Event => x.value -= 1} >Previous</button>
      <video src={s.value(x.bind)}></video>
      <button disabled={x.bind >= s.value.length} onclick={_: Event => x.value += 1} >Next</button>
    </div>
  }

  def render = _render(RawFiles.list, Var(0))

  def main(args: Array[String]): Unit = dom.render(document.body, render)
}
