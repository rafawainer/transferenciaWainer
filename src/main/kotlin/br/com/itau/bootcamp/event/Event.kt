package br.com.itau.bootcamp.event

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable
import org.springframework.data.annotation.Id

@DynamoDBTable(tableName = "transferencias")
data class Event(

    @Id
    var id: KeyEvent,

    @DynamoDBAttribute(attributeName = "data")
    var data: DataEvent? = null
)