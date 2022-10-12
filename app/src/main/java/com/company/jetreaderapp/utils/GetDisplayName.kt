package com.company.jetreaderapp.utils

fun getDisplayName(user: String) : String {
    return user.split('@')[0]
}