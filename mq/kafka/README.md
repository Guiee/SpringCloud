1. 创建消费者和生产者的Map配置
2. 根据Map配置创建对应的消费者工厂(consumerFactory)和生产者工厂(producerFactory)
3. 根据consumerFactory创建监听器的监听器工厂
4. 根据producerFactory创建KafkaTemplate(Kafka操作类)
5. 创建监听容器