package com.chslcompany.gallery.framework.downloader.androiddownloader

interface Downloader {
    fun downloadFile(url: String, description: String): Long
}