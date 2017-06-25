package com.spring.appender;



import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.jboss.logging.Logger;


/**
 * @Description mongodb
 * @Author ErnestCheng
 * @Date 2017/5/19.
 */
public class MongoAppender extends AppenderSkeleton {

    Logger logger = Logger.getLogger(MongoAppender.class);
    private MongoClient mongoClient;  //mongodb连接客户端
    private MongoDatabase mongoDatabase;//记录日志的数据库
    private MongoCollection<BasicDBObject> logsCollection; //记录日志的集合
    private String connectionUrl;   //连接mongodb地址
    private String databaseName;    //数据库名
    private String collectionName;   //集合名
    @Override
    protected void append(LoggingEvent loggingEvent) {
        if(mongoDatabase == null) {
            MongoClientURI connectionString = new MongoClientURI(connectionUrl);
            mongoClient = new MongoClient(connectionString);
            mongoDatabase = mongoClient.getDatabase(databaseName);
            logsCollection = mongoDatabase.getCollection(collectionName, BasicDBObject.class);
        }
        logger.info("message:"+loggingEvent.getMessage());
        logsCollection.insertOne((BasicDBObject) loggingEvent.getMessage());
    }

    public void close() {
        if(mongoClient != null) {
            mongoClient.close();
        }
    }
    public boolean requiresLayout() {
        return false;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public MongoDatabase getMongoDatabase() {
        return mongoDatabase;
    }

    public void setMongoDatabase(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public MongoCollection<BasicDBObject> getLogsCollection() {
        return logsCollection;
    }

    public void setLogsCollection(MongoCollection<BasicDBObject> logsCollection) {
        this.logsCollection = logsCollection;
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }
}
