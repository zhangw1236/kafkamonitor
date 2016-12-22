package com.roger.monitor.kafka;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MetricsCollector {
	private static Logger logger = LoggerFactory.getLogger(MetricsCollector.class);
	
	private String groupId;
	private String zkAddress;
	private String brokers;
	private int zkPort;
	private int kafkaPort;
	private int interval;
	private String topic;
	ProducerMetricsClient producerMetricsClient;
	
	public String toString() {
		return "groupId: " + groupId + ","
				+ "zkAddress: " + zkAddress + ","
				+ "brokers: " + brokers + ","
				+ "zkPort: " + zkPort + ","
				+ "kafkaPort: " + kafkaPort + ","
				+ "interval: " + interval + ","
				+ "topic: " + topic;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getZkAddress() {
		return zkAddress;
	}

	public void setZkAddress(String zkAddress) {
		this.zkAddress = zkAddress;
	}

	public String getBrokers() {
		return brokers;
	}

	public void setBrokers(String brokers) {
		this.brokers = brokers;
	}

	public int getZkPort() {
		return zkPort;
	}

	public void setZkPort(int zkPort) {
		this.zkPort = zkPort;
	}

	public int getKafkaPort() {
		return kafkaPort;
	}

	public void setKafkaPort(int kafkaPort) {
		this.kafkaPort = kafkaPort;
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public void init() {
		logger.info("MetricsCollector init");
		producerMetricsClient = new ProducerMetricsClient(brokers, zkPort, interval);
		producerMetricsClient.start();
	}
	
	public Map<String, Long> getOffset() {
		Map<String, Long> result = new HashMap<String, Long>();
		Map<String, Long> producerList = producerMetricsClient.getOffset();
		Collection<Long> values = producerList.values();
		long producerOffset = 0;
		for(Long v : values) {
			producerOffset += v;
		}
		
		result.put("producer", producerOffset);
		result.put("consumer", (long) 12000);
		
		return result;
	}
}
