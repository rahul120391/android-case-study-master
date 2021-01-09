package com.target.targetcasestudy.data

/**
 * For an explanation of how to validate credit card numbers read:
 *
 * https://www.freeformatter.com/credit-card-number-generator-validator.html#fakeNumbers
 *
 * This contains a breakdown of how this algorithm should work as
 * well as a way to generate fake credit card numbers for testing
 *
 * The structure and signature of this is open to modification, however
 * it *must* include a method, field, etc that returns a [Boolean]
 * indicating if the input is valid or not
 *
 * Additional notes:
 *  * This method does not need to validate the credit card issuer
 *  * This method must validate card number length (13 - 19 digits), but does not
 *    need to validate the length based on the issuer.
 *
 * @param creditCardNumber - credit card number of (13, 19) digits
 * @return true if a credit card number is believed to be valid,
 * otherwise false
 */

fun validateCreditCard(creditCardNumber: String): Boolean {
    if (creditCardNumber.isBlank()) {
        return false
    }
    if (creditCardNumber.length < 13 || creditCardNumber.length > 19) {
        return false
    }
    val lastDigit = creditCardNumber[creditCardNumber.length - 1].toString().toInt()
    val ints = IntArray(creditCardNumber.length - 1)
    for(i in 0..creditCardNumber.length-2){
        ints[i] = creditCardNumber[i].toString().toInt()
    }
    ints.reverse()
    var x =0
    while(x<ints.size){
        ints[x] = ints[x]*2
        x+=2
    }
    for(i in ints.indices){
        if(ints[i]>9){
            ints[i] = ints[i]-9
        }
    }
    var sum = 0
    for(i in ints.indices){
        sum+=ints[i]
    }
    val mod = sum%10
    return mod==lastDigit
}

