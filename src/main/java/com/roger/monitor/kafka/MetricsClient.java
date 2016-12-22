package com.roger.monitor.kafka;

import java.util.Map;

public interface MetricsClient {
	void start();
	void stop();
	Map<String, Long> getOffset();
}
