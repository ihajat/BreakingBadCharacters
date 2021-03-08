package com.example.breakingbadcharacters.presentation.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun Context.getResource(resource: Int): String {
    return this.getResources().getString(resource)
}