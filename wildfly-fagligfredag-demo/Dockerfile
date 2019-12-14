FROM jarrydk/wildfly:18.0.1.Final

MAINTAINER Michael Bornholdt Nielsen, jarry.dk

RUN ${WILDFLY_HOME}/bin/add-user.sh -a -u 'micbn' -p 'MyPassword' -g 'user'

RUN rm ${WILDFLY_HOME}/standalone/configuration/standalone.xml
ADD standalone.xml ${WILDFLY_HOME}/standalone/configuration/

ADD ./modules/ ${WILDFLY_HOME}/modules/

COPY target/wildfly-demo.war ${DEPLOYMENT_DIR}
