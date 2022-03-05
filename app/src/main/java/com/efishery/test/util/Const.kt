package com.efishery.test.util

object Const {
    object Network {
        const val PREFIX_GENERAL = "v1/general/"
        const val PREFIX = "v1/"
    }

    object Access {
        const val AUTH_PREFIX = "Bearer"
    }

    object Database {
        const val DATABASE_NAME = "PARENTING_HUB_DATABASE"

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
            const val basePath = "Paninti/"
            const val storePath = "PARENTINGHUB/"
        }

        object MimeType {
            const val image = "image/jpeg"
            const val pdf = "application/pdf"
        }

        object Image {
            const val defaultFileName = "PARENTING-Image"
        }

        object Pending {
            const val isPending = 1
            const val notPending = 0
        }
    }
}