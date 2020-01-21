# paypay-software-challenge
Software Challenge for Paypay written in Java

1. Coding task

2 libraries to use to make this project work :

- Lombok (https://projectlombok.org/download)
- JavaFx (https://gluonhq.com/products/javafx/) javafx.base.jar is enough for use.

Implementation is based on two internal (immutable) stacks.

2. Design Question: Design A Google Analytic like Backend System. We need to provide Google Analytic like services to our customers. Pls provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

The system needs to:

- handle large write volume: Billions write events per day.
- handle large read/query volume: Millions merchants want to get insight about their business. Read/Query patterns are time-series related metrics.
- provide metrics to customers with at most one hour delay.
- run with minimum downtime.
- have the ability to reprocess historical data in case of bugs in the processing logic.

## First thoughts

- Billions write events per day : All those writing queries should be streamed and stored into some Data management intermediary database.

- handle large read/query volume: This data management part should be totally independent from the writing part. Some batch task should periodically with a bulk approach reformat data gotten from streaming into a different storage.

- provide metrics to customers with at most one hour delay : workers have to handle things with this constraint

- run with minimum downtime : have an horizontally perfomance repartition to keep working as much as possible without any break

- have the ability to reprocess historical data in case of bugs in the processing logic : flexible overriding system needed

## Approach

Here I suppose we chose to use an AWS infrastructure.

1) Writing processing:
Writing requests should be redirected to different instances of data savers (balance load balancing) -> https://aws.amazon.com/kinesis/ could handle this. AWS Glue (To Define stored data structures) + One Crawler (running periodically) + S3 Buckets could help us save data to parquet format (convenient in the situation of lots of information handling and querying (with date and time partitions for example)).
Data should be replicated for historical purposes and cleaned up on the buffer storage periodically (depending on the amount and capacity).

2) Reading part:
Some application for synthetics display must be developped retrieving data from before aggregated thanks to workers and stored in some NoSql tool combined with ElasticSearch to allow people retrieving data quickly.
Formatting tasks could be performed with some task Queue which would be implemented thanks to tools like Redis. Redis would allow to store all the tasks consumers (workers) should consume. Parameterizing this part is part of the process to find the fine balance so that people eager to watch their data analytics do not see their data with more than one hour delay (3), 4)).

3) Analytics users should be able to interact with the stored data coming from the multiple data writings.
Updating data in case of bugs in processing logic should be handled with some task addition.




