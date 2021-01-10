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
    val numberArray = IntArray(creditCardNumber.length)
    for (i in creditCardNumber.indices) {
        numberArray[i] = creditCardNumber[i].toString().toInt()
    }
    var sum = 0
    numberArray.reverse()
    for(x in numberArray.indices){
        var number = numberArray[x]
        if(x%2!=0){
            number*=2
            if(number>9){
                number = number%10+1
            }
        }
        sum+=number
    }
    return sum % 10 == 0
}

