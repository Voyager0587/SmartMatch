package com.example.smartmatch.base.kxt
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author:SunShibo
 * @date:2022-08-22 23:12
 * @feature: some ext func
 */

fun Context.toast(content: String) =
    Toast.makeText(this, content, Toast.LENGTH_SHORT).show()

fun Context.toast(res: Int) =
    Toast.makeText(this, getString(res), Toast.LENGTH_SHORT).show()

fun Context.toast(res: Int, vararg context: String) {
    var content: String? = null
    for (element in context) {
        if (element == " ") {
            continue
        }
        content += " , "
        content += element
    }
    Toast.makeText(this, getString(res) + content, Toast.LENGTH_SHORT).show()
}

fun Context.initSp(): SharedPreferences =
    this.getSharedPreferences("LoginStatus", Context.MODE_PRIVATE)

/**
 *
 * *我们必须使用反射机制从ActivityMainBinding*中的inflate方法加载我们的布局*，并使用相应的inflate方法。
 *
 * We must use the reflection mechanism to load our layout
 * from the inflate method in ActivityMainBinding
 * and use the corresponding inflate method.
 *
 *
 *
 */
inline fun <VB : ViewBinding> Any.getViewBinding(inflater: LayoutInflater, position: Int = 0): VB {
    val vbClass =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[position].getDeclaredMethod("inflate", LayoutInflater::class.java)
    return inflate.invoke(null, inflater) as VB
}

/**
 *简化：获取viewBing实例
 *
 * 功能：在任意类中调用时，通过反射获取该类的泛型参数 VB 的实际类型，并根据指定的位置调用对应的 ViewBinding 类的 inflate 方法来创建并返回 ViewBinding 实例。
 *
 * 函数的参数包括一个 LayoutInflater 对象、一个 ViewGroup 对象和一个可选的位置参数 position（默认为 0）。
 * 通过传入不同的 LayoutInflater 和 ViewGroup 对象，可以在不同的上下文中创建 ViewBinding 实例。
 */
inline fun <VB : ViewBinding> Any.getViewBinding(
    inflater: LayoutInflater,
    container: ViewGroup?,
    position: Int = 0
): VB {
    val vbClass =
        (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.filterIsInstance<Class<VB>>()
    val inflate = vbClass[position].getDeclaredMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )
    return inflate.invoke(null, inflater, container, false) as VB
}



