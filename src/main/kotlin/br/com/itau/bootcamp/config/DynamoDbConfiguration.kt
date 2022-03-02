package br.com.itau.bootcamp.config

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain
import com.amazonaws.regions.Regions
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Primary
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories

@org.springframework.context.annotation.Configuration
@EnableDynamoDBRepositories("br.com.itau.bootcamp.repository")
    class DynamoDBConfiguration{}

//    companion object {
//        val awsRegion: String = "us-east-1"
//    }


    @Bean
    @Primary
    fun DynamoDBMapperConfig(): DynamoDBMapperConfig {
        return DynamoDBMapperConfig.DEFAULT
    }

    @Bean
    @Primary
    fun dynamoDBMapper(amazonDynamoDB: AmazonDynamoDB, config: DynamoDBMapperConfig): DynamoDBMapper {
        return DynamoDBMapper(amazonDynamoDB, config)
    }

    @Bean
    @Primary
    fun amazonDynamoDB(): AmazonDynamoDB {

        return AmazonDynamoDBClientBuilder.standard()
            .withCredentials(DefaultAWSCredentialsProviderChain())
            .withRegion(Regions.fromName("us-east-1"))
            .build()

    }


//package br.com.itau.bootcamp.config
//
//import br.com.itau.bootcamp.entity.DynamoData
//import com.amazonaws.auth.AWSStaticCredentialsProvider
//import com.amazonaws.auth.BasicAWSCredentials
//import com.amazonaws.client.builder.AwsClientBuilder
//import com.amazonaws.regions.Regions
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper
//import com.amazonaws.services.dynamodbv2.document.DynamoDB
//import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput
//import com.amazonaws.services.dynamodbv2.model.ResourceInUseException
//import org.slf4j.LoggerFactory
//import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.annotation.Profile
//
//@org.springframework.context.annotation.Configuration
//@EnableDynamoDBRepositories
//class DynamoDbConfiguration {
//
//    private val logDynamo: org.slf4j.Logger = LoggerFactory.getLogger(this.javaClass)
//
//    @Profile("dynamo-local")
//    @org.springframework.context.annotation.Bean("amazonDynamoDB")
//    fun dynamoDbLocal(@Value("\${aws.dynamodb.endpoint}") amazonDynamoDBEndpoint: String): AmazonDynamoDB {
//
//        val client = AmazonDynamoDBClientBuilder
//            .standard()
//            .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials("key", "secret")))
//            .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, Regions.AP_NORTHEAST_1.toString()))
//            .build()
//        createTableForEntity(client, DynamoData::class)
//        return client
//    }
//
//    private fun createTableForEntity(amazonDynamoDB: AmazonDynamoDB, entity: String) {
//
//        val tableRequest = DynamoDBMapper(amazonDynamoDB)
//            .generateCreateTableRequest(entity.java)
//            .withProvisionedThroughput(ProvisionedThroughput(1L, 1L))
//
//        try {
//            DynamoDB(amazonDynamoDB).createTable(tableRequest).waitForActive()
//            logDynamo.info("Table created! [entity=$entity]")
//        } catch (e: ResourceInUseException){
//            logDynamo.info("Tabela já existente - passo de criação da tabela ignorado! [entity=$entity]")
//        }
//    }
//
//}