#!/bin/bash

source wildfly.conf

$WILDFLY_HOME/bin/standalone.sh \
	-b=0.0.0.0 \
	-c standalone_w-demo.xml \
	-Djboss.socket.binding.port-offset=100 \
	-DpingMessage="PingMicroProfile In Properties"