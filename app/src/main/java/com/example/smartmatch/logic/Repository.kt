package com.example.smartmatch.logic


import androidx.lifecycle.liveData
import com.example.smartmatch.logic.network.NetworkCenter
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

/**
 * @className: Repository
 * @author: Voyager
 * @description: Repository
 * @date:  2023/9/22 21:31
 * @version 1.0
 **/
object Repository {


     fun getMMNetData() = fire(Dispatchers.IO) {
        val response = NetworkCenter.getMMNetData()
        run {
            Result.success(response)
        }
    }

     fun createNewArea(id: Int, name: String) = fire(Dispatchers.IO) {
        val response = NetworkCenter.createNewArea(id, name)
        run {
            Result.success(response)
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}