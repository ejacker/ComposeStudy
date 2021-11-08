package cn.ljpc.wechat.util

fun checkMail(mail: String): Boolean {
    return Regex("^([A-Za-z0-9_\\-\\.\\u4e00-\\u9fa5])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,8})\$").matches(
        mail
    )
}