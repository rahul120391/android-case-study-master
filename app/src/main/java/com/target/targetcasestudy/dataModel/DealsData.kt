package com.target.targetcasestudy.dataModel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.AISLE
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.DEAL_TABLE_NAME
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.DESCRIPTION
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.ID
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.IMAGE_URL
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.REGULAR_PRICE
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.SALE_PRICE
import com.target.targetcasestudy.dataProvider.database.DatabaseConstants.TITLE
import kotlinx.parcelize.Parcelize

data class DealsData(
    val products: List<Product>?=null
)

@Parcelize
@Entity(tableName = DEAL_TABLE_NAME)
data class Product(
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int?=null,
    @ColumnInfo(name = AISLE)
    val aisle: String?=null,
    @ColumnInfo(name = DESCRIPTION)
    val description: String?=null,
    @ColumnInfo(name = IMAGE_URL)
    val image_url: String?=null,
    @Embedded(prefix = REGULAR_PRICE)
    val regular_price: RegularPrice?=null,
    @Embedded(prefix = SALE_PRICE)
    val sale_price: SalePrice?=null,
    @ColumnInfo(name = TITLE)
    val title: String?=null
):Parcelable

@Parcelize
data class RegularPrice(
    val amount_in_cents: Int?=null,
    val currency_symbol: String?=null,
    val display_string: String?=null
):Parcelable

@Parcelize
data class SalePrice(
    val amount_in_cents: Int?=null,
    val currency_symbol: String?=null,
    val display_string: String?=null
):Parcelable