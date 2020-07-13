package com.app.petitionatlas.retrofit

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.io.IOException
import java.lang.reflect.Type

class NullOnEmptyConverterFactory :  Converter.Factory() {

//    override fun responseBodyConverter(
//        type: Type?,
//        annotations: Array<Annotation?>?,
//        retrofit: Retrofit
//    ): Converter<ResponseBody?, *>? {
//        val delegate: Converter<ResponseBody, *> =
//            retrofit.nextResponseBodyConverter(this, type, annotations)
//        return object : Converter<ResponseBody?, Any?>() {
//            @Throws(IOException::class)
//            fun convert(body: ResponseBody): Any? {
//                return if (body.contentLength() === 0) null else delegate.convert(body)
//            }
//        }
//    }

}