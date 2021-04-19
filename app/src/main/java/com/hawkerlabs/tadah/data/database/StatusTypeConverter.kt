//package com.hawkerlabs.tadah.data.database
//
//import androidx.room.TypeConverter
//import com.hawkerlabs.tadah.data.database.model.Status
//import com.squareup.moshi.JsonAdapter
//import com.squareup.moshi.Moshi
//import com.squareup.moshi.Types
//import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
//import kotlinx.android.parcel.RawValue
//import java.lang.reflect.Type
//
//class StatusTypeConverter {
//
//    val factory = PolymorphicJsonAdapterFactory.of(Status::class.java, "type")
//
//
//    @TypeConverter
//    fun fromStatus(status: @RawValue Status): String {
//        val type: Type = Types.getRawType(
//                Status::class.java
//        )
//        val moshi = Moshi.Builder()
//                .build()
//        val adapter: JsonAdapter<Status> = moshi.adapter(Status::class.java)
//
//        return adapter.toJson(status)
//    }
//
//
//    @TypeConverter
//    fun toStatus(json: String): @RawValue Status? {
//        val type: Type = Types.getRawType(
//                Status::class.java
//        )
//        val moshi = Moshi.Builder()
//                .build()
//        val adapter: JsonAdapter<Status> = moshi.adapter(Status::class.java)
//
//        return try {
//            adapter.fromJson(json)
//        } catch (error: Throwable) {
//            null
//        }
//    }
//
//}