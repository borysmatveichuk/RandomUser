package net.borkiss.randomuser.ext

import android.view.View

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.setVisibleGone(visible: Boolean) {
    if (visible) this.visible() else this.gone()
}

fun View.setVisible(visible: Boolean) {
    if (visible) this.visible() else this.invisible()
}