package com.dreamsoftware.tvnexa.utils

interface IMapper<IN, OUT> {
    fun mapInToOut(input: IN): OUT
    fun mapInListToOutList(input: Iterable<IN>): Iterable<OUT>
    fun mapOutToIn(input: OUT): IN
    fun mapOutListToInList(input: Iterable<OUT>): Iterable<IN>
}