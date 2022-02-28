package br.com.itau.bootcamp.mapper

interface Mapper<T, U> {
    fun map(t: T): U
}