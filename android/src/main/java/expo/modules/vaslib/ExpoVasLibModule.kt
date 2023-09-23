package expo.modules.vaslib

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.core.os.bundleOf
import expo.modules.kotlin.exception.Exceptions
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import expo.modules.vaslib.mellon.VASCallsArkeBusiness
import expo.modules.vaslib.mellon.VASResponseListener
import expo.modules.kotlin.Promise
import expo.modules.kotlin.exception.CodedException
import com.google.gson.Gson
import expo.modules.core.errors.ModuleDestroyedException
import expo.modules.vaslib.data.ResponseData
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

private val moduleCoroutineScope = CoroutineScope(Dispatchers.IO)

class ExpoVasLibModule : Module() {
    private val context: Context
        get() = appContext.reactContext ?: throw Exceptions.ReactContextLost()

    private var vas: VASCallsArkeBusiness? = null

    private var transactionPromise : Promise? = null

    override fun definition() = ModuleDefinition {
        Name("ExpoVasLib")

        class ResponseListener : VASResponseListener {
            override fun responseReceived() {
                val jsonString = vas?.responseData

                if (jsonString != null) {
                    /** TODO JSON decode */
                    transactionPromise?.resolve(jsonString)
                } else {
                    transactionPromise?.reject(CodedException("response data is null"))
                }
            }
        }

        val responseListener = ResponseListener()

        Function("initialise") {
            if (vas == null) {
                vas = VASCallsArkeBusiness(context)
                vas?.addListener(responseListener)
            }
        }

        AsyncFunction("doTransaction") { amount: Double, orderNumber: String, needAppPrinted: Boolean, promise: Promise ->
            moduleCoroutineScope.launch {
                if (vas == null) {
                    promise.reject(CodedException("VAS is not initialised"))
                }

                val reqMap = mapOf(
                    "amount" to amount,
                    "orderNumber" to orderNumber,
                    "needAppPrinted" to needAppPrinted
                )

                val jsonText = Gson().toJson(reqMap)

                vas?.setSendData(jsonText)

                vas?.doTransaction("SALE")

                transactionPromise = promise
            }
        }

        OnDestroy {
            try {
                moduleCoroutineScope.cancel(ModuleDestroyedException())
            } catch (e: IllegalStateException) {
                Log.e(TAG, "The scope does not have a job in it")
            }
        }
    }
}
