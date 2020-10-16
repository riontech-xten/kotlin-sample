package biz.eldmandate.kotlinsample.model

import com.google.gson.annotations.SerializedName


/**
 * Created by Vaghela Mithun R. on 15/10/20.
 * Smart Call Center Solution Pvt. Ltd.
 * mithun@smartadvancedtech.com
 */
class NasaApod {
    var copyright: String? = null
    var date: String? = null
    var explanation: String? = null;
    var hdurl: String? = null

    @SerializedName("media_type")
    var mediaType: String? = null

    @SerializedName("service_version")
    var serviceVersion: String? = null

    var title: String? = null

    var url: String? = null
}