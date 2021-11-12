package br.com.heroes.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.*;

import static br.com.heroes.constants.HeroesConstant.*;

import java.util.Arrays;

public class TableConfig {

    public static void main(String[] args) {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder
                        .EndpointConfiguration(ENDPOINT_DYNAMO, REGION_DYNAMO))
                .build();

        DynamoDB dynamoDB = new DynamoDB(client);

        try {

            System.out.println("Starting create table");

            Table table = dynamoDB.createTable(HEROES_TABLE_TABLE,
                    Arrays.asList(new KeySchemaElement("id", KeyType.HASH)),
                    Arrays.asList(new AttributeDefinition("id", ScalarAttributeType.S)),
                    new ProvisionedThroughput(5L, 5L));

            table.waitForActive();
            System.out.println("Sucess " + table.getDescription().getTableStatus());
        } catch (Exception e) {
            System.err.println("Could not create table");
            System.err.println(e.getMessage());
        }
    }

}
