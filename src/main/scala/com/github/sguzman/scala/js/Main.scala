package com.github.sguzman.scala.js

import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.html.{Div, Input}
import org.scalajs.dom.{Event, document}

object Main {
  implicit final class StrWrap(str: String) {
    def id[A] = document.getElementById(str).asInstanceOf[A]
  }

  @dom def _render(s: Var[String]): Binding[Div] = {
    <div>
      <button disabled={s.bind.isEmpty} ></button>
      <input id="input" oninput={_: Event => s.value_=("input".id[Input].value) } />
      <p>{s.bind}</p>
      <table>
        <tbody>
          {
            for (vid <- RawFiles.list) yield {
              <video src={vid} ></video>
            }
          }
        </tbody>
      </table>
    </div>
  }

  def render = _render(Var(""))

  def main(args: Array[String]): Unit = dom.render(document.body, render)
}
