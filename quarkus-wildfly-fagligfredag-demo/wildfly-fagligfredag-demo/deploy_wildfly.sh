#!/bin/bash

source wildfly.conf

mvn clean install

cp target/wildfly-demo-0.0.1-SNAPSHOT.war $WILDFLY_HOME/standalone/deployments/wildfly-demo.war
