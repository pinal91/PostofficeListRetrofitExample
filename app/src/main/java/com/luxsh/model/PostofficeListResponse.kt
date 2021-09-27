package com.luxsh.model

import java.io.Serializable

data class PostofficeListResponse(
	val Status: String? = null,
	val Message: String? = null,
	val PostOffice: List<PostOfficeItem?>? = null
):Serializable

data class PostOfficeItem(
	val Circle: String? = null,
	val Description: String? = null,
	val BranchType: String? = null,
	val State: String? = null,
	val DeliveryStatus: String? = null,
	val Taluk: String? = null,
	val Region: String? = null,
	val Country: String? = null,
	val Division: String? = null,
	val PINCode: String? = null,
	val District: String? = null,
	val Name: String? = null
):Serializable

