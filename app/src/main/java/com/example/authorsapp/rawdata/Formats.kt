package com.example.authorsapp.rawdata

import com.google.gson.annotations.SerializedName

data class Formats(
    @SerializedName(value = "application/epub+zip")
    val epub_zip : String,
    @SerializedName(value = "application/octet-stream")
    val octet_stream: String,
    @SerializedName(value = "application/pdf")
    val pdf: String,
    @SerializedName(value = "application/prs.tex")
    val prs_tex: String,
    @SerializedName(value = "application/rdf+xml")
    val rdf_xml: String,
    @SerializedName(value = "application/x-mobipocket-ebook")
    val x_mobipocket_ebook: String,
    @SerializedName(value = "image/jpeg")
    val image_jpeg: String,
    @SerializedName(value = "text/html")
    val text_html: String,
    @SerializedName(value = "text/html; charset=iso-8859-1")
    val text_html_charset_iso_8859_1: String,
    @SerializedName(value = "text/html; charset=utf-8")
    val text_html_charset_utf_8: String,
    @SerializedName(value = "text/plain")
    val text_plain: String,
    @SerializedName(value = "text/plain; charset=iso-8859-1")
    val text_plain_charset_iso_8859_1: String,
    @SerializedName(value = "text/plain; charset=us-ascii")
    val text_plain_charset_us_ascii: String,
    @SerializedName(value = "text/plain; charset=utf-8")
    val text_plain_charset_utf_8: String
)