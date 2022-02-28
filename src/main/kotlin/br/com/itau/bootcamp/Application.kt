package br.com.itau.bootcamp

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("br.com.itau.bootcamp")
		.start()
}

