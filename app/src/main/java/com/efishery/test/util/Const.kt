package com.efishery.test.util

import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.StructuredName.PREFIX

object Const {
    object Network {
        const val PREFIX_GENERAL = "v1/general/"
        const val PREFIX = "v1/"

        object Home {
            const val PRODUCT = PREFIX + "storages/5e1edf521073e315924ceab4/list"
        }

    }



    object Access {
        const val AUTH_PREFIX = "Bearer"
    }

    object Database {
        const val DATABASE_NAME = "EFISHERY_DATABASE"

        object Table {
            const val PRODUCT = "product_entity"
        }
    }

    object DataStore{
        const val TOKEN = "TOKEN_KEY"
        const val USERNAME = "USERNAME"
    }

    object File {
        object Location {
            const val basePath = "Efishery/"
            const val storePath = "PARENTINGHUB/"
        }

        object MimeType {
            const val image = "image/jpeg"
            const val pdf = "application/pdf"
        }

        object Image {
            const val defaultFileName = "EFISHERY-Image"
        }

        object Pending {
            const val isPending = 1
            const val notPending = 0
        }
    }
}