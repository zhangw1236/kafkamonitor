package com.roger.monitor.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.api.PartitionOffsetRequestInfo;
import kafka.common.TopicAndPartition;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.consumer.SimpleConsumer;

public class ProducerMetricsClient implements MetricsClient {
	private static Logger logger = LoggerFactory.getLogger(ProducerMetricsClient.class);
	private final Thread thread;
	private final AtomicBoolean running;
	private int interval;
	private List<String> brokers;
	private String topic;
	private SimpleConsumer consumer;
	private String clientName = "ProducerMetricsClient";
	private int maxPartitionNum = 0;
	private Map<String, Long> data;

	public ProducerMetricsClient(String hosts, int port, int interval) {
		brokers = Arrays.asList(hosts.split(","));
		this.interval = interval;
		running = new AtomicBoolean(false);
		data = new HashMap<String, Long>();

		consumer = new SimpleConsumer(brokers.get(0), port, 100000, 64 * 1024, clientName);
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					collect();
				} catch (Exception e) {
					logger.error("start ProducerMetricsClient failed", e);
				}
			}
		});
	}

	public void collect() {
		List<String> topics = Collections.singletonList(topic);
		TopicMetadataRequest req = new TopicMetadataRequest(topics);
		kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);

		List<TopicMetadata> metaData = resp.topicsMetadata();
		for (TopicMetadata item : metaData) {
			for (PartitionMetadata part : item.partitionsMetadata()) {
				maxPartitionNum = part.partitionId() > maxPartitionNum ? part.partitionId() : maxPartitionNum;
			}
		}

		while (running.get()) {
			try {
				int loop = 0;
				while (loop <= maxPartitionNum) {
					data.put(Integer.toString(loop), getLastOffset(loop, kafka.api.OffsetRequest.LatestTime()));
				}

				Thread.sleep(interval);
			} catch (InterruptedException e) {
				logger.error("Exception: ", e);
			}
		}
	}

	public long getLastOffset(int partition, long whichTime) {
		TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
		Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
		requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
		kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo,
				kafka.api.OffsetRequest.CurrentVersion(), clientName);
		OffsetResponse response = consumer.getOffsetsBefore(request);

		if (response.hasError()) {
			logger.error("Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition));
			return 0;
		}
		long[] offsets = response.offsets(topic, partition);
		return offsets[0];
	}

	@Override
	public Map<String, Long> getOffset() {
		return data;
	}

	@Override
	public void start() {
		if (running.compareAndSet(false, true)) {
			thread.start();
			logger.info("ProducerMetricsClient started");
		}
	}

	@Override
	public void stop() {
		if (running.compareAndSet(true, false)) {
			logger.info("ProducerMetricsClient stopped");
		}
	}
}
