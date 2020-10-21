package biz.eldmandate.kotlinsample.utils


/**
 * Created by Vaghela Mithun R. on 22/10/20.
 * Smart Call Center Solution Pvt. Ltd.
 * mithun@smartadvancedtech.com
 */
class LiveDataResult<T>(val status: Status, val data: T? = null, val err: Throwable? = null) {
    companion object {
        fun <T> success(data: T?) = LiveDataResult(Status.SUCCESS, data)
        fun <T> loading() = LiveDataResult<T>(Status.LOADING)
        fun <T> error(err: Throwable?) = LiveDataResult<T>(Status.ERROR, null, err)
    }

    enum class Status {
        SUCCESS, ERROR, LOADING
    }
}