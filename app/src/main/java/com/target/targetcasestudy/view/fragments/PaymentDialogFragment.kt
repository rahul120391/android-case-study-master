package com.target.targetcasestudy.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.target.targetcasestudy.R
import com.target.targetcasestudy.base.BaseDialogFragment
import com.target.targetcasestudy.data.validateCreditCard
import com.target.targetcasestudy.databinding.DialogPaymentBinding

/**
 * Dialog that displays a minimal credit card entry form.
 *
 * Your task here is to enable the "submit" button when the credit card number is valid and
 * disable the button when the credit card number is not valid.
 *
 * You do not need to input any of your actual credit card information. See `Validators.kt` for
 * info to help you get fake credit card numbers.
 *
 * You do not need to make any changes to the layout of this screen (though you are welcome to do
 * so if you wish).
 */
class PaymentDialogFragment : BaseDialogFragment<DialogPaymentBinding>(), View.OnClickListener,
    TextWatcher {
    override val layoutId: Int
        get() = R.layout.dialog_payment

    private var binding: DialogPaymentBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = viewDataBinding
        initializeClickListeners()
    }

    private fun initializeClickListeners() {
        binding?.apply {
            btnSubmit.setOnClickListener(this@PaymentDialogFragment)
            btnCancel.setOnClickListener(this@PaymentDialogFragment)
            etCardNumber.addTextChangedListener(this@PaymentDialogFragment)
        }
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.btnSubmit -> {
                dismiss()
            }
            R.id.btnCancel -> {
                dismiss()
            }
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        binding?.btnSubmit?.isEnabled = if (p0?.length ?: 0 > 0) {
            validateCreditCard(p0?.toString() ?: "")
        } else {
            false
        }

    }

    override fun afterTextChanged(p0: Editable?) {
    }


}