package expo.modules.vaslib.data

import expo.modules.kotlin.records.Field
import expo.modules.kotlin.records.Record

class ResponseData : Record {
    @Field
    val acquirer: String = ""

    @Field
    val additionalAmount: String = ""

    @Field
    val amount: Float = 0.toFloat()

    @Field
    val amountStr: String = ""

    @Field
    val authCode: String? = null

    @Field
    val batchNumber: String = ""

    @Field
    val cardNumber: String = ""

    @Field
    val panLast4Digits: String = ""

    @Field
    val cardProduct: String = ""

    @Field
    val cardType: String = ""

    @Field
    val cvmResults: String = ""

    @Field
    val entryMode: String = ""

    @Field
    val expirationDate: String? = null

    @Field
    val interfaceId: String = ""

    @Field
    val localCurrency: String = ""

    @Field
    val mcc: String = ""

    @Field
    val merchantAddress: String = ""

    @Field
    val merchantName: String = ""

    @Field
    val merchantNumber: String = ""

    @Field
    val merchantPostalCode: String = ""

    @Field
    val merchantVatNumber: String = ""

    @Field
    val operatorNumber: String = ""

    @Field
    val packageName: String = ""

    @Field
    val posVersion: String = ""

    @Field
    val referenceNumber: String = ""

    @Field
    val responseCode: Int = 0

    @Field
    val responseCodeThirtyNine: String = ""

    @Field
    val responseMessage: String = ""

    @Field
    val responseMessageThirtyNine: String = ""

    @Field
    val terminalNumber: String = ""

    @Field
    val voided: Boolean = false

    @Field
    val voucherNumber: String = ""

    @Field
    val orderNumber: String = ""
}
