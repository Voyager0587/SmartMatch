package com.example.smartmatch.base.util
/**
 * 按C或T的数量计算如何布局
 */
object CTNumberUtils {
    fun count(num: Int): Array<Int>? {
        return if (num <= 8) {
            arrayOf(8)
        } else if (num <= 16) {
            arrayOf(4, 4)
        } else if (num <= 32) {
            arrayOf(8, 4)
        } else if (num <= 64) {
            arrayOf(8, 8)
        } else if (num <= 127) {
            arrayOf(6, 6, 4)
        } else {
            null
        }
    }

    fun countCID(CTNum: Int, btnNum: Int): Array<IntArray> {
        val res = Array(2) {
            IntArray(btnNum)
        }
        res[0][0] = 1
        val d = CTNum / btnNum
        res[1][0] = d
        var mod = CTNum % btnNum
        if (mod > 0) {
            res[1][0]++
            mod--
        }
        for (i in 1 until btnNum) {
            res[0][i] = res[1][i - 1] + 1
            res[1][i] = res[1][i - 1] + d
            if (mod > 0) {
                res[1][i]++
                mod--
            }
            if (res[0][i] > res[1][i]) {
                res[0][i] = res[1][i]
            }
        }
        return res
    }
}